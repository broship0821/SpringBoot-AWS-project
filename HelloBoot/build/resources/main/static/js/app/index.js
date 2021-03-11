var main = {
    init : function(){
        console.log(this);
        var _this = this;//이 js가 호출될때 고유의 init() 함수를 실행시키기 위함
        //등록 버튼
        $('#btn-save').on('click', function(){
            _this.save();
        });
        //수정 버튼
        $('#btn-update').on('click', function(){
            _this.update();
        });
        //삭제 버튼
        $('#btn-delete').on('click', function(){
            _this.delete();
        });
    },
    save : function() {
         var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
         };

         $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
         }).done(function(){
            alert('글이 성공적으로 등록되었습니다.');
            window.location.href = '/';
         }).fail(function(error){
            alert(JSON.stringify(error));
         });
    },
    update : function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('게시글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();