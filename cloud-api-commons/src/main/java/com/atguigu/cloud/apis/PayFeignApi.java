package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("cloud-payment-service")
@FeignClient("cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    ResultData<Object> addPay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    ResultData<Object> getPayInfo(@PathVariable("id") Integer id);

    @GetMapping("/pay/get/info")
    String mylb();

    /**
     * Resilience4j CircuitBreaker 的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     *
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();

}
