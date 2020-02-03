package me.superning.luntan.service.Impl;

import me.superning.luntan.domain.ErrorCode;
import me.superning.luntan.domain.Merchant;
import me.superning.luntan.mapper.MerchantMapper;
import me.superning.luntan.service.MerchantService;
import me.superning.luntan.vo.MerchantResponse;
import me.superning.luntan.vo.MerchantsRequest;
import me.superning.luntan.vo.PassTemplate;
import me.superning.luntan.vo.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public Merchant findById(Integer id) {
        return merchantMapper.selectByPrimaryKey(id);
    }

    @Override
    public Merchant findByName(String name) {
        Example merchantExample = new Example(Merchant.class);
        merchantExample.createCriteria().andEqualTo("name",name);
        return merchantMapper.selectOneByExample(merchantExample);
    }

    @Override
    @Transactional
    public Response createMerchants(MerchantsRequest request) {
        Response response = new Response();
        MerchantResponse merchantResponse = new MerchantResponse();
        // 校验一下各个属性是否正确。
        ErrorCode errorCode = vaild(request);
        // ErrorCode errorCode = request.vaildRequest();
        //出错了
        if (errorCode!=ErrorCode.SUCCESS) {
            merchantResponse.setId(-1L);
            response.setErrorMsg(errorCode.getMessage());
            response.setErrorCode(errorCode.getCode());
        } else {
            Merchant merchant = request.toMerchants();
            // 插入操作
            merchantMapper.insertSelective(merchant);
            merchantResponse.setId(merchant.getId());
        }
        // todo
        response.setData(merchantResponse);
        return response;
    }

    @Override
    public Response buildMerchanrById(Integer id) {
        return null;
    }

    @Override
    public Response dropPasstemplate(PassTemplate passTemplate) {
        return null;
    }

    @Override
    public ErrorCode vaild(MerchantsRequest request) {
        if (findByName(request.getName()) != null) {
            return ErrorCode.DUPLICATE_NAME;
        } else if (StringUtils.isEmpty(request.getName())) {
            return ErrorCode.EMPTY_NAME;
        } else if (request.getPersonalPhone().length() != 11) {
            return ErrorCode.ERROR_PHONE;
        } else if (StringUtils.isEmpty(request.getLogoUrl())) {
            return ErrorCode.EMPTY_LOGO;
        } else if (StringUtils.isEmpty(request.getLicenseUrl())) {
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        } else if (StringUtils.isEmpty(request.getAddress())) {
            return ErrorCode.EMPTY_ADDRESS;
        }
        return ErrorCode.SUCCESS;
    }
}
