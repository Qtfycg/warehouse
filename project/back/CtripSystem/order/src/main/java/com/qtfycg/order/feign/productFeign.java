package com.qtfycg.order.feign;

import com.qtfycg.common.R.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "product")
public interface productFeign {
    @GetMapping("/detail")
    R getDetail(Long id);
}
