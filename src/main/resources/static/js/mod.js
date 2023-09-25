$(function(){
<!--파일삭제-->
             $(".deleteBtn").on("click", function(e) {
                e.preventDefault();
                /*HTML 제거*/
                deleteFile($(this));

                /*DATA 삭제*/
                var d_f_seq = $(this).attr("id");
                $.ajax({
                    url:"/delete_file",
                    data:{"seq":d_f_seq},
                    method:"post",
                    success:function(data){
                        if(data=="1"){
                           location.reload();
                        }
                    }
                })


            });
             /*<!--부서 변경 Modal Open-->*/
             $("#modalBtn").on("click", function(e) {
                var myModal = new bootstrap.Modal(document.getElementById('Modal'), "static"); //모달 맨앞으로 열기
                myModal.show();
            });

            /*메뉴변경 모달*/
             $("#modal2Btn").on("click", function(e) {
                var myModal = new bootstrap.Modal(document.getElementById('Modal2'), "static"); //모달 맨앞으로 열기
                myModal.show();
            });

            // 트리
            $("#tree").bind('select_node.jstree', function(event, data){

                            $(".main_div").empty(); //작성 페이지 삭제 후 리스트 출력
                        var path_url = data.instance.get_node(data.selected).id;
                            $.ajax({
                                url: "/menu_code/" + path_url,
                                type: "post",
                                async: false,
                                success: function(data) {

            //                        $(this).removeClass("active");
                                    if (data.length > 0) {
                                        JSON.stringify(data); // data parsing
                                        $("#jqgrid_table").clearGridData(); // clear
                                        startGrid2(); //그리드 그리기
                                        $("#jqgrid_table").jqGrid('setCaption', data[0].MENU_NAME); //caption 동적 변화

                                        $("#jqgrid_table").jqGrid("setGridParam", {
                                            data: data
                                        }).trigger("reloadGrid");

                                    } else {
                                        $("#jqgrid_table").clearGridData();
                                        startGrid2();
                                        $("#jqgrid_table").jqGrid('setCaption', '등록된 데이터가 없습니다.');
                                    }
                                }
                            });
                  });



            /*<!--부서 변경 Method-->*/
             $("#modDeptBtn").on("click", function(e) {
                if ($("#sub_dept_name option:selected").val()!="-1"){
                       $("#dept_name").attr("value",$("#sub_dept_name option:selected").text()) ;
                       $("#proj_code").attr("value",$("#sub_dept_name option:selected").val()) ;
                       alert("등록부서가 "+ $("#sub_dept_name option:selected").text()+"로 변경되었습니다.\n저장 버튼을 눌러 저장을 해주시기 바랍니다.");
                }else{
                        alert("변경하실 등록부서를 선택하시고 사용해주시기 바랍니다.");
                }
                $(".DeptModal_close").click();
             });

             /*메뉴 변경*/
             $("#modMenu").on("click", function(e) {
             if ($("#menu_h_s option:selected").val()!="-1"){
                    $("#menu").attr("value",$("#menu_h_s option:selected").text()) ;
                    $("#menu_code").attr("value",$("#menu_h_s option:selected").val()) ;
                    alert("등록메뉴가 "+ $("#menu_h_s option:selected").text()+"로 변경되었습니다.\n저장 버튼을 눌러 저장을 해주시기 바랍니다.");
             }else{
                     alert("변경하실 등록부서를 선택하시고 사용해주시기 바랍니다.");
             }
                $(".DeptModal_close").click();
             });


});

function save(param){
    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}
    var sendingData = new FormData();
            /*문서 변경*/
            var title = $("#title").val();
            var content = $("#content").val();
            var doc_ver = $("#doc_ver").text();
            /*부서 변경*/
            var dept_name = $("#dept_name").val();
            var proj_code = $("#proj_code").val();
            /*메뉴 변경*/
            var menu_name = $("#menu").val();
            var menu_code = $("#menu_code").val();
            var seq = param;
            var moduser = $("#emp_no").val();;

