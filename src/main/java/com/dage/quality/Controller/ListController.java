package com.dage.quality.Controller;

import com.dage.quality.DTO.UserDto;
import com.dage.quality.Service.CommentService;
import com.dage.quality.Service.FileService;
import com.dage.quality.Service.ListService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class ListController {
    @Autowired
    ListService listService;
    @Autowired
    FileService fileService;

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(HttpServletRequest request, HttpSession session, MultipartHttpServletRequest request2
            , @RequestParam(value = "file", required = false) MultipartFile[] mFile) throws IOException {
        String result ="0" ;
        if (session.getAttribute("dto")==null){
            System.out.println("세션종료");
            return "-2";
        }
        UserDto user =  (UserDto)session.getAttribute("dto");

        String seq = "";
        String user_id = user.getUser_id();
        String user_no = request.getParameter("user_no");
        try {
            if(mFile!=null){
                seq = listService.getFinalSeq(user_no);
                listService.updateFileTag(seq);

                String upload_folder ="" ;
                upload_folder = System.getProperty("user.dir")+"Upload/"+user_id+"/";

                File files_path = new File(upload_folder);
                if (!files_path.exists()) {
                    files_path.mkdirs();
                }
                for (MultipartFile multipartFile : mFile) {

                    String origin_file_nm = null;
                    long f_size = 0;
                    origin_file_nm = multipartFile.getOriginalFilename(); //실제 파일이름



                    f_size = multipartFile.getSize()/1024; // 파일 사이즈 KB

                    String file_ex = FilenameUtils.getExtension(origin_file_nm); //파일 확장자
                    String c_file_name = System.currentTimeMillis()+"."+file_ex; //파일 이름 변경
                    File save = new File(upload_folder,c_file_name);
                    multipartFile.transferTo(save);
                    fileService.insertFileInfo(seq,origin_file_nm,c_file_name,upload_folder,f_size);

                }

            }
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }
    @RequestMapping("/m_up_File")
    @ResponseBody
    public String m_up_File(HttpServletRequest request, HttpSession session, MultipartHttpServletRequest request2
            , @RequestParam(value = "file", required = false) MultipartFile[] mFile) throws IOException {
        String result ="0" ;
        if (session.getAttribute("dto")==null){
            System.out.println("세션종료");
            return "-2";
        }
        UserDto user =  (UserDto)session.getAttribute("dto");

        String seq = request.getParameter("seq");
        String user_id = user.getUser_id();
        String user_no = request.getParameter("user_no");
        try {
            if(mFile!=null){

                listService.updateFileTag(seq);

                String upload_folder ="" ;
                upload_folder = System.getProperty("user.dir")+"Upload/"+user_id+"/";

                File files_path = new File(upload_folder);
                if (!files_path.exists()) {
                    files_path.mkdirs();
                }
                for (MultipartFile multipartFile : mFile) {

                    String origin_file_nm = null;
                    long f_size = 0;
                    origin_file_nm = multipartFile.getOriginalFilename(); //실제 파일이름



                    f_size = multipartFile.getSize()/1024; // 파일 사이즈 KB

                    String file_ex = FilenameUtils.getExtension(origin_file_nm); //파일 확장자
                    String c_file_name = System.currentTimeMillis()+"."+file_ex; //파일 이름 변경
                    File save = new File(upload_folder,c_file_name);
                    multipartFile.transferTo(save);
                    fileService.insertFileInfo(seq,origin_file_nm,c_file_name,upload_folder,f_size);

                }

            }
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpSession session
            , @RequestParam(value = "files", required = false) MultipartFile[] mFile) throws IOException {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }
        String user_no = request.getParameter("user_no");
        String position = request.getParameter("position");
        String emp_no = request.getParameter("empno");
        String title = request.getParameter("title");
        String menu_code = request.getParameter("menu_code");
        String menu_code_s = request.getParameter("menu_code_s");
        String menu_name = request.getParameter("menu_name");
        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");
        String content = request.getParameter("content");
        UserDto user =  (UserDto)session.getAttribute("dto");
        String doc_month = request.getParameter("doc_month");
        String doc_turn = request.getParameter("doc_turn");
        String doc_sdt = request.getParameter("doc_sdt");
        String doc_edt = request.getParameter("doc_edt");
        String seq = "";
        String user_id = user.getUser_id();



        try {
            listService.write(menu_code,menu_code_s,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_month,doc_turn,doc_sdt,doc_edt);
            seq = listService.getFinalSeq(user_no);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/upload_b")
    @ResponseBody
    public String upload_b(HttpServletRequest request, HttpSession session
            , @RequestParam(value = "files", required = false) MultipartFile[] mFile) throws IOException {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }
        String user_no = request.getParameter("user_no");
        String position = request.getParameter("position");
        String emp_no = request.getParameter("empno");
        String title = request.getParameter("title");
        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");
        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");
        String content = request.getParameter("content");
        UserDto user =  (UserDto)session.getAttribute("dto");
        String doc_dt = request.getParameter("doc_dt");
        String trn_case = request.getParameter("trn_case");
        String seq = "";
        String user_id = user.getUser_id();
        try {
            listService.write2(menu_code,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_dt,trn_case);
            seq = listService.getFinalSeq(user_no);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/upload_o")
    @ResponseBody
    public String upload_o(HttpServletRequest request, HttpSession session
            , @RequestParam(value = "files", required = false) MultipartFile[] mFile) throws IOException {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }
        String user_no = request.getParameter("user_no");
        String position = request.getParameter("position");
        String emp_no = request.getParameter("empno");
        String title = request.getParameter("title");
        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");
        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");
        String content = request.getParameter("content");
        UserDto user =  (UserDto)session.getAttribute("dto");
        String doc_month = request.getParameter("doc_month");
        String op_num = request.getParameter("op_num");
        String seq = "";
        String user_id = user.getUser_id();
        try {
            listService.write_op(menu_code,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_month,op_num);
            seq = listService.getFinalSeq(user_no);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/upload_i")
    @ResponseBody
    public String upload_i(HttpServletRequest request, HttpSession session
            , @RequestParam(value = "files", required = false) MultipartFile[] mFile) throws IOException {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }
        String user_no = request.getParameter("user_no");
        String position = request.getParameter("position");
        String emp_no = request.getParameter("empno");
        String title = request.getParameter("title");
        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");
        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");
        String content = request.getParameter("content");
        UserDto user =  (UserDto)session.getAttribute("dto");
        String doc_dt = request.getParameter("doc_dt");
        String ck_point = request.getParameter("ck_point");
        String seq = "";
        String user_id = user.getUser_id();
        try {
            listService.write_im(menu_code,title,name,dept_name,proj_code,content,menu_name,position,user_no,emp_no,doc_dt,ck_point);
            seq = listService.getFinalSeq(user_no);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/cmt")
    @ResponseBody
    public String cmt(HttpServletRequest request, HttpSession session) {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }

        String cmt1 = request.getParameter("cmt1");
        String cmt2 = request.getParameter("cmt2");
        String cmt3 = request.getParameter("cmt3");
        String cmt4 = request.getParameter("cmt4");
        String cmt5 = request.getParameter("cmt5");

        try {
            listService.cmt(cmt1,cmt2,cmt3,cmt4,cmt5);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }


    @RequestMapping("/modList")
    @ResponseBody
    public String modList(HttpServletRequest request, HttpSession session) {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }

        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");

        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String doc_ver = request.getParameter("doc_ver");
        String moduser = request.getParameter("moduser");


        String seq = request.getParameter("seq");
        String doc_month = request.getParameter("doc_month");
        String doc_turn = request.getParameter("doc_turn");
        String doc_sdt = request.getParameter("doc_sdt");
        String doc_edt = request.getParameter("doc_edt");

        try {
            listService.mod(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_month,doc_turn,doc_sdt,doc_edt);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/modList2")
    @ResponseBody
    public String modList2(HttpServletRequest request, HttpSession session) {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }

        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");

        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String doc_ver = request.getParameter("doc_ver");
        String moduser = request.getParameter("moduser");


        String seq = request.getParameter("seq");
        String doc_dt = request.getParameter("doc_dt");
        String trn_case = request.getParameter("trn_case");

        try {
            listService.mod2(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_dt,trn_case);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }


    @RequestMapping("/modList_op")
    @ResponseBody
    public String modList_op(HttpServletRequest request, HttpSession session) {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }


        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");

        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String doc_ver = request.getParameter("doc_ver");
        String moduser = request.getParameter("moduser");


        String seq = request.getParameter("seq");
        String doc_month = request.getParameter("doc_month");
        String op_num = request.getParameter("op_num");



        try {
            listService.mod_op(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_month,op_num);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/modList_im")
    @ResponseBody
    public String modList_im(HttpServletRequest request, HttpSession session) {
        String result ="0" ;
        if(session.getAttribute("dto")==null){
            return "-1";
        }


        String menu_code = request.getParameter("menu_code");
        String menu_name = request.getParameter("menu_name");

        String dept_name = request.getParameter("dept_name");
        String proj_code = request.getParameter("proj_code");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String doc_ver = request.getParameter("doc_ver");
        String moduser = request.getParameter("moduser");


        String seq = request.getParameter("seq");
        String doc_dt = request.getParameter("doc_dt");
        String ck_point = request.getParameter("ck_point");



        try {
            listService.mod_im(menu_code,menu_name,title,content,doc_ver,moduser,dept_name,proj_code,seq,doc_dt,ck_point);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }

    @RequestMapping("/delete_list")
    @ResponseBody
    public String delete_list(HttpServletRequest request){
        String result ="";
        try {
            listService.delete_list(request.getParameter("seq"));return result ="1";
        }catch (Exception e){
            e.printStackTrace();
            return result="-1";
        }
    }


}
