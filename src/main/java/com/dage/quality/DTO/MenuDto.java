package com.dage.quality.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

public class MenuDto {

    private String id;


    private String text;

    private String crtuserno;
    private String moduserno;
    private String crtdate;
    private String parent;
    private int level;
    private int cnt;


    private String com_value;


}
