package me.superning.luntan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.superning.luntan.domain.ErrorCode;
import me.superning.luntan.service.MerchantService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 优惠卷对象定义
 * @author superning
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplate {

    /** 优惠卷id*/
    private Long id;
    /** 优惠卷title*/
    private String title;
    /** 优惠卷描述*/
    private String desc;
    /** 优惠卷详细信息*/
    private String message;
    /** 优惠卷发放数量*/
    private Long limit;
    /** 优惠卷是否有token， 用于商户核销 token储存与Redis中*/
    private Boolean hasToken;
    /** 优惠卷背景颜色代码*/
    private Integer backGroundColorCode;
    /** 优惠卷开始时间*/
    private Date start;
    /** 优惠卷结束时间*/
    private Date end;

//    /**
//     * 判断优惠卷的有效性
//     * @return {@link ErrorCode}
//     */
//    public ErrorCode validPasstemplate()
//    {
//
//    }



}
