package me.superning.luntan.service.Impl;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import me.superning.luntan.domain.Constants;
import me.superning.luntan.domain.ErrorCode;
import me.superning.luntan.domain.Merchant;
import me.superning.luntan.mapper.MerchantMapper;
import me.superning.luntan.service.MerchantService;
import me.superning.luntan.vo.MerchantResponse;
import me.superning.luntan.vo.MerchantsRequest;
import me.superning.luntan.vo.PassTemplate;
import me.superning.luntan.vo.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public Merchant findById(Long id) {
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
        ErrorCode errorCode = vaildMerchantsRequest(request);

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
    public Response buildMerchanrById(int id) {

        Response response = new Response();

        Merchant merchant = findById((long) id);

        //查询的商户不存在
        if (merchant==null) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getMessage());
        }
        // 返回的响应对象，把merchant包装进去,无论对错。
        response.setData(merchant);
        logger.info("this is the response ------>[{}]",response);
        return response;
    }

    @Override
    public Response dropPasstemplate(PassTemplate passTemplate) {
        Response response = new Response();
        ErrorCode errorCode = validPasstemplate(passTemplate);
        if (errorCode!=ErrorCode.SUCCESS){
            response.setErrorMsg(errorCode.getMessage());
            response.setErrorCode(errorCode.getCode());
        } else {
            String templateJson = JSON.toJSONString(passTemplate);
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Constants.TEMPLATE_TOPIC,
                    Constants.TOKEN_KEY,
                    Constants.TOKEN_VALUE);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("发送消息[{}] 失败，原因[{}]",Constants.TOKEN_VALUE,throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> stringStringSendResult) {

                }
            });

            logger.info("DropPassTemplate + [{}]",passTemplate);

        }
        return response;
    }

    @Override
    public ErrorCode vaildMerchantsRequest(MerchantsRequest request) {
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

    @Override
    public ErrorCode validPasstemplate(PassTemplate passTemplate) {
        if (findById(passTemplate.getId())==null)
        {
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }

        return ErrorCode.SUCCESS;
    }
}
