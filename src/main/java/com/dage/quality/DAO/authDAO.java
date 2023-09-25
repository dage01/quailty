package com.dage.quality.DAO;

import com.dage.quality.DTO.AuthDto;
import com.dage.quality.DTO.FileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface authDAO {

    AuthDto auth_s(@Param("empno")String empno);
    List<AuthDto> auth_all();
    void addAuth(@Param("empno")String empno,
                 @Param("admin_tg")String admin_tg,
                 @Param("addFolder")String addFolder,
                 @Param("modFolder")String modFolder,
                 @Param("delFolder")String delFolder,
                 @Param("doc_tg")String doc_tg);
    void delAuth(@Param("empno")String empno);
}
