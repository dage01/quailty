package com.dage.quality.DAO;

import com.dage.quality.DTO.FileDto;
import com.dage.quality.DTO.ListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FIleDao {
    void insertFileInfo(@Param("seq")String seq
                       ,@Param("origin_nm")String origin_nm
                       ,@Param("save_nm")String save_nm
                       ,@Param("path")String path
                       ,@Param("f_size")Long f_size);
    List<FileDto> getFileName (@Param("list_seq")String list_seq);
    FileDto getFileInfo(@Param("seq")String seq);

    void delete_file(@Param("seq")String seq);
}
