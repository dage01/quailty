package com.dage.quality.Service;

import com.dage.quality.DAO.FIleDao;
import com.dage.quality.DAO.authDAO;
import com.dage.quality.DTO.AuthDto;
import com.dage.quality.DTO.FileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    authDAO dao;
    public AuthDto auth_s(@Param("empno")String empno){
       return dao.auth_s(empno);
    }
    public List<AuthDto> auth_all(){
       return dao.auth_all();
    }
    public void addAuth(@Param("empno")String empno,
                        @Param("admin_tg")String admin_tg,
                        @Param("addFolder")String addFolder,
                        @Param("modFolder")String modFolder,
                        @Param("delFolder")String delFolder,
                        @Param("doc_tg")String doc_tg){
        dao.addAuth(empno,admin_tg,addFolder,modFolder,delFolder,doc_tg);
    }
    public void delAuth(@Param("empno")String empno){dao.delAuth(empno);}
}
