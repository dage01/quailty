



$(function(){
    $("#jqgrid_table").jqGrid({
            colNames: [
                "문서번호","댓글수","제목","등록일","현장명","작성자","직위","현장코드" ,"보관함", "문서버전", "메뉴코드"
            ],
            colModel: [
                {
                    index: 'SEQ',
                    name: 'SEQ',
                    width: 2,
                    hidden: false,
                    key: true,
                    align:"center"
                },
                {
                    index: 'CMT',
                    name: 'CMT',
                    width: 2
                },

                {
                    index: 'TITLE',
                    name: 'TITLE',
                    width: 16
                },
                {
                    index: 'CRTDATE',
                    name: 'CRTDATE',
                    width: 3,
                    formatter: "date",
                    formatoptions: {
                        newformat: " Y/m/d"
                    }
                },
                {
                    index: 'DEPT_NAME',
                    name: 'DEPT_NAME',
                    width: 8
                },
                {
                    index: 'USER_NM',
                    name: 'USER_NM',
                    width: 3,
                    align:"center"
                },
                {
                    index: 'POSITION',
                    name: 'POSITION',
                    width: 5,
                    align:"center",
                    hidden:true
                },
                {
                    index: 'PROJ_CODE',
                    name: 'PROJ_CODE',
                    width: 5,
                    align:"center",
                    hidden:true
                },
                {
                    index: 'MENU_NAME',
                    name: 'MENU_NAME',
                    width: 5
                },

                {
                    index: 'DOC_VER',
                    name: 'DOC_VER',
                    width: 2
                },

                {
                    index: 'MENU_CODE',
                    name: 'MENU_CODE',
                    width: 2,
                    hidden:true
                },



            ],
            viewrecords: false // show the current page, data rang and total records on the toolbar
            ,shrinkToFit: true
            ,rowNum: 30
            ,loadonce: false
            ,data:data
            ,datatype: "local"
            ,autowidth: true //auto 넓이
            ,height: "85vh" //높이
            ,pager: "#jqGridPager" //페이징
            ,sortable: false // 마우스로 행 옮기기
            ,loadtext: "로딩중입니다..."
            ,caption: "DONG-AH SAFETY"
            ,searchoptions: {
                sopt: ['eq', 'bw', 'bn', 'cn', 'nc', 'ew', 'en']
            }
            ,rownumbers : true
            ,rownumWidth : 50
            ,rowList : [10,20,30,40,50,60,70,80,90,100]

            ,onCellSelect: function(id, status, e) //행 클릭 function
            {
                //alert('선택된 ROW : '+id);
                var selected = jQuery("#jqgrid_table").getRowData(id);

                $.ajax({
                    url: "/viewList",
                    data:
                    {
                        "id": id
                    },
                    method: "post",
                    success: function(data)
                        {
                                var pathUrl = id;
                                let popUp_option = "width=1300,height=1000,left=20,top=10, toolbar=no,status=no, menubar =no,location=no";

                            if (selected.MENU_CODE == "0011") {
                                window.open("/listPopup2/" + pathUrl, "target", popUp_option);
                                return;
                            }
                            if (selected.MENU_CODE == "0013") {
                                window.open("/listPopup_op/" + pathUrl, "target", popUp_option);
                                return;
                            }
                            if (selected.MENU_CODE == "0014") {
                                window.open("/listPopup_im/" + pathUrl, "target", popUp_option);
                                return;
                            } else {
                             window.open("/listPopup/" + pathUrl, "target", popUp_option);
                           }


                        }
                });
            },
            loadError: function(xhr, status, error) // 불러오기 실패했을 경우 발생하는 이벤트
            {
                //xhr : XMLHttpRequest object , status : type of error , error : exception object
                alert("오류가 발생 하였습니다.\n 전산팀에 문의하세요.");
            }
    });


       $("#jqgrid_table").jqGrid("setLabel", "rn", "순번");
       $('#jqgrid_table').jqGrid('filterToolbar');
       $('#jqgrid_table').navGrid("#jqGridPager", {
           search: true, // show search button on the toolbar
           add: false,
           edit: false,
           del: false,
           refresh: true
       });

       $(window).on("resize", function () {
           var $grid =  $("#jqgrid_table"),
               newWidth = $grid.closest(".ui-jqgrid").parent().width();
               newHeight = $grid.closest(".ui-jqgrid").parent().height();
   //        console.log(newHeight);
           $grid.jqGrid("setGridWidth", newWidth, true);
           var height = $(window).height()-50;

           console.log(height);
           $('.ui-jqgrid-bdiv').height(height);
       });
   })
