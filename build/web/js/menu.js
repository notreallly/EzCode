/*對話框隱藏*/
$(function () {
    "use strict";
    $.get('CheckLoginServlet', {}, function (response) {
        var chk = response.split(" ");
        var id = chk[0];
        var sa = chk[1];
        LoginExist(id);
        ChkSA(sa);
    });

    function LoginExist(id) {
        if (id !== "0") {
            $("#menuLogin").hide();
            $("#menuSignUp").hide();
            $("#menuLogOut").show();
            $("#menuSearchProfile").show();
        } else {
            $("#menuLogin").show();
            $("#menuSignUp").show();
            $("#menuLogOut").hide();
            $("#menuSearchProfile").hide();
        }
    }
    ;

    function ChkSA(sa) {
        if (sa === "0") {
            $("#menuAdministrator").hide();
        } else {
            $("#menuAdministrator").show();
        }
    }
    ;

    /* 登出 */
    $("#menuLogOut").click(function () {
        LoginExist("0");
        ChkSA("0");
        $.get('LogOutServlet', {}, function (response) {
            if (response === "登出成功") {
                $('#frm_board').attr('src', $('#frm_board').attr('src'));
                $('.loadfile').removeClass('isloadfile');
                $('.loadfile h2').html('é');
                $('.loadfile p').toggle(function () {
                    $(this).remove();
                });
            }
        });
    });
});

