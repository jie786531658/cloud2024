package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    public static final String PaymentSrv_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder (PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo (@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData<String> delPayInfo (@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/del/" + id);
        return ResultData.success("删除成功");
    }

    @PutMapping("/consumer/pay/update")
    public ResultData<String> updatePayInfo (PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
        return ResultData.success("更新成功");
    }

    @GetMapping("/consumer/pay/get/info")
    public String getInfoByConsul () {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }

}
