package com.dage.quality.Controller;


import com.dage.quality.DAO.authDAO;
import com.dage.quality.DTO.*;
import com.dage.quality.Service.*;

import com.google.gson.JsonObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    ListService listService;
    @Autowired
    FileService fileService;
    @Autowired
    AuthService authService;
    @Autowired
    MenuService menuService;
    @GetMapping("/")
    public String home() {

        return "index";
    }
    @GetMapping("/ie")
    public String ie() {

        return "ie";
    }

    @RequestMapping("/cmt_list")
    public String test(Model model,HttpServletRequest request){

        String menu_code_s = request.getParameter("menu_code_s");
        System.out.println(menu_code_s);
        ListDto cmt = listService.cmt_select();
        //model.addAttribute("cmt",listService.cmt_select(menu_code_s));

        return "test";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        if(session!=null){
            session.invalidate();
        }
        return "index";
    }

    @RequestMapping("/getMenuData")
    @ResponseBody
    public List<MenuDto> getMenuData(HttpSession session,Model model){
        model.addAttribute("data",menuService.test());
        List<MenuDto> menu = null;
        menu = menuService.test();
        return menu ;
    }

    @RequestMapping("/login_chk")
    @ResponseBody
    public int chK_logIn(HttpServletRequest request) {
        String user_id = (String) request.getParameter("id");
        String user_pwd = (String) request.getParameter("pwd");
        HttpSession session;

        UserDto dto = userService.chK_logIn(user_id, user_pwd);
        if (dto != null) {
//            System.out.println(dto);
            session = request.getSession();//세션 생성

            session.setAttribute("dto", dto); //세션에 유저 정보 등록
            String empno = dto.getEmpno();

            session.setAttribute("auth",authService.auth_s(empno));
            return 1; //로그인 성공
        } else {
            return -1; // 로그인 실패
        }
    }

    /*2023 04 14 수정
    * 기존 상위 Level1 , 하위 LEVEL2에서
    * 트리 형식으로 LEVEL 계속 생성 가능
    * 변경 내용 -> MENU_CODE를 받아 해당 MENU_CODE의 자식 메뉴로 생성
    * menu name 들고오는 메소드 만들어서 메뉴이름 가져오기
    * menu code 와 menu name 을 view로 전달
    *  */
    @GetMapping(value = {"/fragments/addModal/{MENU_CODE}", "/fragments/addModal"})
    public String openModal(HttpSession session, Model model, @PathVariable(required = false) String MENU_CODE ) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        model.addAttribute("MENU_CODE", MENU_CODE);


        model.addAttribute("MENU_NAME", userService.get_menu_name(MENU_CODE));
        return "Modal/addModal";
    }

    @GetMapping(value = {"/fragments/deleteModal/{MENU_CODE}", "/fragments/deleteModal"})
    public String deleteModal(HttpSession session, Model model, @PathVariable(required = false) String MENU_CODE) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
//        List<UserDto> menu_list_h = null;
//        List<UserDto> menu_list_s = null;
//        if (userService.menu_list_h() != null) {
//            menu_list_h = userService.menu_list_h();
//            menu_list_s = userService.menu_list_s();
//        }
        model.addAttribute("MENU_CODE", MENU_CODE);
