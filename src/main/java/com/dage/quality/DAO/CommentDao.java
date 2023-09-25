package com.dage.quality.DAO;

import com.dage.quality.DTO.CommentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<CommentDto> comment_list(@Param("list_seq")String list_seq);

    void com_write(
            @Param("list_seq")String list_seq
            ,@Param("user_nm")String user_nm
            ,@Param("user_no")String user_no
            ,@Param("empno")String empno
            ,@Param("qul_com")String qul_com
            ,@Param("position")String position
            ,@Param("dept_name")String dept_name
            ,@Param("rmk")String rmk
    );


    void delcomm(@Param("seq")String seq,@Param("list_seq")String list_seq);

}
