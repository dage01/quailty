package com.dage.quality.Service;

import com.dage.quality.DAO.FIleDao;
import com.dage.quality.DTO.FileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    FIleDao dao;
            public void insertFileInfo(@Param("seq")String seq
                    ,@Param("origin_nm")String origin_nm
                    ,@Param("save_nm")String save_nm
                    ,@Param("path")String path
                    ,@Param("f_size") Long f_size){
                dao.insertFileInfo(seq,origin_nm,save_nm,path,f_size);
            }
            public List<FileDto> getFileName (@Param("list_seq")String list_seq){
                return  dao.getFileName(list_seq);
            }
            public FileDto getFileInfo(@Param("seq")String seq){
                return dao.getFileInfo(seq);
            }
            public void delete_file(@Param("seq")String seq){dao.delete_file(seq);}
}
