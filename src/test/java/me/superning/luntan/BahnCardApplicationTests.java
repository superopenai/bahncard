package me.superning.luntan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import me.superning.luntan.service.MerchantService;
import me.superning.luntan.vo.MerchantsRequest;
import me.superning.luntan.vo.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
