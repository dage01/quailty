package com.dage.quality.DAO;

import com.dage.quality.DTO.CommentDto;
import com.dage.quality.DTO.ListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListDao {
    void write
            (
            @Param("menu_code")String menu_code
            ,@Param("menu_code_s")String menu_code_s
            ,@Param("title")String title
            ,@Param("name")String name
            ,@Param("dept_name")String dept_name
            ,@Param("proj_code")String proj_code
            ,@Param("content")String content
            ,@Param("menu_name")String menu_name
            ,@Param("position")String position
            ,@Param("user_no") String user_no
            ,@Param("emp_no") String emp_no
            ,@Param("doc_month") String doc_month
            ,@Param("doc_turn") String doc_turn
            ,@Param("doc_sdt") String doc_sdt
            ,@Param("doc_edt") String doc_edt

            );

    void write2
            (
                    @Param("menu_code")String menu_code
                    ,@Param("title")String title
                    ,@Param("name")String name
                    ,@Param("dept_name")String dept_name
                    ,@Param("proj_code")String proj_code
                    ,@Param("content")String content
                    ,@Param("menu_name")String menu_name
                    ,@Param("position")String position
                    ,@Param("user_no") String user_no
                    ,@Param("emp_no") String emp_no
                    ,@Param("doc_dt") String doc_dt
                    ,@Param("trn_case") String trn_case
            );

    void write_op
            (
                    @Param("menu_code")String menu_code
                    ,@Param("title")String title
                    ,@Param("name")String name
                    ,@Param("dept_name")String dept_name
                    ,@Param("proj_code")String proj_code
                    ,@Param("content")String content
                    ,@Param("menu_name")String menu_name
                    ,@Param("position")String position
                    ,@Param("user_no") String user_no
                    ,@Param("emp_no") String emp_no
                    ,@Param("doc_month") String doc_month
                    ,@Param("op_num") String op_num
            );

    void write_im
            (
                    @Param("menu_code")String menu_code
                    ,@Param("title")String title
                    ,@Param("name")String name
                    ,@Param("dept_name")String dept_name
                    ,@Param("proj_code")String proj_code
                    ,@Param("content")String content
                    ,@Param("menu_name")String menu_name
                    ,@Param("position")String position
                    ,@Param("user_no")String user_no
                    ,@Param("emp_no")String emp_no
                    ,@Param("doc_dt")String doc_dt
                    ,@Param("ck_point")String ck_point
            );

    void mod
            (
                @Param("menu_code")String menu_code
                ,@Param("menu_name")String menu_name
                ,@Param("title")String title
                ,@Param("content")String content
                ,@Param("doc_ver") String doc_ver
                ,@Param("moduser") String moduser
                ,@Param("dept_name")String dept_name
                ,@Param("proj_code")String proj_code
                ,@Param("seq")String seq
                ,@Param("doc_month") String doc_month
                ,@Param("doc_turn") String doc_turn
                ,@Param("doc_sdt") String doc_sdt
                ,@Param("doc_edt") String doc_edt

            );
    void mod2
            (
                    @Param("menu_code")String menu_code
                    ,@Param("menu_name")String menu_name
                    ,@Param("title")String title
                    ,@Param("content")String content
                    ,@Param("doc_ver") String doc_ver
                    ,@Param("moduser") String moduser
                    ,@Param("dept_name")String dept_name
                    ,@Param("proj_code")String proj_code
                    ,@Param("seq")String seq
                    ,@Param("doc_dt") String doc_dt
                    ,@Param("trn_case") String trn_case

            );

    void mod_op
            (
                    @Param("menu_code")String menu_code
                    ,@Param("menu_name")String menu_name
                    ,@Param("title")String title
                    ,@Param("content")String content
                    ,@Param("doc_ver") String doc_ver
                    ,@Param("moduser") String moduser
                    ,@Param("dept_name")String dept_name
                    ,@Param("proj_code")String proj_code
                    ,@Param("seq")String seq
                    ,@Param("doc_month") String doc_month
                    ,@Param("op_num") String op_num

            );

    void mod_im
            (
                    @Param("menu_code")String menu_code
                    ,@Param("menu_name")String menu_name
                    ,@Param("title")String title
                    ,@Param("content")String content
                    ,@Param("doc_ver") String doc_ver
                    ,@Param("moduser") String moduser
                    ,@Param("dept_name")String dept_name
                    ,@Param("proj_code")String proj_code
                    ,@Param("seq")String seq
                    ,@Param("doc_dt") String doc_dt
                    ,@Param("ck_point") String ck_point

            );

    void cmt
            (
                    @Param("cmt1")String cmt1
                    ,@Param("cmt2")String cmt2
                    ,@Param("cmt3")String cmt3
                    ,@Param("cmt4")String cmt4
                    ,@Param("cmt5")String cmt5
            );
    ListDto list_info(@Param("seq")String seq);

    List<ListDto> dept_list(@Param("proj_code")String proj_code);

    List<ListDto> list_detail(@Param("seq")String seq);

    List<ListDto> proj_list();

    ListDto cmt_select();
    String getFinalSeq(@Param("user_no")String user_no);
    void updateFileTag(@Param("seq")String seq);
    void delete_list(@Param("seq")String seq);
}
