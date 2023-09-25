$(function() {



// function write_list() {
//
//        var status2 = "toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,width=800, height=1000, top=0,left=50";
//                  window.open("/write",'팝업',status2);
//
// }
    function startGrid2() {
        //jqGrid setting
        $("#jqgrid_table_main").jqGrid({
            colNames: [
                 "현장코드", "현장명"
            ],
            colModel: [

                {
                    index: 'DEPT_CD',
                    name: 'DEPT_CD',
                    width: 5
                },
                {
                    index: 'DEPT_NAME',
                    name: 'DEPT_NAME',
                    width: 5,
                    align:"center"
                },

            ],
            viewrecords: false // show the current page, data rang and total records on the toolbar
            ,shrinkToFit: true
            ,rowNum: 30
            ,loadonce: false
            ,datatype: "local"
            ,autowidth: true //auto 넓이
            ,height: "85vh" //높이
            ,pager: "#jqGridPager_main" //페이징
            ,sortable: false // 마우스로 행 옮기기
            ,loadtext: "로딩중입니다..."
            ,searchoptions: {
                sopt: ['eq', 'bw', 'bn', 'cn', 'nc', 'ew', 'en']
            }
            ,rownumbers : true
            ,rownumWidth : 50
            ,rowList : [10,20,30,40,50,60,70,80,90,100],
            onCellSelect: function(id, status, e) //행 클릭 function
            {
                //alert('선택된 ROW : '+id);
                $.ajax({
                    url: "/viewList",
                    data: {
                        "id": id
                    },
                    method: "post",
                    success: function(data) {
                        var pathUrl = data;
                        let popUp_option = "width=1000,height=1000,left=20,top=10, toolbar=no,status=no, menubar =no,location=no";
                        window.open("/listPopup/" + pathUrl, "target", popUp_option);

                    }
                })
            }
            ,
            loadError: function(xhr, status, error) // 불러오기 실패했을 경우 발생하는 이벤트
            {
                //xhr : XMLHttpRequest object , status : type of error , error : exception object
                alert("오류가 발생 하였습니다.\n 전산팀에 문의하세요.");
            }
            ,headerrow: true
        });
        //jqGrid setting end

        $("#jqgrid_table_main").jqGrid("setLabel", "rn", "순번");
        //tool bar
        $('#jqgrid_table_main').navGrid("#jqGridPager_main", {
            search: true, // show search button on the toolbar
            add: false,
            edit: false,
            del: false,
            refresh: true
        });
        $('#jqgrid_table_main').jqGrid('filterToolbar');
    }



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
                            $("#jqgrid_table_main").clearGridData(); // clear
                            startGrid2(); //그리드 그리기
                            $("#jqgrid_table_main").jqGrid('setCaption', data[0].MENU_NAME); //caption 동적 변화

                            $("#jqgrid_table_main").jqGrid("setGridParam", {
                                data: data
                            }).trigger("reloadGrid");

                        } else {
                            $("#jqgrid_table_main").clearGridData();
                            startGrid2();
                            $("#jqgrid_table_main").jqGrid('setCaption', '등록된 데이터가 없습니다.');
                        }
                    }
                });
      });
    $(window).on("resize", function () {
        var $grid =  $("#jqgrid_table_main"),
            newWidth = $grid.closest(".ui-jqgrid").parent().width();
            $grid.jqGrid("setGridWidth", newWidth, true);
            var height = $(window).height()-100;
            $('.ui-jqgrid-bdiv').height(height);

    });





});





