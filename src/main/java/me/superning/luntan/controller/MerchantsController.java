package me.superning.luntan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import me.superning.luntan.service.MerchantService;
import me.superning.luntan.vo.MerchantsRequest;
import me.superning.luntan.vo.PassTemplate;
import me.superning.luntan.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/merchant")
public class MerchantsController {
  //  private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MerchantService merchantService;

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody MerchantsRequest request) {
        logger.info("Create Merchant-----> [{}]", JSON.toJSONString(request));
        return merchantService.createMerchants(request);

    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantMsg(@PathVariable int id) {

        logger.info("Merchant Msg ID ----> [{}]",id);
        return merchantService.buildMerchanrById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate( @RequestBody PassTemplate passTemplates) {
        logger.info("The passtemplate is-----> [{}]",JSON.toJSONString(passTemplates));
        return merchantService.dropPasstemplate(passTemplates);
    }


}
