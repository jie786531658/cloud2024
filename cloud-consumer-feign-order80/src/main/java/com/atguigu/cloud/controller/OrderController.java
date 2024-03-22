package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData<Object> addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData<Object> getPayInfo(@PathVariable("id") Integer id) {

        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData<Object> resultData = null;
        try {
            System.out.println("调用开始-----:" + DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:" + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
//        return payFeignApi.getPayInfo(id);
    }

    @GetMapping("/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.mylb();
    }

}