//        model.addAttribute("menu_list_h", menu_list_h);
//        model.addAttribute("menu_list_s", menu_list_s);
        return "Modal/deleteModal";
    }
    @GetMapping(value = {"/Modal/SelectModal/{MENU_CODE}", "/Modal/SelectModal"})
    public String SelectModal(HttpSession session, Model model, @PathVariable(required = false) String MENU_CODE) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        List<UserDto> menu_list_h = null;
        List<UserDto> menu_list_s = null;
        if (userService.menu_list_h() != null) {
            menu_list_h = userService.menu_list_h();
            menu_list_s = userService.menu_list_s();
        }
        model.addAttribute("MENU_CODE", MENU_CODE);
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);
        return "Modal/SelectModal";
    }

    @GetMapping(value = {"/fragments/modModal/{MENU_CODE}", "/fragments/modModal"})
    public String modModal(HttpSession session, Model model, @PathVariable(required = false) String MENU_CODE) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }

        model.addAttribute("MENU_CODE", MENU_CODE);
        model.addAttribute("MENU_NAME", userService.get_menu_name(MENU_CODE));
        return "Modal/modModal";
    }
    @RequestMapping("/fragments/authModal")
    public String authModal(HttpSession session, Model model) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        List<UserDto> menu_list_h = null;
        List<UserDto> menu_list_s = null;
        List<UserDto> user_list = userService.user_list();
        if (userService.menu_list_h() != null) {
            menu_list_h = userService.menu_list_h();
            menu_list_s = userService.menu_list_s();
        }
        model.addAttribute("auth_list",authService.auth_all());
        model.addAttribute("user_list",user_list);

        return "Modal/authModal";
    }
    @RequestMapping("/delAuth")
    @ResponseBody
    public String delAuth(HttpServletRequest request){
        String empno ="";
        empno = request.getParameter("empno");
        try {
            authService.delAuth(empno);
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }
    @RequestMapping("/addAuth")
    @ResponseBody
    public String addAuth(HttpServletRequest request,
                          @RequestParam(value = "empno[]",required = false)String[]empno){
        String result = "";

        String addFolder = (String)request.getParameter("addFolder");
        String modFolder = (String)request.getParameter("modFolder");
        String delFolder = (String)request.getParameter("delFolder");
        String doc_tg = (String)request.getParameter("doc_tg");
        String admin_tg = (String)request.getParameter("auth_tg");

        for (int i = 0; i <empno.length ; i++) {
            if(authService.auth_s(empno[i])!=null){
                return "-2";
            }else{

                authService.addAuth(empno[i],admin_tg,addFolder,modFolder,delFolder,doc_tg);
            }
        }
        return "1";

    }




    @RequestMapping("/main")
    public String main(HttpSession session,HttpServletRequest request, Model model,@RequestParam(required = false, value = "search_txt")String search_txt) {

        // session.setMaxInactiveInterval(3600); // 세션 체크

        if (session.getAttribute("dto") == null) {
            return "index"; //세션 없으면 로그인 페이지.
        }

//        UserDto dto = (UserDto) session.getAttribute("dto"); //로그인 정보
        UserDto dto = (UserDto) session.getAttribute("dto");
        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");
        List<UserDto> menu_list_h = userService.menu_list_h();
        List<UserDto> trn_list = userService.trn_list();
        List<UserDto> menu_list_s ;

        List<ListDto> board_list ;




        board_list = userService.list();
        model.addAttribute("trn_list",trn_list);
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list", userService.menu_list());
        model.addAttribute("data",menuService.test());
        model.addAttribute("chk_id",menuService.chk_id());
        model.addAttribute("board_list", board_list);



        return "main";
    }

    @RequestMapping("/list_search")
    public String list_search(HttpSession session,HttpServletRequest request, Model model) {
        if (session.getAttribute("dto") == null) {
            return "index"; //세션 없으면 로그인 페이지.
        }


        String search_txt = request.getParameter("search_txt");

//        UserDto dto = (UserDto) session.getAttribute("dto"); //로그인 정보
        UserDto dto = (UserDto) session.getAttribute("dto");
        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");
        List<UserDto> menu_list_h = userService.menu_list_h();
        List<UserDto> trn_list = userService.trn_list();
        List<UserDto> menu_list_s ;

        List<ListDto> board_list ;




        board_list = userService.list_search(search_txt);
        model.addAttribute("trn_list",trn_list);
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list", userService.menu_list());
        model.addAttribute("data",menuService.test());
        model.addAttribute("chk_id",menuService.chk_id());
        model.addAttribute("board_list", board_list);



        return "main";
    }

    @RequestMapping("/main2")
    public String main2(HttpSession session, Model model,HttpServletRequest request) {
        if (session.getAttribute("dto") == null) {
            return "index"; //세션 없으면 로그인 페이지.
        }



        String seq = request.getParameter("id");

        UserDto dto = (UserDto) session.getAttribute("dto");
        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");

        List<ListDto> proj_list = listService.proj_list();



        String search_txt = "";
        List<UserDto> menu_list_h = userService.menu_list_h();

        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list", userService.menu_list());
        model.addAttribute("data",menuService.test());

        model.addAttribute("proj_list", proj_list);




        return "main2";
//        return "toastTest";
    }

    @RequestMapping("/addFolder")
    @ResponseBody
    public String addFolder(HttpSession session, HttpServletRequest request) {
        if (userService.chk_folder_name(request.getParameter("name")) == null) {
            try {
                userService.addFolder(request.getParameter("name"), request.getParameter("user_no"), request.getParameter("P_MENU_CODE"));
                return "1";
            } catch (Exception e) {
                e.printStackTrace();
                return "-1";
            }
        }
        return "-2";
    }

    @RequestMapping("/deleteFolder/{MENU_CODE}")
    @ResponseBody
    public String deleteFolder(HttpSession session, HttpServletRequest request, @PathVariable("MENU_CODE") String menu_code) {

        try {
            userService.main_folder_d(menu_code);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    @RequestMapping("/deleteCkFolder/{MENU_CODE}")
    @ResponseBody
    public int deleteCkFolder(HttpSession session, HttpServletRequest request, @PathVariable("MENU_CODE") String menu_code) {
        int result = 0;
        try {
            result = userService.chk_folder_data_h(menu_code);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    @RequestMapping("/modFolder")
    @ResponseBody
    public String modFolder(HttpSession session, HttpServletRequest request) {
        try {
            userService.main_folder_m(request.getParameter("menu_code"), request.getParameter("name"));

            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    @RequestMapping("/get_s_menu")
    @ResponseBody
    public List<UserDto> get_s_menu(HttpSession session, HttpServletRequest request) {
        try {
            userService.get_s_menu(request.getParameter("p_menu_code"));
            return userService.get_s_menu(request.getParameter("p_menu_code"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/modPage/{param}")
    public String modifyPage(@PathVariable("param") String param, Model model, HttpSession session) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        ListDto info = listService.list_info(param);
        model.addAttribute("info", info);
        String file_tag = info.getFile_tag();
        UserDto dto = (UserDto) session.getAttribute("dto");

        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);

        List<UserDto> menu_list_h = userService.menu_list_h();
        List<UserDto> menu_list_s = userService.menu_list_s();

        if (sub_emp_list.size() > 0) {
            model.addAttribute("sub_emp_list", sub_emp_list);

        }
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);

        if (file_tag.equals("Y")) {
            List<FileDto> file_list = fileService.getFileName(param);
            model.addAttribute("file", file_list);
        }
        return "modPage";
    }
    @GetMapping("/modPage2/{param}")
    public String modifyPage2(@PathVariable("param") String param, Model model, HttpSession session) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        ListDto info = listService.list_info(param);
        model.addAttribute("info", info);
        String file_tag = info.getFile_tag();
        UserDto dto = (UserDto) session.getAttribute("dto");

        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);

        List<UserDto> menu_list_h = userService.menu_list_h();
        List<UserDto> menu_list_s = userService.menu_list_s();
        List<UserDto> trn_list = userService.trn_list();


        if (sub_emp_list.size() > 0) {
            model.addAttribute("sub_emp_list", sub_emp_list);

        }
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);

        if (file_tag.equals("Y")) {
            List<FileDto> file_list = fileService.getFileName(param);
            model.addAttribute("file", file_list);
        }
        model.addAttribute("trn_list", trn_list);
        return "modPage2";
    }

    @GetMapping("/modPage_op/{param}")
    public String modifyPage3(@PathVariable("param") String param, Model model, HttpSession session) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        ListDto info = listService.list_info(param);
        model.addAttribute("info", info);
        String file_tag = info.getFile_tag();
        UserDto dto = (UserDto) session.getAttribute("dto");

        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);

        List<UserDto> menu_list_h = userService.menu_list_h();
        List<UserDto> menu_list_s = userService.menu_list_s();
        List<UserDto> trn_list = userService.trn_list();


        if (sub_emp_list.size() > 0) {
            model.addAttribute("sub_emp_list", sub_emp_list);

        }
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);

        if (file_tag.equals("Y")) {
            List<FileDto> file_list = fileService.getFileName(param);
            model.addAttribute("file", file_list);
        }
        model.addAttribute("trn_list", trn_list);
        return "modPage_op";
    }

    @GetMapping("/modPage_im/{param}")
    public String modifyPage4(@PathVariable("param") String param, Model model, HttpSession session) {
        if (session.getAttribute("dto") == null) {
            return "index";
        }
        ListDto info = listService.list_info(param);
        model.addAttribute("info", info);
        String file_tag = info.getFile_tag();
        UserDto dto = (UserDto) session.getAttribute("dto");

        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);

        List<UserDto> menu_list_h = userService.menu_list_h();
        List<UserDto> menu_list_s = userService.menu_list_s();
        List<UserDto> trn_list = userService.trn_list();


        if (sub_emp_list.size() > 0) {
            model.addAttribute("sub_emp_list", sub_emp_list);

        }
        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);

        if (file_tag.equals("Y")) {
            List<FileDto> file_list = fileService.getFileName(param);
            model.addAttribute("file", file_list);
        }
        model.addAttribute("trn_list", trn_list);
        return "modPage_im";
    }

