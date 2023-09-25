package com.dage.quality.Controller;




import com.dage.quality.DAO.FIleDao;
import com.dage.quality.DTO.AuthDto;
import com.dage.quality.DTO.FileDto;
import com.dage.quality.DTO.ListDto;
import com.dage.quality.DTO.UserDto;
import com.dage.quality.DTO.CommentDto;
import com.dage.quality.Service.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@Controller
public class RestController {
    @Autowired
    UserService userService;

    @Autowired
    ListService listService;

    @Autowired
    CommentService CommentService;

    @Autowired
    FileService fileService;

    @Autowired
    AuthService authService;

    @Autowired
    private JavaMailSender javaMailSender;


    @RequestMapping("/menu_code/{id}")
    @ResponseBody
    public List<ListDto>  view_List(@PathVariable String id, Model model,HttpSession session){

        AuthDto auth = null;
        auth =(AuthDto) session.getAttribute("auth");
        UserDto user =null;
        user =(UserDto) session.getAttribute("dto");
        List<ListDto> list = null;
        /*관리자는 전체 일반은 부서만.*/
/*
        if(auth!=null && auth.getDOC_AUTH().equals("T")){

//            System.out.println(list);
        }else{
            list = userService.list_state_proj(user.getEmpno(),user.getProj_code(),id);
        }
*/

            list = userService.list_state(id);

        //2023.07.07 수정중..

        JSONPObject object = null;
        if(list.isEmpty()){
//            System.out.println("등록안됨");
            return null;
        }else {
            object   = new JSONPObject("JSON.parse", list);
        }
        return list;
    }
    @RequestMapping("/viewList")
    @ResponseBody
    public String test3(HttpServletRequest request,Model model){
            String seq = request.getParameter("id");
        return seq;
    }

    @RequestMapping("/list_detail")
    @ResponseBody
    public String list_detail(HttpServletRequest request,Model model){


        String seq = request.getParameter("id");




        List<ListDto> list_detail = listService.list_detail(seq);

       // model.addAttribute("list_detail",listService.list_detail(seq));

        model.addAttribute("list_detail", listService.list_detail(seq));

        listService.list_detail(seq);

        return "main2";
    }


    @RequestMapping("/listPopup/{pathUrl}")
    //@ResponseBody
    public String qul_detail(@PathVariable String pathUrl,Model model,HttpSession session,HttpServletRequest request){

        if(session.getAttribute("dto")==null){
            return "index";
        }
        String seq = pathUrl;
        ListDto info = listService.list_info(seq);
        model.addAttribute("info",info);


        String proj_code = info.getProj_code();





        String file_tag = info.getFile_tag();


        if(file_tag.equals("Y")){
            List<FileDto> file_list = fileService.getFileName(seq);
            model.addAttribute("file",file_list);
        }


        model.addAttribute("comment_list",CommentService.comment_list(seq)); //
        model.addAttribute("dept_list",listService.dept_list(proj_code));



        UserDto dto = (UserDto) session.getAttribute("dto");
        if(authService.auth_s(dto.getEmpno())!=null){
            System.out.println(authService.auth_s(dto.getEmpno()).getADMIN_TG());
            model.addAttribute("auth_tg",authService.auth_s(dto.getEmpno()).getADMIN_TG());
        }



        return "listPopup";
    }




    @RequestMapping("/listPopup2/{pathUrl}")
//    @ResponseBody
    public String qul_detail2(@PathVariable String pathUrl,Model model,HttpSession session){

        if(session.getAttribute("dto")==null){
            return "index";
        }
        String seq = pathUrl;
        ListDto info = listService.list_info(seq);
        model.addAttribute("info",info);
        String proj_code = info.getProj_code();




        String file_tag = info.getFile_tag();


        if(file_tag.equals("Y")){
            List<FileDto> file_list = fileService.getFileName(seq);
            model.addAttribute("file",file_list);
        }

        //CommentDto comm = (CommentDto) CommentService.comment_list(list_seq);
        //model.addAttribute("comm",comm);

        //List<CommentDto> comment_list = CommentService.comment_list(seq);
        model.addAttribute("comment_list",CommentService.comment_list(seq)); //
        model.addAttribute("dept_list",listService.dept_list(proj_code));

        UserDto dto = (UserDto) session.getAttribute("dto");
        if(authService.auth_s(dto.getEmpno())!=null){
            System.out.println(authService.auth_s(dto.getEmpno()).getADMIN_TG());
            model.addAttribute("auth_tg",authService.auth_s(dto.getEmpno()).getADMIN_TG());
        }



        return "listPopup2";
    }

