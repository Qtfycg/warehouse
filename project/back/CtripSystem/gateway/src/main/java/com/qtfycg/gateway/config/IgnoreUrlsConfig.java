package com.qtfycg.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@ConfigurationProperties(prefix = "security.ignore")
@Component
@Data
public class IgnoreUrlsConfig {
    private List<String> urls;
}