//    @RequestMapping("/cmt_select")
//    @ResponseBody
//    public String cmt_select(HttpServletRequest request, HttpSession session,Model model) {
//
//
//        String menu_code_s = request.getParameter("menu_code_s");
//
//
//        model.addAttribute("cmt_select", listService.cmt_select(menu_code_s));
//
//        listService.cmt_select(menu_code_s);
//
//        return "write";
//    }

    //@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST}) // 기본
   // @RequestMapping("/write")

   // @RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
    //@RequestMapping(value="/write", method = {RequestMethod.GET}) // 기본
   @GetMapping("/write")
   /*@ResponseBody*/
   public String write(HttpSession session, Model model) {


       if (session.getAttribute("dto") == null) {
           return "index"; //세션 없으면 로그인 페이지.
       }
       UserDto dto = (UserDto) session.getAttribute("dto");

       ListDto cmt = listService.cmt_select();

       String emp_no = dto.getEmpno();
       List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);

       AuthDto auth = null;
       auth =(AuthDto) session.getAttribute("auth");
       List<UserDto> menu_list_h = userService.menu_list_h(); //큰 메뉴틀은 고정
       List<UserDto> menu_list_s ;
       if(auth != null){
           if(auth.getDOC_AUTH().equals("T")){
               menu_list_s = userService.menu_list_s();
           }else{
               menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
           }
       }else  {
           menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
       }

