var file_list= null;


$(function(){

   var menu_code ="";
   var menu_name = "";
$("#menu_li > a").on("click", function() {
    $(".main_div").empty(); //작성 페이지 삭제 후 리스트 출력
});


$("#selectbtn").on("click", function() {


    menu_code = $("#menu_h option:selected").val();
    menu_name = $("#menu_h option:selected").text();



   $("#menu_code").val($("#menu_h option:selected").val());
   $("#menu_name").val($("#menu_h option:selected").text());

   get_title();
   $("#close").click();

});


    $("#dept_btn").on("click",function(){

        if ($("#sub_dept_name option:selected").val()!="-1"){
           $("#dept_name").attr("value",$("#sub_dept_name option:selected").text()) ;
           $("#proj_code").attr("value",$("#sub_dept_name option:selected").val()) ;
           alert("등록부서가 "+ $("#sub_dept_name option:selected").text()+"로 변경되었습니다.");
        }else{
            alert("변경하실 등록부서를 선택하시고 사용해주시기 바랍니다.");
        }

    })

    $("#cmt_btn").on("click",function(){
        var sendingData = new FormData();

        var cmt1 = $("#cmt1").val();
        var cmt2 = $("#cmt2").val();
        var cmt3 = $("#cmt3").val();
        var cmt4 = $("#cmt4").val();
        var cmt5 = $("#cmt5").val();

        if(cmt1 == ""){
          alert("첫번째 필수파일을 작성하세요.");
          return false;
        }
        if(cmt2 == ""){
          alert("두번째 필수파일을 작성하세요.");
          return false;
        }
        if(cmt3 == ""){
          alert("세번째 필수파일을 작성하세요.");
          return false;
        }
        if(cmt4 == ""){
          alert("네번째 필수파일을 작성하세요.");
          return false;
        }
        if(cmt5 == ""){
          alert("다섯번째 필수파일을 작성하세요.");
          return false;
        }
        sendingData.append("cmt1", cmt1);
        sendingData.append("cmt2", cmt2);
        sendingData.append("cmt3", cmt3);
        sendingData.append("cmt4", cmt4);
        sendingData.append("cmt5", cmt5);


        $.ajax({

          url:"/cmt",
          data:sendingData,
          method:"post",
          dataType:"json",
          processData: false,
          contentType: false,
          beforeSend:function()
          {
           $("#cmt_btn").attr("disabled",true)
          },
            success:function(data){
                if(data=="1"){
                    alert("완료");
                    //opener.parent.location.reload();
                    location.reload();
                }else{
                    alert("실패 새로고침 이후 다시 사용해 주세요.");

                    $("#cmt_btn").attr("disabled",false);
                }

            }
          });
    });

    $("#sub_btn").on("click",function(){





        var sendingData = new FormData();



        var user_no = $("#user_no").val();
        var empno = $("#empno").val();
        var title = $("#title").val();
        var name = $("#name").val();
        var dept_name = $("#dept_name").val();
        var menu_code = $("#menu_code").val();
        var menu_name = $("#menu_name").val();
        var content = $("#content").val();
        var proj_code = $("#proj_code").val();
        var position = $("#position").val();
        var doc_month = $("#doc_month").val();
        var doc_turn = $("#doc_turn").val();
        var doc_sdt = $("#doc_sdt").val();
        var doc_edt = $("#doc_edt").val();


        if(menu_code == ""){
            alert("저장하실 메뉴를 선택하세요.");
            return false;
        }



        if(title == ""){
            alert("제목을 작성하세요.");
            $("#title").focus();
            return false;
        }

        if(content.length>500){
            alert("내용은 500자 이내로 작성하세요.");
            return false;
        }





        sendingData.append("user_no", user_no);
        sendingData.append("position",position);
        sendingData.append("empno", empno);
        sendingData.append("title", title);
        sendingData.append("name",name);
        sendingData.append("dept_name",dept_name);
        sendingData.append("menu_code",menu_code);
        sendingData.append("menu_name",menu_name);
        sendingData.append("proj_code",proj_code);
        sendingData.append("content",content);
        sendingData.append("doc_month",doc_month);
        sendingData.append("doc_turn",doc_turn);
        sendingData.append("doc_sdt",doc_sdt);
        sendingData.append("doc_edt",doc_edt);


        if(file_list!="" && file_list !=null)
        {
           for(var i=0;i <= file_list.length;i++)
           {
                sendingData.append("files",file_list[i]);
           }
        }
        $.ajax({

            url:"/upload",
            data:sendingData,
            method:"post",
            dataType:"json",
            processData: false,
            contentType: false,
            beforeSend:function()
            {
             $("#sub_btn").attr("disabled",true);

            },
            success:function(data){
                if(myDropzone.files==null || myDropzone.files.length<=0){
                    if(data=="1"){
                        alert("업로드 완료");
                        opener.parent.location.reload();
                        location.reload();
                        window.close();
                    }else{
                        alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");

                        $("#sub_btn").attr("disabled",false);
                    }
                }else{
                    $("#test2").click();
                }
            }
        });
    });

    $("#sub_btn_b").on("click",function() {

       var sendingData = new FormData();

       var user_no = $("#user_no").val();
       var empno = $("#empno").val();
       var title = $("#title").val();
       var name = $("#name").val();
       var dept_name = $("#dept_name").val();
       var menu_code = $("#menu_code").val();
       var menu_name = $("#menu_name").val();
       var content = $("#content").val();
       var proj_code = $("#proj_code").val();
       var position = $("#position").val();
       var doc_dt = $("#doc_dt").val();
       var trn_case = $("#trn_case2").val();





       if(menu_code == ""){
           alert("저장하실 메뉴를 선택하세요.");
           return false;
       }
       if(title == ""){
           alert("제목을 작성하세요.");
           $("#title").focus();
           return false;
       }

       if(content.length>500){
           alert("내용은 500자 이내로 작성하세요.");
           return false;
       }
       sendingData.append("user_no", user_no);
       sendingData.append("position",position);
       sendingData.append("empno", empno);
       sendingData.append("title", title);
       sendingData.append("name",name);
       sendingData.append("dept_name",dept_name);
       sendingData.append("menu_code",menu_code);
       sendingData.append("menu_name",menu_name);
       sendingData.append("proj_code",proj_code);
       sendingData.append("content",content);
       sendingData.append("doc_dt",doc_dt);
       sendingData.append("trn_case",trn_case);


              if(file_list!="" && file_list !=null)
              {
                 for(var i=0;i <= file_list.length;i++)
                 {
                      sendingData.append("files",file_list[i]);
                 }
              }

              $.ajax({
                  url:"/upload_b",
                  data:sendingData,
                  method:"post",
                  dataType:"json",
                  processData: false,
                  contentType: false,
                  beforeSend:function()
                  {
                   $("#sub_btn_b").attr("disabled",true);

                  },
                  success:function(data){
                      if(myDropzone.files==null || myDropzone.files.length<=0){
                          if(data=="1"){
                              alert("업로드 완료");
                              opener.parent.location.reload();
                              location.reload();
                              window.close();
                          }else{
                              alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");

                              $("#sub_btn_b").attr("disabled",false);
                          }
                      }else{
                          $("#test2").click();
                      }
                  }
              });


        })


        $("#sub_btn_o").on("click",function() {

               var sendingData = new FormData();

               var user_no = $("#user_no").val();
               var empno = $("#empno").val();
               var title = $("#title").val();
               var name = $("#name").val();
               var dept_name = $("#dept_name").val();
               var menu_code = $("#menu_code").val();
               var menu_name = $("#menu_name").val();
               var content = $("#content").val();
               var proj_code = $("#proj_code").val();
               var position = $("#position").val();
               var doc_month = $("#doc_month").val();
               var op_num = $("#op_num").val();





               if(menu_code == ""){
                   alert("저장하실 메뉴를 선택하세요.");
                   return false;
               }
               if(title == ""){
                   alert("제목을 작성하세요.");
                   $("#title").focus();
                   return false;
               }

               if(content.length>500){
                   alert("내용은 500자 이내로 작성하세요.");
                   return false;
               }
               sendingData.append("user_no", user_no);
               sendingData.append("position",position);
               sendingData.append("empno", empno);
               sendingData.append("title", title);
               sendingData.append("name",name);
               sendingData.append("dept_name",dept_name);
               sendingData.append("menu_code",menu_code);
               sendingData.append("menu_name",menu_name);
               sendingData.append("proj_code",proj_code);
               sendingData.append("content",content);
               sendingData.append("doc_month",doc_month);
               sendingData.append("op_num",op_num);


                      if(file_list!="" && file_list !=null)
                      {
                         for(var i=0;i <= file_list.length;i++)
                         {
                              sendingData.append("files",file_list[i]);
                         }
                      }

                      $.ajax({
                          url:"/upload_o",
                          data:sendingData,
                          method:"post",
                          dataType:"json",
                          processData: false,
                          contentType: false,
                          beforeSend:function()
                          {
                           $("#sub_btn_o").attr("disabled",true);

                          },
                          success:function(data){
                              if(myDropzone.files==null || myDropzone.files.length<=0){
                                  if(data=="1"){
                                      alert("업로드 완료");
                                      opener.parent.location.reload();
                                      location.reload();
                                      window.close();
                                  }else{
                                      alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");

                                      $("#sub_btn_b").attr("disabled",false);
                                  }
                              }else{
                                  $("#test2").click();
                              }
                          }
                      });


                })

                $("#sub_btn_i").on("click",function() {

                               var sendingData = new FormData();

                               var user_no = $("#user_no").val();
                               var empno = $("#empno").val();
                               var title = $("#title").val();
                               var name = $("#name").val();
                               var dept_name = $("#dept_name").val();
                               var menu_code = $("#menu_code").val();
                               var menu_name = $("#menu_name").val();
                               var content = $("#content").val();
                               var proj_code = $("#proj_code").val();
                               var position = $("#position").val();
                               var doc_dt = $("#doc_dt").val();
                               var ck_point = $("#ck_point").val();





                               if(menu_code == ""){
                                   alert("저장하실 메뉴를 선택하세요.");
                                   return false;
                               }
                               if(title == ""){
                                   alert("제목을 작성하세요.");
                                   $("#title").focus();
                                   return false;
                               }

                               if(content.length>500){
                                   alert("내용은 500자 이내로 작성하세요.");
                                   return false;
                               }
                               sendingData.append("user_no", user_no);
                               sendingData.append("position",position);
                               sendingData.append("empno", empno);
                               sendingData.append("title", title);
                               sendingData.append("name",name);
                               sendingData.append("dept_name",dept_name);
                               sendingData.append("menu_code",menu_code);
                               sendingData.append("menu_name",menu_name);
                               sendingData.append("proj_code",proj_code);
                               sendingData.append("content",content);
                               sendingData.append("doc_dt",doc_dt);
                               sendingData.append("ck_point",ck_point);


                                      if(file_list!="" && file_list !=null)
                                      {
                                         for(var i=0;i <= file_list.length;i++)
                                         {
                                              sendingData.append("files",file_list[i]);
                                         }
                                      }

                                      $.ajax({
                                          url:"/upload_i",
                                          data:sendingData,
                                          method:"post",
                                          dataType:"json",
                                          processData: false,
                                          contentType: false,
                                          beforeSend:function()
                                          {
                                           $("#sub_btn_i").attr("disabled",true);

                                          },
                                          success:function(data){
                                              if(myDropzone.files==null || myDropzone.files.length<=0){
                                                  if(data=="1"){
                                                      alert("업로드 완료");
                                                      opener.parent.location.reload();
                                                      location.reload();
                                                      window.close();
                                                  }else{
                                                      alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");

                                                      $("#sub_btn_b").attr("disabled",false);
                                                  }
                                              }else{
                                                  $("#test2").click();
                                              }
                                          }
                                      });


                                })




})

