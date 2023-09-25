package com.dage.quality.Service;


import com.dage.quality.DAO.MenuDao;
import com.dage.quality.DAO.UserDao;
import com.dage.quality.DTO.ListDto;
import com.dage.quality.DTO.MenuDto;
import com.dage.quality.DTO.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    MenuDao menuDao;
    //유저정보
    public UserDto chK_logIn(@Param("user_id")String user_id, @Param("user_pwd")String user_pwd){
        return userDao.chK_logIn(user_id,user_pwd);
    }
    //겸직 리스트
    public List<UserDto> get_sub_name(@Param("emp_no")String emp_no){
        return userDao.get_sub_name(emp_no);
    }
    public List<UserDto> user_list(){
        return userDao.user_list();
    }
    //메뉴 리스트


    public List<UserDto> trn_list(){ return userDao.trn_list(); }
    public List<UserDto> menu_list_h(){
        return userDao.menu_list_h();
    }
    public List<UserDto> menu_list(){
        return userDao.menu_list();
    }
    public List<UserDto> menu_list_s(){
        return userDao.menu_list_s();
    }
    public List<UserDto> menu_list_noAdmin(@Param("empno")String empno,@Param("proj_code")String proj_code){
        return userDao.menu_list_noAdmin(empno,proj_code);
    }
    public List<UserDto> get_s_menu(@Param("p_menu_code")String p_menu_code){
        return userDao.get_s_menu(p_menu_code);
    }




    //리스트

    public List<ListDto> list(){
        return userDao.list();
    }

    public List<ListDto> list_search(@Param("search_txt")String search_txt){
        return userDao.list_search(search_txt);
    }
    //부서별 리스트
    public List<ListDto>list_proj(@Param("empno")String empno,@Param("proj_code")String proj_code){return userDao.list_proj(empno,proj_code);}

    //리스트
    public List<ListDto> list_state(@Param("menu_list")String menu_list){
        return userDao.list_state(menu_list);
    }
    public List<ListDto> list_state_proj(@Param("empno")String empno,@Param("proj_code")String proj_code,@Param("menu_list")String menu_list){
        return userDao.list_state_proj(empno,proj_code,menu_list);
    }
    /* 폴더관리 */
    //추가
    public void addFolder(@Param("name")String name,@Param("user_no")String user_no,@Param("P_MENU_CODE")String P_MENU_CODE){
        userDao.addFolder(name,user_no,P_MENU_CODE);
    }
    //삭제
    public void sub_folder_d(@Param("menu_code")String menu_code){
        userDao.sub_folder_d(menu_code);
    }
    public void main_folder_d(@Param("menu_code")String menu_code){
        userDao.main_folder_d(menu_code);
    }
    //변경
    public void main_folder_m(@Param("menu_code")String menu_code,@Param("name")String name){userDao.main_folder_m(menu_code,name);}
    /*폴더관리 끝*/

    /*추가 삭제 및 관련 함수 ex)중복이름,삭제 시 폴더안의 데이터 유무*/

    public String chk_folder_name (@Param("name")String name){
        return userDao.chk_folder_name(name);
    }
    public int chk_folder_data_h (@Param("menu_code")String menu_code){
        return userDao.chk_folder_data_h(menu_code);
    }
    public int chk_folder_data_s (@Param("menu_code")String menu_code){
        return userDao.chk_folder_data_s(menu_code);
    }



    public String get_menu_name(@Param("menu_code")String menu_code){
     return userDao.get_menu_name(menu_code);
    }

    public String get_cmt_name(@Param("menu_code_s")String menu_code_s){
        return userDao.get_cmt_name(menu_code_s);
    }
}
