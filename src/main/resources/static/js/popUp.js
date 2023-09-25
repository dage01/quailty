$(function(){
  $("#comment").keypress(function(e){
    if (e.keyCode == 13)
         {
//            e.preventDefault();
//            write_com();
         }
  })

});

function mBtn(param){
    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}

      let popUp_option = "width=1000,height=1000,left=20,top=10, toolbar=no,status=no, menubar =no,location=no";
      window.open("/modPage/" + param, "_self", popUp_option);

        /*remotewindow 팝업창에서 다른 팝업창*/
        /*_self 자신의 창에 팝업창내용*/

//    window.close();

}

function mBtn2(param){
    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}

      let popUp_option = "width=1000,height=1000,left=20,top=10, toolbar=no,status=no, menubar =no,location=no";
      window.open("/modPage2/" + param, "_self", popUp_option);

        /*remotewindow 팝업창에서 다른 팝업창*/
        /*_self 자신의 창에 팝업창내용*/

//    window.close();

}

function mBtn_op(param){
    if(param==""){alert("세션이 만료되었습니다. 다시 로그인 해주세요.");location.href="/logout";}

      let popUp_option = "width=1000,height=1000,left=20,top=10, toolbar=no,status=no, menubar =no,location=no";
      window.open("/modPage_op/" + param, "_self", popUp_option);

        /*remotewindow 팝업창에서 다른 팝업창*/
        /*_self 자신의 창에 팝업창내용*/

//    window.close();

}

function mBtn_im(param){
    if(param==""){alert("세션이 만료되었습니다. 다시 로그인 해주세요.");location.href="/logout";}

        let popUp_option = "width=1000,height=1000,left=20,top=10, toolbar=no, status=no, menubar = no, location = no";
        window.open("/modPage_im/" + param, "_self", popUp_option);

}

function delcomm(param){

var list_seq = $("#list_seq").val();

    if(param==""){alert("세션이 만료되었습니다.다시 로그인 해주세요");location.href="/";}
        else{
            if (confirm("삭제하시겠습니까?") == true){
            $.ajax({
                url:'/delcomm',
                data:{"seq":param,"list_seq":list_seq},
                method:"post",
                success:function(data){
                    if(data=="1"){
                        alert("삭제되었습니다.");
                        location.reload();
                        //window.close();
                    }else {alert("통신오류가 발생하였습니다.");}
                }
            })
            } else {
            return false;
            }


        }
}



function delete_list(param){

    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}
    else{
        if (confirm("삭제하시겠습니까?") == true){
        $.ajax({
            url:'/delete_list',
            data:{"seq":param},
            method:"post",
            success:function(data){
                if(data=="1"){
                    alert("삭제되었습니다.");
                    opener.parent.location.reload();
                    window.close();
                }else {alert("통신오류가 발생하였습니다.");}
            }
        })
        } else {
        return false;
        }


    }
}

function sendmail(param){

var title = $("#title").text();
var to_user = $("#writer_id").text();
var email = $("#e_mail").text();

//String[] array = email.split(",");


//alert(array);


param = param.replaceAll("<br/>","\r\n");



  $.ajax({
     url: '/test3', // 요청 할 주소
     async: true, // false 일 경우 동기 요청으로 변경
     type: 'post', // GET, PUT
     data: {"title":title,"rmk":param,"to_user":to_user,"email":email}, // 전송할 데이터
     dataType: 'json', // xml, json, script, html
     success:function(data){
        if(data==1){
        alert("발송완료");

        } else {
            alert("전송실패!");
        }

     } // 요청 완료 시
  });

}


 function com_write(){


 var rmk = $("#rmk").val().replace(/\n/g, "<br/>");



 var list_seq = $("#list_seq").val();
          if(rmk.length<0|| rmk==""){
               alert("댓글을 입력해 주시기 바랍니다.");
               $("#rmk").focus();
               return false;
          }else
           {
               $.ajax({
                   url:"/com_write"
                   ,method:"post"
                   ,data:{"rmk":rmk,"list_seq":list_seq}
                   ,beforeSend:function()
                   {
                       $("#comment_write").attr("disabled",true);
                   }
                   ,success:function(data){
                       if(data==1){
                          alert("등록이 완료되었습니다.");
                          //alert(list_seq);


                          $("#rmk").val('');
                          location.reload();
                          $("#comment_write").attr("disabled",false);
                       }
                       else{
                           alert("댓글 등록이 실패하였습니다.");
                       }
                   }//success end
               }); //ajax end
           }


       }