    @RequestMapping("/listPopup_op/{pathUrl}")
//    @ResponseBody
    public String qul_detail3(@PathVariable String pathUrl,Model model,HttpSession session){

        if(session.getAttribute("dto")==null){
            return "index";
        }
        String seq = pathUrl;
        ListDto info = listService.list_info(seq);
        model.addAttribute("info",info);
        String proj_code = info.getProj_code();




        String file_tag = info.getFile_tag();


        if(file_tag.equals("Y")){
            List<FileDto> file_list = fileService.getFileName(seq);
            model.addAttribute("file",file_list);
        }

        //CommentDto comm = (CommentDto) CommentService.comment_list(list_seq);
        //model.addAttribute("comm",comm);

        //List<CommentDto> comment_list = CommentService.comment_list(seq);
        model.addAttribute("comment_list",CommentService.comment_list(seq)); //
        model.addAttribute("dept_list",listService.dept_list(proj_code));

        UserDto dto = (UserDto) session.getAttribute("dto");
        if(authService.auth_s(dto.getEmpno())!=null){
            System.out.println(authService.auth_s(dto.getEmpno()).getADMIN_TG());
            model.addAttribute("auth_tg",authService.auth_s(dto.getEmpno()).getADMIN_TG());
        }



        return "listPopup_op";
    }

    @RequestMapping("/listPopup_im/{pathUrl}")
//    @ResponseBody
    public String qul_detail4(@PathVariable String pathUrl,Model model,HttpSession session){

        if(session.getAttribute("dto")==null){
            return "index";
        }
        String seq = pathUrl;
        ListDto info = listService.list_info(seq);
        model.addAttribute("info",info);
        String proj_code = info.getProj_code();




        String file_tag = info.getFile_tag();


        if(file_tag.equals("Y")){
            List<FileDto> file_list = fileService.getFileName(seq);
            model.addAttribute("file",file_list);
        }

        model.addAttribute("comment_list",CommentService.comment_list(seq)); //
        model.addAttribute("dept_list",listService.dept_list(proj_code));

        UserDto dto = (UserDto) session.getAttribute("dto");
        if(authService.auth_s(dto.getEmpno())!=null){
            System.out.println(authService.auth_s(dto.getEmpno()).getADMIN_TG());
            model.addAttribute("auth_tg",authService.auth_s(dto.getEmpno()).getADMIN_TG());
        }



        return "listPopup_im";
    }

