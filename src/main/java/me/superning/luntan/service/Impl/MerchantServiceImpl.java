package me.superning.luntan.service.Impl;

import me.superning.luntan.domain.Merchant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import me.superning.luntan.mapper.MerchantMapper;
import me.superning.luntan.service.MerchantService;
@Service
public class MerchantServiceImpl implements MerchantService{

    @Resource
    private MerchantMapper merchantMapper;

    @Override
    public Merchant findById(Integer id) {
        return null;
    }

    @Override
    public Merchant findByName(String name) {
        return null;
    }
}
