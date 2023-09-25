



$(function(){
    $("#jqgrid_table_main").jqGrid({
            colNames: [
                "현장코드","현장명"
            ],
            colModel: [

                 {
                                    index: 'proj_code',
                                    name: 'proj_code',
                                    width: 1,
                                    hidden: false,
                                    key: true,
                                    align:"center"
                                },
                                {
                                    index: 'dept_name',
                                    name: 'dept_name',
                                    width: 2
                                },




            ],
            viewrecords: false // show the current page, data rang and total records on the toolbar
            ,shrinkToFit: true
            ,rowNum: 200
            ,loadonce: false
            ,data:data
            ,datatype: "local"
            ,autowidth: true //auto 넓이
            ,height: "85vh" //높이
            ,pager: "#jqGridPager_main" //페이징
            ,sortable: false // 마우스로 행 옮기기
            ,loadtext: "로딩중입니다..."
            ,caption: "현장리스트"
            ,searchoptions: {
                sopt: ['eq', 'bw', 'bn', 'cn', 'nc', 'ew', 'en']
            }
            ,rownumbers : true
            ,rownumWidth : 50
            ,rowList : [10,20,30,40,50,60,70,80,90,100]

            ,onCellSelect: function(id, status, e) //행 클릭 function
            {
                //alert('선택된 ROW : '+id);
                var selected = $('#jqgrid_table_main').getCell(id, 'proj_code');
                //jQuery("#jqgrid_table_main").getRowData(id);


                //alert(selected);
                $.ajax({

                   url: "/list_detail",
                   data: {
                       "id": id
                   },
                   method: "post",
                   success: function(data) {
                   $("#jqgrid_table_main2").clearGridData();
                        $("#jqgrid_table_main2").jqGrid("setGridParam", {
                                                    id: id
                                                }).trigger("reloadGrid");
                    $('#jqgrid_table_main2').trigger('reloadGrid');
                   }

                });
            },
            loadError: function(xhr, status, error) // 불러오기 실패했을 경우 발생하는 이벤트
            {
                //xhr : XMLHttpRequest object , status : type of error , error : exception object
                alert("오류가 발생 하였습니다.\n 전산팀에 문의하세요.");
            }
    });

    $("#jqgrid_table_main2").jqGrid({
                colNames: [
                    "1회차","2회차"
                ],
                colModel: [


                                    {
                                        index: 'date_yn',
                                        name: 'date_yn',
                                        width: 2
                                    },
                                    {
                                        index: 'date_yn2',
                                        name: 'date_yn2',
                                        width: 2
                                    },




                ],
                viewrecords: false // show the current page, data rang and total records on the toolbar
                ,shrinkToFit: true
                ,rowNum: 200
                ,loadonce: false
                ,data:data
                ,datatype: "local"
                ,autowidth: true //auto 넓이
                ,height: "85vh" //높이
                ,pager: "#jqGridPager_main2" //페이징
                ,sortable: false // 마우스로 행 옮기기
                ,loadtext: "로딩중입니다..."
                ,caption: "수시위험성평가"
                ,searchoptions: {
                    sopt: ['eq', 'bw', 'bn', 'cn', 'nc', 'ew', 'en']
                }
                ,rownumbers : false
                ,rownumWidth : 50
                ,rowList : [10,20,30,40,50,60,70,80,90,100]

                ,onCellSelect: function(id, status, e) //행 클릭 function
                {
                    //alert('선택된 ROW : '+id);


                    $.ajax({

                    });
                },
                loadError: function(xhr, status, error) // 불러오기 실패했을 경우 발생하는 이벤트
                {
                    //xhr : XMLHttpRequest object , status : type of error , error : exception object
                    alert("오류가 발생 하였습니다.\n 전산팀에 문의하세요.");
                }
        });


    $("#jqgrid_table_main3").jqGrid({
                    colNames: [
                        "작성유무"
                    ],
                    colModel: [


                                        {
                                            index: 'date_yn3',
                                            name: 'date_yn3',
                                            width: 2
                                        },




                    ],
                    viewrecords: false // show the current page, data rang and total records on the toolbar
                    ,shrinkToFit: true
                    ,rowNum: 200
                    ,loadonce: false
                    ,data:data
                    ,datatype: "local"
                    ,autowidth: true //auto 넓이
                    ,height: "85vh" //높이
                    ,pager: "#jqgrid_table_main3" //페이징
                    ,sortable: false // 마우스로 행 옮기기
                    ,loadtext: "로딩중입니다..."
                    ,caption: "비상대응훈련"
                    ,searchoptions: {
                        sopt: ['eq', 'bw', 'bn', 'cn', 'nc', 'ew', 'en']
                    }
                    ,rownumbers : false
                    ,rownumWidth : 50
                    ,rowList : [10,20,30,40,50,60,70,80,90,100]

                    ,onCellSelect: function(id, status, e) //행 클릭 function
                    {
                        //alert('선택된 ROW : '+id);
                        var selected = jQuery("#jqgrid_table_main3").getRowData(id);

                        $.ajax({

                        });
                    },
                    loadError: function(xhr, status, error) // 불러오기 실패했을 경우 발생하는 이벤트
                    {
                        //xhr : XMLHttpRequest object , status : type of error , error : exception object
                        alert("오류가 발생 하였습니다.\n 전산팀에 문의하세요.");
                    }
            });

    $("#jqgrid_table_main").jqGrid("setLabel", "rn", "순번");
    $('#jqgrid_table_main').jqGrid('filterToolbar');
    $('#jqgrid_table_main').navGrid("#jqGridPager_main", {
        search: true, // show search button on the toolbar
        add: false,
        edit: false,
        del: false,
        refresh: true
    });
    $('#jqgrid_table_main2').jqGrid('filterToolbar');
    $('#jqgrid_table_main2').navGrid("#jqGridPager_main", {
        search: true, // show search button on the toolbar
        add: false,
        edit: false,
        del: false,
        refresh: true
    });

    $("#jqgrid_table_main3").jqGrid("setLabel", "rn", "순번");
    $('#jqgrid_table_main3').jqGrid('filterToolbar');
    $('#jqgrid_table_main3').navGrid("#jqGridPager_main", {
        search: true, // show search button on the toolbar
        add: false,
        edit: false,
        del: false,
        refresh: true
    });



    $(window).on("resize", function () {
        var $grid =  $("#jqgrid_table_main"),
            newWidth = $grid.closest(".ui-jqgrid").parent().width();
            newHeight = $grid.closest(".ui-jqgrid").parent().height();
//        console.log(newHeight);
        $grid.jqGrid("setGridWidth", newWidth, true);
        var height = $(window).height()-50;

        console.log(height);
        $('.ui-jqgrid-bdiv').height(height);
    });




    $(window).on("resize", function () {
        var $grid =  $("#jqgrid_table_main2"),
            newWidth = $grid.closest(".ui-jqgrid").parent().width();
            newHeight = $grid.closest(".ui-jqgrid").parent().height();
    //    console.log(newHeight);
        $grid.jqGrid("setGridWidth", newWidth, true);
        var height = $(window).height()-50;

        console.log(height);
        $('.ui-jqgrid-bdiv').height(height);
    });

    $(window).on("resize", function () {
        var $grid =  $("#jqgrid_table_main3"),
            newWidth = $grid.closest(".ui-jqgrid").parent().width();
            newHeight = $grid.closest(".ui-jqgrid").parent().height();
//        console.log(newHeight);
        $grid.jqGrid("setGridWidth", newWidth, true);
        var height = $(window).height()-50;

        console.log(height);
        $('.ui-jqgrid-bdiv').height(height);
    });

})
