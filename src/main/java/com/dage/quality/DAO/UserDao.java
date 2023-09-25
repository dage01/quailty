package com.dage.quality.DAO;


import com.dage.quality.DTO.ListDto;
import com.dage.quality.DTO.UserDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
    UserDto chK_logIn(@Param("user_id")String user_id, @Param("user_pwd")String user_pwd); //유저 정보
    List<UserDto>get_sub_name(@Param("emp_no")String emp_no); //겸직 리스트
    List<UserDto> user_list();
    void write(
            @Param("title")String title
            ,@Param("name")String name
            ,@Param("dept_name")String dept_name
            ,@Param("content")String content
            ,@Param("proj_code")String proj_code
              );
    //메뉴리스트
    List<UserDto>menu_list_h();

    List<UserDto>trn_list();
    List<UserDto>menu_list();
    List<UserDto>menu_list_s();
    List<UserDto>menu_list_noAdmin(@Param("empno")String empno,@Param("proj_code")String proj_code);
    List<UserDto>get_s_menu(@Param("p_menu_code")String p_menu_code);
    List<UserDto> get_all();
    //모든 리스트
    List<ListDto>list();

    List<ListDto>list_search(@Param("search_txt")String search_txt);

    //부서별 리스트
    List<ListDto>list_proj(@Param("empno")String empno,@Param("proj_code")String proj_code);

    //메뉴별 리스트 전체목록
    List<ListDto>list_state(@Param("menu_code")String menu_code);
    //메뉴별 리스트 부서별
    List<ListDto>list_state_proj(@Param("empno")String empno,@Param("proj_code")String proj_code,@Param("menu_code")String menu_code);


    //메뉴 관리
    void addFolder(@Param("name")String name,@Param("user_no")String user_no,@Param("P_MENU_CODE")String P_MENU_CODE);

    void sub_folder_d(@Param("menu_code")String menu_code);
    void main_folder_d(@Param("menu_code")String menu_code);
    void main_folder_m(@Param("menu_code")String menu_code,@Param("name")String name);
    String chk_folder_name (@Param("name")String name);

    public int chk_folder_data_h (@Param("menu_code")String menu_code);
    public int chk_folder_data_s (@Param("menu_code")String menu_code);

    String get_menu_name(@Param("menu_code")String menu_code);

    String get_cmt_name(@Param("menu_code_s")String menu_code_s);

}

