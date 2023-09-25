
$(function(){


   $(document).keypress(function(e) {
     if (e.keyCode == 13)
     {
        e.preventDefault();
        confirm();
     }

 });


})

    function confirm(){
        var id = $("#user_id").val();
        var pwd = $("#user_pwd").val();

        if(id == "" )
        {
            alert("아이디를 입력해주세요");
            $("#user_id").focus();
            return false;
        }
        else if(pwd == "" )
        {
            alert("비밀번호를 입력해주세요");
            $("#user_pwd").focus();
            return false;
        }
        $.ajax({
            url:"/login_chk",
            data:{"id":id,"pwd":pwd},
            dataType:"json",
            method:'post',
            beforeSend:function(){
                $("#loginBtn").attr("disabled",true);
            }
            ,success:function(data)
            {
                //if(data==1){location.href="/main";}
                if(data==1){location.href="/main";}
                if(data!=1)
                {
                alert("로그인에 실패하였습니다.\n아이디와 비밀번호를 확인해주세요");

                    $("#loginBtn").attr("disabled",false);
                }
            }

        });
    };