/* 登入對話框 */
$(function () {
    "use strict";
    var LoginMail = $("#LoginMail"), LoginPassword = $("#LoginPassword"), loginFields = $(
            []).add(LoginMail).add(LoginPassword), tips = $(".validateTips");

    function updateTips(tip) {
        tips.text(tip).addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }
    function updateError(tip) {
        stop();
        tips.text(tip).addClass("ui-state-error");
        setTimeout(function () {
            tips.removeClass("ui-state-error", 1500);
        }, 500);
    }
    function checkRegexp(field, regexp, mes) {
        if (!(regexp.test(field.val()))) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    function LoginExist(id) {
        if (id !== "0") {
            $("#menuLogin").hide();
            $("#menuSignUp").hide();
            $("#menuLogOut").show();
            $("#menuSearchProfile").show();
        } else {
            $("#menuLogin").show();
            $("#menuSignUp").show();
            $("#menuLogOut").hide();
            $("#menuSearchProfile").hide();
        }
    }
    ;

    function ChkSA(sa) {
        if (sa === "0") {
            $("#menuAdministrator").hide();
        } else {
            $("#menuAdministrator").show();
        }
    }
    ;

    function CleanForm() {
        document.getElementById("dialogLoginForm").reset();
    }

    $("#dialogLogin").dialog({
        autoOpen: false,
        height: 360,
        width: 350,
        modal: true,
        resizable: false,
        draggable: false,
        buttons: [{
                text: "登入",
                click: function () {
                    var bValid = true;
                    loginFields.removeClass("ui-state-error");
                    bValid = bValid && checkRegexp(LoginMail, /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.([A-Za-z]{2,4})$/, "請確認帳號");
                    bValid = bValid && checkRegexp(LoginPassword, /^(([a-zA-Z][a-zA-Z0-9]*))+$/, "請確認密碼");
                    if (bValid) {
                        var lmail = $("#LoginMail").val();
                        var lpassword = $("#LoginPassword").val();
                        $.post('LoginServlet', {
                            mail: lmail,
                            password: lpassword
                        }, function (response) {
                            if (response === "登入失敗，請確認帳號密碼") {
                                updateError(response);
                                LoginMail.addClass("ui-state-error");
                                LoginPassword.addClass("ui-state-error");
                            } else {
                                $("#dialogLogin").dialog("close");
                                $.get('CheckLoginServlet', {}, function (response) {
                                    var chk = response.split(" ");
                                    var id = chk[0];
                                    var sa = chk[1];
                                    LoginExist(id);
                                    ChkSA(sa);
                                    if ($("#LoginMail") !== "undefined") {
                                        $("#LoginMail").val("");
                                    }
                                    if ($("#LoginPassword") !== "undefined") {
                                        $("#LoginPassword").val("");
                                    }
                                    loginFields.removeClass("ui-state-error");
                                    tips.text("登入您的帳戶");
                                    $('#frm_board').attr('src', $('#frm_board').attr('src'));
                                    $('.loadfile').removeClass('isloadfile');
                                    $('.loadfile h2').html('é');
                                    $('.loadfile p').toggle(function () {
                                        $(this).remove();
                                    });
                                });
                            }
                        });
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    $(this).dialog("close");
                    loginFields.removeClass("ui-state-error");
                    if ($("#LoginMail") !== "undefined") {
                        $("#LoginMail").val("");
                    }
                    if ($("#LoginPassword") === undefined) {
                        $("#LoginPassword").val("");
                    }
                    tips.text("登入您的帳戶");
                }
            }]
    });
    $("#menuLogin").click(function () {
        $("#dialogLogin").dialog("open");
    });
    $("#forgetpass").click(function () {
        $("#dialogLogin").dialog("close");
        $("#dialogSearchPW").dialog("open");
    });
    $("#dialogSearchPW").dialog({
        autoOpen: false,
        height: 360,
        width: 350,
        modal: true
    });
});
/* 註冊對話框 */
$(function () {
    "use strict";
    var SignUpName = $("#SignUpName"), SignUpMail = $("#SignUpMail"), SignUpPW = $("#SignUpPW"), SignUpConfirmPW = $("#SignUpConfirmPW"), signUpFields = $(
            []).add(SignUpName).add(SignUpMail).add(SignUpPW).add(
            SignUpConfirmPW), tips = $(".validateTips");

    function updateTips(tip) {
        stop();
        tips.text(tip).addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function updateError(tip) {
        stop();
        tips.text(tip).addClass("ui-state-error");
        setTimeout(function () {
            tips.removeClass("ui-state-error", 1500);
        }, 500);
    }

    function checkNameLength(field, fieldName, mes) {
        if (field.val().length > 20 || field.val().length < 3) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    function checkPWLength(field, fieldName, mes) {
        if (field.val().length > 20 || field.val().length < 6) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    function checkRegexp(field, regexp, mes) {
        if (!(regexp.test(field.val()))) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }
    function validateForm(field, mes) {
        if (($("#SignUpPW").val()) !== ($("#SignUpConfirmPW").val())) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }
    $("#dialogSignUp").dialog({
        autoOpen: false,
        height: 360,
        width: 350,
        modal: true,
        resizable: false,
        draggable: false,
        buttons: {
            "註冊": function () {
                var bValid = true;
                signUpFields.removeClass("ui-state-error");
                bValid = bValid && checkNameLength(SignUpName, "暱稱", "暱稱長度不符");
                bValid = bValid && checkRegexp(SignUpName, /^(([a-zA-Z0-9]*))+$/, "暱稱格式不符");
                bValid = bValid && checkRegexp(SignUpMail, /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.([A-Za-z]{2,4})$/, "請輸入有效信箱");
                bValid = bValid && checkPWLength(SignUpPW, "密碼", "密碼為6至20個字");
                bValid = bValid && checkRegexp(SignUpPW, /^(([a-zA-Z][a-zA-Z0-9]*))+$/, "密碼為英數組合，英文為首，大小寫有區別");
                bValid = bValid && validateForm(SignUpConfirmPW, "兩次輸入密碼不同");
                if (bValid) {
                    var sName = $("#SignUpName").val();
                    var sMail = $("#SignUpMail").val();
                    var sPW = $("#SignUpPW").val();
                    $.post('SignUpServlet', {
                        nickname: sName,
                        mail: sMail,
                        password: sPW
                    }, function (response) {
                        if (response === "註冊失敗，請更換帳號重新註冊") {
                            updateError(response);
                            SignUpMail.addClass("ui-state-error");
                        } else {
                            alert("會員註冊成功!");
                            $("#dialogSignUp").dialog("close");
                            if ($("#SignUpName") !== "undefined") {
                                $("#SignUpName").val("");
                            }
                            if ($("#SignUpMail") !== "undefined") {
                                $("#SignUpMail").val("");
                            }
                            if ($("#SignUpPW") !== "undefined") {
                                $("#SignUpPW").val("");
                            }
                            if ($("#SignUpConfirmPW") !== "undefined") {
                                $("#SignUpConfirmPW").val("");
                            }
                        }
                    });
                }
            },
            "取消": function () {
                $(this).dialog("close");
                if ($("#SignUpName") !== "undefined") {
                    $("#SignUpName").val("");
                }
                if ($("#SignUpMail") !== "undefined") {
                    $("#SignUpMail").val("");
                }
                if ($("#SignUpPW") !== "undefined") {
                    $("#SignUpPW").val("");
                }
                if ($("#SignUpConfirmPW") !== "undefined") {
                    $("#SignUpConfirmPW").val("");
                }
            }
        }
    });
    $("#menuSignUp").click(function () {
        $("#dialogSignUp").dialog("open");
    });
});

/* 會員資料框 */
$(function () {
    "use strict";
    var profileName = $("#profileName"), profilePW = $("#profilePW"), profileNewPW = $("#profileNewPW"), profileConfirmPW = $("#profileConfirmPW"), profileFields = $(
            []).add(profileName).add(profilePW).add(profileNewPW).add(
            profileConfirmPW), tips = $(".validateTips");

    function updateTips(tip) {
        tips.text(tip).addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function updateError(tip) {
        stop();
        tips.text(tip).addClass("ui-state-error");
        setTimeout(function () {
            tips.removeClass("ui-state-error", 1500);
        }, 500);
    }

    function checkNameLength(field, fieldName, mes) {
        if (field.val().length > 20 || field.val().length < 3) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    function checkPWLength(field, fieldName, mes) {
        if (field.val().length > 20 || field.val().length < 6) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    function checkRegexp(field, regexp, mes) {
        if (!(regexp.test(field.val()))) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    function fncEnable() {
        document.profileForm.mail.disabled = false;
    }

    function validateForm(field, mes) {
        if (($("#profileNewPW").val()) !== ($("#profileConfirmPW").val())) {
            field.addClass("ui-state-error");
            updateTips(mes);
            return false;
        } else {
            return true;
        }
    }

    $("#dialogSearchProfile").dialog({
        autoOpen: false,
        height: 440,
        width: 350,
        modal: true,
        resizable: false,
        draggable: false,
        buttons: {
            "儲存": function () {
                var bValid = true;
                profileFields.removeClass("ui-state-error");
                bValid = bValid && checkNameLength(profileName, "暱稱",
                        "暱稱由英數組成且3至20字元內");
                bValid = bValid && checkPWLength(profilePW, "原密碼",
                        "請確認密碼");
                bValid = bValid && checkRegexp(profileName, /^(([a-zA-Z0-9]*))+$/,
                        "暱稱由英數組成且3至20字元內");
                bValid = bValid && checkRegexp(profilePW, /^(([a-zA-Z][a-zA-Z0-9]*))+$/,
                        "請確認密碼");
                if ($("#profileNewPW").val() !== "") {
                    bValid = bValid && checkPWLength(profileNewPW,
                            "新密碼", "密碼為6至20個英數組合，英文為首最少一個，大小寫有區別 ");
                    bValid = bValid && checkPWLength(profileConfirmPW,
                            "確認密碼", "請確認密碼");
                    bValid = bValid && checkRegexp(
                            profileNewPW,
                            /^([a-zA-Z][a-zA-Z0-9]*)+$/, "密碼格式不正確");
                    bValid = bValid && checkRegexp(
                            profileConfirmPW, /^([a-zA-Z][a-zA-Z0-9]*)+$/, "密碼格式不正確");
                    bValid = bValid && validateForm(profileConfirmPW, "兩次輸入密碼不同");
                }
                if (bValid) {
                    var pName = $("#profileName").val();
                    var pPW = $("#profilePW").val();
                    if ($("#profileNewPW").val() !== "") {
                        var pNPW = $("#profileNewPW").val();
                        $.post('ModifyProfileServlet', {
                            nickname: pName,
                            password: pPW,
                            newpassword: pNPW
                        }, function (response) {
                            if (response === "暱稱與密碼修改失敗，請重新輸入") {
                                updateError(response);
                                profileName.addClass("ui-state-error");
                                profilePW.addClass("ui-state-error");
                            } else if (response === "密碼不正確") {
                                updateError(response);
                                profilePW.addClass("ui-state-error");
                            } else {
                                alert("暱稱密碼修改成功!");
                                $("#dialogSearchProfile").dialog("close");
                            }
                            if ($("#profileConfirmPW") !== "undefined") {
                                $("#profileConfirmPW").val("");
                            }
                            if ($("#profilePW") !== "undefined") {
                                $("#profilePW").val("");
                            }
                            if ($("#profileNewPW") !== "undefined") {
                                $("#profileNewPW").val("");
                            }
                        });
                    } else {
                        $.post('ModifyProfileServlet', {
                            nickname: pName,
                            password: pPW
                        }, function (response) {
                            if (response === "暱稱修改失敗，請重新輸入") {
                                updateError(response);
                                profileName.addClass("ui-state-error");
                            } else if (response === "密碼不正確") {
                                updateError(response);
                                profilePW.addClass("ui-state-error");
                            } else {
                                alert("暱稱修改成功!");
                                $("#dialogSearchProfile").dialog("close");
                            }
                            if ($("#profileConfirmPW") !== "undefined") {
                                $("#profileConfirmPW").val("");
                            }
                            if ($("#profilePW") !== "undefined") {
                                $("#profilePW").val("");
                            }
                            if ($("#profileNewPW") !== "undefined") {
                                $("#profileNewPW").val("");
                            }
                        });
                    }
                }
            },
            "取消": function () {
                $(this).dialog("close");
                if ($("#profilePW") !== "undefined") {
                    $("#profilePW").val("");
                }
                if ($("#profileNewPW") !== "undefined") {
                    $("#profileNewPW").val("");
                }
                if ($("#profileConfirmPW") !== "undefined") {
                    $("#profileConfirmPW").val("");
                }
            }
        }
    });

    /* 點選後列出會員資訊 */
    $("#menuSearchProfile").click(function () {
        $.get('SearchProfileServlet', {}, function (response) {
            if (response === '查詢失敗') {
                alert(response);
            } else {
                var array = response.split(" ");
                $('#profileMail').val(array[2]);
                $('#profileName').val(array[1]);
            }
        });
        $("#dialogSearchProfile").dialog("open");
    });
});

/* 忘記密碼 */
$(function () {
    "use strict";
    var mail = $("#mail1"), allFields = $([]).add(name).add(mail), tips = $(".validateTips2");

    function updateTips(t) {
        tips.text(t).addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function checkLength(o, n, min, max) {
        if (o.val().length > max || o.val().length < min) {
            o.addClass("ui-state-error");
            updateTips("" + n + " 的長度必須介於 " + min + " 和 " + max + " 之間");
            return false;
        } else {
            return true;
        }
    }

    function checkRegexp(o, regexp, n) {
        if (!(regexp.test(o.val()))) {
            o.addClass("ui-state-error");
            updateTips(n);
            return false;
        } else {
            return true;
        }
    }

    $("#dialogSearchPW").dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        resizable: false,
        draggable: false,
        buttons: {
            "確定": function () {
                var bValid = true;
                allFields.removeClass("ui-state-error");
                bValid = bValid && checkLength(mail, "帳號(信箱)", 1, 320);
                bValid = bValid && checkRegexp(mail, /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.([A-Za-z]{2,4})$/, "請輸入有效信箱");

                if (bValid) {
                    $(this).dialog("close");
                    alert("已傳送到信箱");
                }
            },
            "取消": function () {
                $(this).dialog("close");
            }
        }
    });
});