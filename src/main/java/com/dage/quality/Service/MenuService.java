package com.dage.quality.Service;

import com.dage.quality.DAO.FIleDao;
import com.dage.quality.DAO.MenuDao;
import com.dage.quality.DTO.FileDto;
import com.dage.quality.DTO.MenuDto;
import com.dage.quality.DTO.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {
    @Autowired
    MenuDao dao;

    public List<MenuDto> test(){
        return dao.test();
    }

    public List<MenuDto> chk_id(){
        return dao.chk_id();
    }

}
