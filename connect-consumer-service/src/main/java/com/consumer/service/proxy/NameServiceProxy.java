package com.consumer.service.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.consumer.service.request.NameRequest;
import com.consumer.service.response.NameResponse;

//@FeignClient(name = "connect-producer-service",url = "localhost:8081")
@FeignClient(name = "connect-zuul-api-gateway")
@RibbonClient(name= "connect-producer-service")
public interface NameServiceProxy {
	@PostMapping("/connect-producer-service/provider/name/v1/getName")
	public NameResponse getName(@RequestBody NameRequest nameRequest);

}
