package me.superning.luntan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.superning.luntan.domain.ErrorCode;
import me.superning.luntan.domain.Merchant;
import me.superning.luntan.service.MerchantService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * 创建商户请求对象
 *
 * @author superning
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsRequest {


    /**
     * 商户名字
     */
    private String name;
    /**
     * 商标logo的url
     */
    private String logoUrl;
    /**
     * 执照url
     */
    private String licenseUrl;
    /**
     * 个人电话
     */
    private String personalPhone;
    /**
     * 地址
     */
    private String address;



    /**
     * 将VO请求对象转换成商户对象，转换方法写在这里更合适
     * @return {@link Merchant}
     */
    public Merchant toMerchants() {
        Merchant merchant = new Merchant();
        merchant.setName(name);
        merchant.setAddress(address);
        merchant.setPersonalPhone(personalPhone);
        merchant.setLicenseUrl(licenseUrl);
        merchant.setLogoUrl(logoUrl);
        return merchant;
    }

}
