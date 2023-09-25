package com.dage.quality.DAO;

import com.dage.quality.DTO.ListDto;
import com.dage.quality.DTO.MenuDto;
import com.dage.quality.DTO.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

    List<MenuDto> test();
    List<MenuDto> chk_id();
}
