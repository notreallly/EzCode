$(function () {

    //存檔功能
    $('#save').on('click', function () {
        var code = $('#txt').val();
        var loadfile = $('.loadfile', parent.document);
        $.post('SaveFileServlet', {
            code: code
        }, function (response) {
            message(response);
            $("#modified").html("未修改");
            $('#modified').css('color', 'black');
            if (response === "修改成功" || response === "已超過儲存檔案上限，請先刪除檔案") {
            } else {
                $(loadfile).removeClass('isloadfile');
                $(loadfile.find('h2')).html('é');
                $(loadfile.find('p')).toggle(function () {
                    $(this).remove();
                });
            }
        });
    });

    //刪檔功能
    $('#delete').on('click', function () {
        var loadfile = $('.loadfile', parent.document);
        $.post('DeleteFileServlet', {}, function (response) {
            message(response);
            $(loadfile).removeClass('isloadfile');
            $(loadfile.find('h2')).html('é');
            $(loadfile.find('p')).toggle(function () {
                $(this).remove();
            $('#txt').val("");
            });
        });
    });

    //彈出資訊
    function message(response) {
        var mes = $('#message', parent.document);
        mes.text(response);
        mes.show();
        mes.fadeOut(2000);
    }

    //讀取文本
    var unmodify;

    $('#txt').on('keydown', function (event) {
        unmodify = $(this).val();

        //tab件換空白
        if (event.which === 9) {
            var cIndex = this.selectionStart;
            this.value = [this.value.slice(0, cIndex), //Slice at cursor index
                "    ",
                this.value.slice(cIndex)].join('');//Join with the end
            event.stopPropagation();
            event.preventDefault();
            this.selectionStart = cIndex + 4;
            this.selectionEnd = cIndex + 4;
            $('#modified').html('已修改');
            $('#modified').css('color', 'red');
        }

    });

    //檢驗按鍵觸發是否修改textarea
    $('#txt').on('keyup', function () {
        if ($(this).val() !== unmodify) {
            $('#modified').html('已修改');
            $('#modified').css('color', 'red');
        }
    });

    //複製貼上監控
    $('#txt').on('paste cut', function () {
        $('#modified').html('已修改');
        $('#modified').css('color', 'red');
    });
});