//            var doc_dt = $("#doc_dt").val();
//            var trn_case = $("#trn_case").val();
            var doc_month = $("#doc_month").val();
            var doc_turn = $("#doc_turn").val();
            var doc_sdt = $("#doc_sdt").val();
            var doc_edt = $("#doc_edt").val();

            if(title == ""){
                alert("제목을 작성해주시기 바랍니다");
                $("#title").focus();
                return false;
            }

            if(content.length>500){
                alert("내용은 500자 이내로 작성 바랍니다.");
                return false;
            }

            sendingData.append("seq", seq);
            sendingData.append("moduser", moduser);

            sendingData.append("title", title);
            sendingData.append("content",content);
            sendingData.append("doc_ver",doc_ver);

            sendingData.append("menu_code",menu_code);
            sendingData.append("menu_name",menu_name);

            sendingData.append("dept_name",dept_name);
            sendingData.append("proj_code",proj_code);

//            sendingData.append("doc_dt",doc_dt);
//            sendingData.append("trn_case",trn_case);

            sendingData.append("doc_month",doc_month);
            sendingData.append("doc_turn",doc_turn);
            sendingData.append("doc_sdt",doc_sdt);
            sendingData.append("doc_edt",doc_edt);

            $.ajax({
                url:"/modList",
                data:sendingData,
                method:"post",
                dataType:"json",
                processData: false,
                contentType: false,
                beforeSend:function()
                {
                 $(".modBtn").attr("disabled",true);

                },
                success:function(data){
                    if(myDropzone.files==null || myDropzone.files.length<=0){
                        if(data=="1"){
                            alert("수정 완료");
                            opener.parent.location.reload();
                            location.href='/listPopup/'+seq;
                        }else{
                            alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");
                            $(".modBtn").attr("disabled",false);
                        }
                    }else{
                        $("#test2").click();
                    }
                }
            });


}

function save2(param){
    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}

    var sendingData = new FormData();
            /*문서 변경*/
            var title = $("#title").val();
            var content = $("#content").val();
            var doc_ver = $("#doc_ver").text();
            /*부서 변경*/
            var dept_name = $("#dept_name").val();
            var proj_code = $("#proj_code").val();
            /*메뉴 변경*/
            var menu_name = $("#menu").val();
            var menu_code = $("#menu_code").val();
            var seq = param;
            var moduser = $("#emp_no").val();

            var doc_dt = $("#doc_dt").val();
            var trn_case = $("#trn_case1").val();

            if(title == ""){
                alert("제목을 작성해주시기 바랍니다");
                $("#title").focus();
                return false;
            }

            if(content.length>500){
                alert("내용은 500자 이내로 작성 바랍니다.");
                return false;
            }

            sendingData.append("seq", seq);
            sendingData.append("moduser", moduser);

            sendingData.append("title", title);
            sendingData.append("content",content);
            sendingData.append("doc_ver",doc_ver);

            sendingData.append("menu_code",menu_code);
            sendingData.append("menu_name",menu_name);

            sendingData.append("dept_name",dept_name);
            sendingData.append("proj_code",proj_code);

            sendingData.append("doc_dt",doc_dt);
            sendingData.append("trn_case",trn_case);

            $.ajax({
                url:"/modList2",
                data:sendingData,
                method:"post",
                dataType:"json",
                processData: false,
                contentType: false,
                beforeSend:function()
                {
                 $(".modBtn").attr("disabled",true);

                },
                success:function(data){
                    if(myDropzone.files==null || myDropzone.files.length<=0){
                        if(data=="1"){
                            alert("수정 완료");
                            opener.parent.location.reload();
                            location.href='/listPopup2/'+seq;
                        }else{
                            alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");
                            $(".modBtn").attr("disabled",false);
                        }
                    }else{
                        $("#test2").click();
                    }
                }
            });


}

