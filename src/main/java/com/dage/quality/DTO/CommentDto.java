package com.dage.quality.DTO;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentDto {
    private String  SEQ;
    private String  LIST_SEQ;
    private String  USER_NM;
    private String  USER_NO;
    private String  EMPNO;
    private String  CRTDATE;
    private String  QUL_COM;
    private String  POSITION;
    private String  DEPT_NAME;
    private String  RMK;
}
