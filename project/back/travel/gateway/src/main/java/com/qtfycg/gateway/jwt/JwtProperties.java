package com.qtfycg.gateway.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "travel.auth")
@Component
public class JwtProperties {
    private String secret;
    private List<String> whitePaths;
}
