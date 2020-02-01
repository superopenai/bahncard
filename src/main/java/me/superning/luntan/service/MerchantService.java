package me.superning.luntan.service;

import me.superning.luntan.domain.Merchant;

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
    Merchant findById(Integer id);

    /**
     * 根据商户名称获取商户对象
     * @param name 商户名称
     * @return {@link Merchant}
     */
    Merchant findByName(String name);





}
