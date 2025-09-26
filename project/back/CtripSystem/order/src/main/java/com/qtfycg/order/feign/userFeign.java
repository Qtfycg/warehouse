package com.qtfycg.order.feign;

import com.qtfycg.common.R.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "user")
public interface userFeign {
    @GetMapping("/me")
    R getInfo(Long userId);
}
