package me.superning.luntan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import me.superning.luntan.service.MerchantService;
import me.superning.luntan.vo.MerchantsRequest;
import me.superning.luntan.vo.PassTemplate;
import me.superning.luntan.vo.Response;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonbTester;

import java.util.Date;

@SpringBootTest
class BahnCardApplicationTests {
    @Autowired
    MerchantService service;

    @Test
    void createMerchants() {
        MerchantsRequest merchantsRequest = new MerchantsRequest();
        merchantsRequest.setName("测试一号");
        merchantsRequest.setPersonalPhone("12345678912");
        merchantsRequest.setAddress("测试地址");
        merchantsRequest.setLicenseUrl("test-LicenseUrl");
        merchantsRequest.setLogoUrl("test-LogoUrl");

        Response merchants = service.createMerchants(merchantsRequest);
        System.out.println(JSON.toJSONString(merchants, SerializerFeature.WriteClassName));

    }

    @Test
    void buildMerchanrById(){

        System.out.println(JSON.toJSONString(service.buildMerchanrById(1)));
    }

    @Test
    void dropPasstemplate() {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(1L);
        passTemplate.setTitle("测试标题二号");
        passTemplate.setBackGroundColorCode(2);
        passTemplate.setDesc("测试描述二号");
        passTemplate.setMessage("测试信息二号");
        passTemplate.setHasToken(false);
        passTemplate.setLimit(100000L);
        passTemplate.setStart(new Date());
        passTemplate.setEnd(DateUtils.addDays(new Date(),10));

        System.out.println(JSON.toJSONString(service.dropPasstemplate(passTemplate)));
    }
}
