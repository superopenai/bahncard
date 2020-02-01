package me.superning.luntan.domain;

import lombok.Data;

/**
 * @author superning
 * 优惠卷背景颜色
 */
public enum TemplateColor {

    /**红色 */
    RED(1,"红色"),
    /** 蓝色*/
    BLUE(2,"蓝色"),
    /**绿色 */
    GREEN(3,"绿色");

    private Integer Code;

    private String Color;


    TemplateColor(Integer code, String color) {
        Code = code;
        Color = color;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