//        if(auth.getDOC_AUTH().equals("T")){
//            menu_list_s = userService.menu_list_s();
//        }else{
//            menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
//        }
//        if (sub_emp_list.size() > 0) {
//            model.addAttribute("sub_emp_list", sub_emp_list);
//
//        }
       model.addAttribute("menu_list_h", menu_list_h);
       model.addAttribute("menu_list_s", menu_list_s);
       model.addAttribute("data",menuService.test());
       model.addAttribute("cmt",cmt);
//        model.addAttribute("menu_list", userService.menu_list());
       return "write";
   }
//    @RequestMapping("/write")
//    /*@ResponseBody*/
//    public String write(HttpSession session, Model model,HttpServletRequest request) {
//
//        String menu_code_s = request.getParameter("menu_code_s");
//        //System.out.println(menu_code_s);
////        String menu_code_s = request.getParameter("menu_code_s");
////
////        System.out.println(menu_code_s);
////
////        ListDto cmt_select = listService.cmt_select(menu_code_s);
////
////        model.addAttribute("cmt_select", cmt_select);
//        //model.addAttribute("cmt", listService.cmt_select(menu_code_s));
////        if (session.getAttribute("dto") == null) {
////            return "index";
////        }
////
////
////        AuthDto auth = null;
////
////
////
////        UserDto dto = (UserDto) session.getAttribute("dto");
////
////
////        //model.addAttribute("cmt", listService.cmt_select(menu_code_s));
////
////
////        String emp_no = dto.getEmpno();
////
////        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);
////
////        String menu_code_s = request.getParameter("menu_code_s");
////
////        System.out.println(menu_code_s);
////
////        listService.cmt_select(request.getParameter("menu_code_s"));
////
////        auth =(AuthDto) session.getAttribute("auth");
////        List<UserDto> menu_list_h = userService.menu_list_h(); //큰 메뉴틀은 고정
////        List<UserDto> menu_list_s ;
////        if(auth != null){
////            if(auth.getDOC_AUTH().equals("T")){
////                menu_list_s = userService.menu_list_s();
////            }else{
////                menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
////            }
////        } else {
////            menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
////        }
////
////        model.addAttribute("menu_list_h", menu_list_h);
////        model.addAttribute("menu_list_s", menu_list_s);
////        model.addAttribute("data",menuService.test());
//
//
//
//
//        //model.addAttribute("cmt",userService.get_cmt_name(menu_code_s));
//        return "write";
//    }
    @GetMapping("/write2") // 비상대응훈련
    /*@ResponseBody*/
    public String write2(HttpSession session, Model model) {
        if (session.getAttribute("dto") == null) {
            return "index"; //세션 없으면 로그인 페이지.
        }
        UserDto dto = (UserDto) session.getAttribute("dto");

        ListDto cmt = listService.cmt_select();

        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);



        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");
        List<UserDto> menu_list_h = userService.menu_list_h(); //큰 메뉴틀은 고정
        List<UserDto> trn_list = userService.trn_list();
        List<UserDto> menu_list_s ;
        if(auth != null){
            if(auth.getDOC_AUTH().equals("T")){
                menu_list_s = userService.menu_list_s();
            }else{
                menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
            }
        }else  {
            menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
        }

        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("trn_list", trn_list);
        model.addAttribute("menu_list_s", menu_list_s);
        model.addAttribute("data",menuService.test());
        model.addAttribute("cmt",cmt);
        return "write2";
    }

    @GetMapping("/write_op") // 종사자
    /*@ResponseBody*/
    public String write_op(HttpSession session, Model model,HttpServletRequest request) {

        if (session.getAttribute("dto") == null) {
            return "index"; //세션 없으면 로그인 페이지.
        }
        UserDto dto = (UserDto) session.getAttribute("dto");

        ListDto cmt = listService.cmt_select();


        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);



        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");
        List<UserDto> menu_list_h = userService.menu_list_h(); //큰 메뉴틀은 고정
        List<UserDto> menu_list_s ;
        if(auth != null){
            if(auth.getDOC_AUTH().equals("T")){
                menu_list_s = userService.menu_list_s();
            }else{
                menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
            }
        }else  {
            menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
        }


        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);
        model.addAttribute("data",menuService.test());
        model.addAttribute("cmt",cmt);
        return "write_op";
    }


    @GetMapping("/write_im") // 종사자
    /*@ResponseBody*/
    public String write_im(HttpSession session, Model model,HttpServletRequest request) {

        if (session.getAttribute("dto") == null) {
            return "index"; //세션 없으면 로그인 페이지.
        }
        UserDto dto = (UserDto) session.getAttribute("dto");

        String menu_code_s = request.getParameter("menu_code_s");
        System.out.println(menu_code_s);



        String emp_no = dto.getEmpno();
        List<UserDto> sub_emp_list = userService.get_sub_name(emp_no);



        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");
        List<UserDto> menu_list_h = userService.menu_list_h(); //큰 메뉴틀은 고정
        List<UserDto> menu_list_s ;
        if(auth != null){
            if(auth.getDOC_AUTH().equals("T")){
                menu_list_s = userService.menu_list_s();
            }else{
                menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
            }
        }else  {
            menu_list_s = userService.menu_list_noAdmin(dto.getEmpno(),dto.getProj_code());
        }


        model.addAttribute("menu_list_h", menu_list_h);
        model.addAttribute("menu_list_s", menu_list_s);
        model.addAttribute("data",menuService.test());
        return "write_im";
    }




}
