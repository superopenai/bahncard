package me.superning.luntan.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "Merchant")
public class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 商户名字
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 商标logo的url
     */
    @Column(name = "logo_url")
    private String logoUrl;
    /**
     * 执照url
     */
    @Column(name = "license_url")
    private String licenseUrl;
    /**
     * 个人电话
     */
    @Column(name = "personal_phone")
    private String personalPhone;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 人工审核默认为0
     */
    @Column(name = "is_audit")
    private Boolean isAudit;
}