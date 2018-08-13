$(function () {
    "use strict";  
    //執行按鈕觸發
    $('#frm_coding').on('load', function () {
        var $contents = $(this).contents();
        $contents.find('#execute').click(function () {
            $('#frm_result').attr('src', $('#frm_result').attr('src'));
            var code = $contents.find('#txt').val();
            $.post('ExecuteServlet', {
                code: code
            }, function (response) {
                $('#frm_result').contents().find("body").append(response);
            }, 'html');
        });
    });

    //.board開啟
    $('.board').on('click', board_close);

    function board_close() {
        $(this).css('background-color', 'white');
        $('#content').addClass('isBoard');
        $(this).children().show();
    }
    //.board收合
    $('#toggleBoard').on('click', function (event) {
        event.stopPropagation();
        $('.board').css('background-color', 'rgba(231,231,231,1.00)');
        $('#content').removeClass('isBoard');
        $('.board').children().hide();
        $('.board').addClass('hover');
    });

    //loadfile縮放
    $('.loadfile h2').on('click', function () {
        $('.loadfile').toggleClass('isloadfile');

        if ($('.loadfile').hasClass('isloadfile')) {
            $(this).html('~');
            $.get('DisplayFileServlet', {}, function (response) {
                $('.loadfile').append(response);
                $('.loadfile').find('p').css('cursor','pointer');
            }, 'html');
        } else {
            $(this).html('é');
        }
        $('.loadfile p').toggle(function () {
            $(this).remove();
        });
    });

    //讀取檔案功能
    $(document).on('click', '#loadfile p', function (event) {
        var filename = $(this).html().substring(15);
        if ($(this).html() !== "請先登入" && $(this).html() !== "沒有存檔") {
            $.get('LoadFileServlet', {
                filename: filename
            }, function (response) {
                if (response === "找不到檔名") {
                    message(response);
                } else {
                    $('#frm_coding').contents().find("#txt").val(response);
                    $('#frm_coding').contents().find("#modified").html("未修改");
                    $('#modified').css('color', 'black');
                    message("讀取成功");
                }
            });
        }
    });

    //按鈕觸發發布功能
    $('#frm_coding').on('load', function () {
        var coding_content = $(this).contents();
        coding_content.find('#publish').on('click', function () {
            if (coding_content.find('#modified').html() === "未修改") {
                $.post('PublishServlet', {
                }, function (response) {
                    if (response === "請先登入") {
                        message(response);
                    } else if (response === "沒有程式碼或程式碼未儲存") {
                        message(response);
                    } else {
                        $("#dialogpublish").dialog("open");
                    }
                });
            } else {
                message("請先存檔");
            }
        });
    });

    //彈出對話框
    function message(response) {
        var mes = $('#message');
        mes.text(response);
        stop(false, true);
        mes.show();
        mes.fadeOut(1000);
    }

    //發布對話框
    $("#dialogpublish").dialog({
        autoOpen: false,
        height: 800,
        width: 790,
        modal: true,
        resizable: false,
        draggable: false,
        buttons: {
            "送出": function () {
                var title = $('#title').val();
                var description = $('#description').val();
                if (validate($('#title'), title) & validate($('#description'), description)) {
                    $.post('PublishServlet', {
                        title: title,
                        description: description
                    }, function (response) {
                        alert(response);
                    });
                    $(this).dialog("close");
                    dialogclose();
                    $('#title').removeClass("ui-state-error");
                    $('#description').removeClass("ui-state-error");
                    $('#frm_board').attr('src', $('#frm_board').attr('src'));
                }
            },
            "取消": function () {
                $(this).dialog("close");
                $('#title').removeClass("ui-state-error");
                $('#description').removeClass("ui-state-error");
                dialogclose();
            }
        }
    });

    function validate(field, value) {
        if (value === "") {
            field.addClass("ui-state-error");
            return false;
        } else {
            return true;
        }
    }

    function dialogclose() {
        if ($('#title') !== "undefined") {
            $('#title').val("");
        }
        if ($("#description") !== "undefined") {
            $("#description").val("");
        }
    }

});