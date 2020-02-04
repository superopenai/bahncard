package me.superning.luntan.service;

import me.superning.luntan.domain.ErrorCode;
import me.superning.luntan.domain.Merchant;
import me.superning.luntan.vo.MerchantsRequest;
import me.superning.luntan.vo.PassTemplate;
import me.superning.luntan.vo.Response;

/**
 *
 * @author superning
 */
public interface MerchantService{

    /**
     * 根据商户id获取商户对象
     * @param id 商户id
     * @return {@link Merchant}
     */
    Merchant findById(Long id);

    /**
     * 根据商户名称获取商户对象
     * @param name 商户名称
     * @return {@link Merchant}
     */
    Merchant findByName(String name);

    /**
     * 创建商户服务接口
     * @param request {@link MerchantsRequest} 创建的请求
     * @return {@link Response}
     */
    Response createMerchants(MerchantsRequest request);

    /**
     * 根据id生成商户信息
     * @param id 商户id
     * @return {@link Response}
     */
    Response buildMerchanrById(Long id);

    /**
     * 投放优惠卷
     * @param passTemplate {@link PassTemplate} 优惠卷对象
     * @return {@link Response}
     */
    Response dropPasstemplate(PassTemplate passTemplate);

    /**
     * 验证商户请求对象是否符合定义
     * @param request {@link MerchantsRequest} 传入一个商户请求对象
     * @return {@link ErrorCode}
     */
    ErrorCode vaildMerchantsRequest(MerchantsRequest request);



    ErrorCode validPasstemplate(PassTemplate passTemplate);
}
