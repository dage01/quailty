package com.dage.quality.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    //유저정보
    private String user_id;
    private String user_pwd;
    private String user_nm;
    private String user_no;
    private String empno_d;
    private String empno;
    private String dept_name;
    private String dept_code;
    private String position;
    private String sub_dept_name;
    private String proj_code;
    private String admin;
    private int rownum;

    //메뉴 리스트
    private String search_txt;
    private String menu_code;
    private String cmt1;
    private String cmt2;
    private String cmt3;
    private String cmt4;
    private String cmt5;
    private String name;
    private String crtuserno;
    private String moduserno;
    private String crtdate;
    private String p_menu_code;
    private int level;
    private int cnt;
    private String child_tg;
    private String trn_case;

    //변수

    private String gender;


}