function save_op(param){
    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}



    var sendingData = new FormData();
            /*문서 변경*/
            var title = $("#title").val();
            var content = $("#content").val();
            var doc_ver = $("#doc_ver").text();
            /*부서 변경*/
            var dept_name = $("#dept_name").val();
            var proj_code = $("#proj_code").val();
            /*메뉴 변경*/
            var menu_name = $("#menu").val();
            var menu_code = $("#menu_code").val();
            var seq = param;
            var moduser = $("#emp_no").val();

            var doc_month = $("#doc_month").val();
            var op_num = $("#op_num").val();

            if(title == ""){
                alert("제목을 작성해주시기 바랍니다");
                $("#title").focus();
                return false;
            }

            if(content.length>500){
                alert("내용은 500자 이내로 작성 바랍니다.");
                return false;
            }

            sendingData.append("seq", seq);
            sendingData.append("moduser", moduser);

            sendingData.append("title", title);
            sendingData.append("content",content);
            sendingData.append("doc_ver",doc_ver);

            sendingData.append("menu_code",menu_code);
            sendingData.append("menu_name",menu_name);

            sendingData.append("dept_name",dept_name);
            sendingData.append("proj_code",proj_code);

            sendingData.append("doc_month",doc_month);
            sendingData.append("op_num",op_num);


            $.ajax({
                url:"/modList_op",
                data:sendingData,
                method:"post",
                dataType:"json",
                processData: false,
                contentType: false,
                beforeSend:function()
                {
                 $(".modBtn").attr("disabled",true);

                },
                success:function(data){
                    if(myDropzone.files==null || myDropzone.files.length<=0){
                        if(data=="1"){
                            alert("수정 완료");
                            opener.parent.location.reload();
                            location.href='/listPopup_op/'+seq;
                        }else{
                            alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");
                            $(".modBtn").attr("disabled",false);
                        }
                    }else{
                        $("#test2").click();
                    }
                }
            });


}

function save_im(param){
    if(param==""){alert("세션이 만료되었습니다.다시로그인해주세요");location.href="/";}



    var sendingData = new FormData();
            /*문서 변경*/
            var title = $("#title").val();
            var content = $("#content").val();
            var doc_ver = $("#doc_ver").text();
            /*부서 변경*/
            var dept_name = $("#dept_name").val();
            var proj_code = $("#proj_code").val();
            /*메뉴 변경*/
            var menu_name = $("#menu").val();
            var menu_code = $("#menu_code").val();
            var seq = param;
            var moduser = $("#emp_no").val();

            var doc_dt = $("#doc_dt").val();
            var ck_point = $("#ck_point").val();

            if(title == ""){
                alert("제목을 작성해주시기 바랍니다");
                $("#title").focus();
                return false;
            }

            if(content.length>500){
                alert("내용은 500자 이내로 작성 바랍니다.");
                return false;
            }

            sendingData.append("seq", seq);
            sendingData.append("moduser", moduser);

            sendingData.append("title", title);
            sendingData.append("content",content);
            sendingData.append("doc_ver",doc_ver);

            sendingData.append("menu_code",menu_code);
            sendingData.append("menu_name",menu_name);

            sendingData.append("dept_name",dept_name);
            sendingData.append("proj_code",proj_code);

            sendingData.append("doc_dt",doc_dt);
            sendingData.append("ck_point",ck_point);


            $.ajax({
                url:"/modList_im",
                data:sendingData,
                method:"post",
                dataType:"json",
                processData: false,
                contentType: false,
                beforeSend:function()
                {
                 $(".modBtn").attr("disabled",true);

                },
                success:function(data){
                    if(myDropzone.files==null || myDropzone.files.length<=0){
                        if(data=="1"){
                            alert("수정 완료");
                            opener.parent.location.reload();
                            location.href='/listPopup_im/'+seq;
                        }else{
                            alert("업로드 실패 새로고침 이후 다시 사용해 주세요.");
                            $(".modBtn").attr("disabled",false);
                        }
                    }else{
                        $("#test2").click();
                    }
                }
            });


}



function progress(percent){

    $(".progress").show();
    $("#pro_g").css("width", "calc(" + percent + "% )");
    $(".pro_s").text(percent.toFixed(2)+"%");
}