    @RequestMapping("/delcomm")
    @ResponseBody
    public String delcomm(HttpServletRequest request){

        String result ="";
        String seq = request.getParameter("seq");
        String list_seq = request.getParameter("list_seq");
        try {
            CommentService.delcomm(seq,list_seq);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            result ="-1";
        }
        return result;
    }
    @RequestMapping("/delete_file")
    @ResponseBody
    public String delete_file(HttpServletRequest request){
        String result ="";
        String seq = request.getParameter("seq");
        try {
            fileService.delete_file(seq);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            result ="-1";
        }
        return result;
    }
    @RequestMapping(value = "/file_download")
    @ResponseBody
    public ResponseEntity<Resource> fileDownLoad(
            @Param("file_seq")String file_seq, HttpServletResponse response,
            HttpServletRequest request) throws IOException {

        String seq = file_seq;

        FileDto filesInfo = fileService.getFileInfo(seq);

        Resource resource = new FileSystemResource(filesInfo.getPATH()+"/"+filesInfo.getSAVE_NM());
        /*Resource resource = new FileSystemResource(filesInfo.getUpload_folder()+"\\"+filesInfo.getEn_file_name());*/

        if(!resource.exists()){

            System.out.println("-----------파일 경로에 파일 없음--------------------");
            System.out.println("DB에 저장된 파일 주소 :" + filesInfo.getPATH());
            System.out.println("DB에 저장된 파일 이름 " + filesInfo.getSAVE_NM());

            File file = new File(filesInfo.getPATH(),filesInfo.getSAVE_NM());
            System.out.println("상대 경로: "+file.getCanonicalPath());
            System.out.println("절대 경로: "+file.getAbsolutePath());
            System.out.println("경로 URL:"+resource.getURL());
            System.out.println("경로 URI:"+resource.getURI());
            System.out.println("파일 유무:"+resource.isFile());
            System.out.println("get File:"+resource.getFile());
            System.out.println("-----------파일 경로에 파일 없음--------------------");
            return  new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }


        //실제 파일 이름
        String resourceName = filesInfo.getORIGIN_NM();



        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

        //실제파일이름을 인코딩하여 보내기
        String encodingFileName = filesInfo.getSAVE_NM();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Content-Disposition","attachment; filename="+ URLDecoder.decode(encodingFileName,"UTF-8")+"\\");
        headers.set("Access-Control-Expose-Headers", "X-Filename");
        headers.set("X-Filename", encodingFileName);
        return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/file_download2")
    @ResponseBody
    public ResponseEntity<Resource> fileDownLoad2(
            @Param("file_seq")String file_seq, HttpServletResponse response,
            HttpServletRequest request) throws IOException {

        String seq = file_seq;

        FileDto filesInfo = fileService.getFileInfo(seq);

        Resource resource = new FileSystemResource(filesInfo.getPATH()+"/"+filesInfo.getSAVE_NM());
        /*Resource resource = new FileSystemResource(filesInfo.getUpload_folder()+"\\"+filesInfo.getEn_file_name());*/

        if(!resource.exists()){

            System.out.println("-----------파일 경로에 파일 없음--------------------");
            System.out.println("DB에 저장된 파일 주소 :" + filesInfo.getPATH());
            System.out.println("DB에 저장된 파일 이름 " + filesInfo.getSAVE_NM());

            File file = new File(filesInfo.getPATH(),filesInfo.getSAVE_NM());
            System.out.println("상대 경로: "+file.getCanonicalPath());
            System.out.println("절대 경로: "+file.getAbsolutePath());
            System.out.println("경로 URL:"+resource.getURL());
            System.out.println("경로 URI:"+resource.getURI());
            System.out.println("파일 유무:"+resource.isFile());
            System.out.println("get File:"+resource.getFile());
            System.out.println("-----------파일 경로에 파일 없음--------------------");
            return  new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }


        //실제 파일 이름
        String resourceName = filesInfo.getORIGIN_NM();



        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

        //실제파일이름을 인코딩하여 보내기
        String encodingFileName = filesInfo.getSAVE_NM();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Content-Disposition","attachment; filename="+ URLDecoder.decode(encodingFileName,"UTF-8")+"\\");
        headers.set("Access-Control-Expose-Headers", "X-Filename");
        headers.set("X-Filename", encodingFileName);
        return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);

    }






    @RequestMapping("/com_write")
    @ResponseBody
    public int com_write(HttpServletRequest request, HttpSession session){
        String rmk = request.getParameter("rmk");
        //rmk = rmk.replaceAll("\r\n","<br>");
        String list_seq = request.getParameter("list_seq");

        int result = 1;

        UserDto dto = (UserDto) session.getAttribute("dto");
        if(dto!=null){
            String user_nm ="";
            user_nm=dto.getUser_nm();
            String user_id ="";
            user_id=dto.getUser_id();
            String user_no ="";
            user_no=dto.getUser_no();
            String dept_name = "";
            dept_name = dto.getDept_name();
            String position = "";
            position = dto.getPosition();

            String empno = "";
            String qul_com = "";

            CommentService.com_write(list_seq,user_nm,user_no,empno,qul_com,position,dept_name,rmk);

        }else{
            result=-1; //세션에 로그인 값이 없으면 리턴 false
        }
        return result;
    }

}
