package com.dage.quality.Controller;


import com.dage.quality.DTO.UserDto;
import com.dage.quality.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class rest2Controller {

    @Autowired
    private JavaMailSender javaMailSender;
    UserService userService;
    @RequestMapping("/test3")
    public String sendMail(HttpServletRequest request,HttpSession session) throws MessagingException{

        String result = "0";
        String[] sender  = null;
        sender = new String[] {"hwkim@dage.co.kr","whchae@dage.co.kr","cywoo@dage.co.kr","kslee@dage.co.kr","jschoi@dage.co.kr"};

        UserDto user = (UserDto)session.getAttribute("dto");
        String user_id = user.getUser_id();
        String user_nm = user.getUser_nm();
        String title = request.getParameter("title");
        String rmk = request.getParameter("rmk");
        String to_user = request.getParameter("to_user");
        String cc = request.getParameter("cc");
        String email = request.getParameter("email");

        String[] array = email.split(",");

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(user_id+"@dage.co.kr");
            helper.setTo(to_user+"@dage.co.kr");
            helper.setCc(array);
            helper.setSubject("글제목 : " + title + " 글에 댓글이 등록되었습니다.");
            helper.setText("작성자 : " + user_nm + "\n" +" 내용 : " + rmk);
            javaMailSender.send(message);
            result = "1";

        } catch (Exception e){
            e.printStackTrace();
            result = "-1";
        }

        return result;


    }




}
