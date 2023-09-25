package com.dage.quality.Service;

import com.dage.quality.DAO.CommentDao;
import com.dage.quality.DTO.AuthDto;
import com.dage.quality.DTO.CommentDto;
import com.dage.quality.DTO.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao dao;

    public void com_write(@Param("list_seq")String list_seq
            ,@Param("user_nm")String user_nm
            ,@Param("user_no")String user_no
            ,@Param("empno")String empno
            ,@Param("qul_com")String qul_com
            ,@Param("position")String position
            ,@Param("dept_name")String dept_name
            ,@Param("rmk")String rmk)  {
        dao.com_write(list_seq,user_nm,user_no,empno,qul_com,position,dept_name,rmk);
    }

    public List<CommentDto> comment_list(@Param("list_seq")String list_seq){
        return dao.comment_list(list_seq);
    }

    public void delcomm(@Param("seq")String seq,
                        @Param("list_seq")String list_seq
    ){
        dao.delcomm(seq,list_seq);
    }

}
