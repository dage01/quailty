package com.dage.quality.Service;

import com.dage.quality.DAO.ListDao;
import com.dage.quality.DTO.CommentDto;
import com.dage.quality.DTO.ListDto;
import com.dage.quality.DTO.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ListService {
    @Autowired
    ListDao dao;
    public void write
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

            ){
         dao.write(menu_code,menu_code_s,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_month,doc_turn,doc_sdt,doc_edt);
    }

    public void write2
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
            ){
        dao.write2(menu_code,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_dt,trn_case);
    }

    public void write_op
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
            ){
        dao.write_op(menu_code,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_month,op_num);
    }

    public void write_im
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
                    ,@Param("ck_point") String ck_point
            ){
        dao.write_im(menu_code,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_dt,ck_point);
    }

    public void mod
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
                    ,@Param("doc_month")String doc_month
                    ,@Param("doc_turn")String doc_turn
                    ,@Param("doc_sdt")String doc_sdt
                    ,@Param("doc_edt")String doc_edt
            ){
         dao.mod(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_month,doc_turn,doc_sdt,doc_edt);
    }

    public void mod2
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
                    ,@Param("doc_dt")String doc_dt
                    ,@Param("trn_case")String trn_case
            ){
        dao.mod2(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_dt,trn_case);
    }

    public void mod_op
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
                    ,@Param("doc_month")String doc_month
                    ,@Param("op_num")String op_num
            ){
        dao.mod_op(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_month,op_num);
    }

    public void mod_im
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
                    ,@Param("doc_dt")String doc_dt
                    ,@Param("ck_point")String ck_point
            ){
        dao.mod_im(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_dt,ck_point);
    }

    public void cmt
            (
                    @Param("cmt1")String cmt1
                    ,@Param("cmt2")String cmt2
                    ,@Param("cmt3")String cmt3
                    ,@Param("cmt4")String cmt4
                    ,@Param("cmt5")String cmt5
            ){
        dao.cmt(cmt1, cmt2, cmt3, cmt4, cmt5);
    }

    public ListDto list_info(@Param("seq")String seq){
        return dao.list_info(seq);
    }

    public List<ListDto> dept_list(@Param("proj_code")String proj_code){
        return dao.dept_list(proj_code);
    }

    public List<ListDto> list_detail(@Param("seq")String seq) { return dao.list_detail(seq); }

    public List<ListDto> proj_list(){
        return dao.proj_list();
    }

    public ListDto cmt_select(){ return dao.cmt_select();}
    public String getFinalSeq(@Param("user_no")String user_no){ return dao.getFinalSeq(user_no);}

    public void updateFileTag(@Param("seq")String seq){dao.updateFileTag(seq);}

    public void delete_list(@Param("seq")String seq){dao.delete_list(seq);}
}