//function write_list() {
//
//  var status2 = "toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,width=800, height=1000, top=0,left=50";
//  window.open("/write",'팝업',status2);
//}

function get_title() { // 제목 자동설정
    var month = $("#doc_month").val();


    document.getElementById("title").value = month.substring(0,4)+ "년 " + month.substring(7,5) + "월 " + $("#doc_turn option:selected").text()
    + "(" + $("#doc_sdt").val() + "~" + $("#doc_edt").val() + ")" + $("#menu_name").val() + " - " + $("#dept_name").val();

}

function get_title_b() { // 제목 자동설정
    var date = $("#doc_dt").val();

    document.getElementById("title").value = date.substring(0,4)+ "년 " + date.substring(7,5) + "월 " + date.substring(10,8) + "일 " + $("#menu_name").val() + "(" + $("#trn_case2").val() + ")" + "-" + $("#dept_name").val();

}

function get_title_o() {

        var month = $("#doc_month").val();


        document.getElementById("title").value = month.substring(0,4)+ "년 " + month.substring(7,5) + "월 " + $("#menu_name").val() + " - " + $("#dept_name").val();

}

function get_title_im() {

        var date = $("#doc_dt").val();

        document.getElementById("title").value = date.replaceAll('-','.') + "(" + $("#ck_point").val() + "-" + $("#dept_name").val() + ")";

}



function delete_f(e){
    let fileArray = Array.from(file_list);
    fileArray.splice(e, 1);
    file_list = fileArray;
    $("span[id="+e+"]").remove();

   }
function progress(percent){

    $(".progress").show();
    $("#pro_g").css("width", "calc(" + percent + "% )");
    $(".pro_s").text(percent.toFixed(2)+"%");
}

    function get_s_menu(MENU_CODE){


     $('#menu_list_s').empty();
        $.ajax({
            url:"/get_s_menu",
            data:{"p_menu_code":MENU_CODE},
            success:function(data){
                for(var i=0; i<data.length;i++){
                     var option = $("<option value="+data[i].MENU_CODE+">"+data[i].NAME+"</option>");
                     $('#menu_list_s').append(option);
                }
            }
        })
    }




