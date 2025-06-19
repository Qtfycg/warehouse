package com.qtfycg.registry.discovery;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Component
public class ServiceRegistryUtil {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private Integer servicePort;

    @Value("${spring.cloud.consul.host:localhost}")
    private String consulHost;

    @Value("${spring.cloud.consul.port:8500}")
    private Integer consulPort;

    @Value("${spring.cloud.consul.discovery.health-check-path:/actuator/health}")
    private String healthPath;

    private ConsulClient consulClient;

    @PostConstruct
    public void init() {
        this.consulClient = new ConsulClient(consulHost, consulPort);
    }

    /**
     * 注册服务实例到 Consul
     */
    public void register() {
        String instanceId = serviceName + "-" + UUID.randomUUID();

        NewService service = new NewService();
        service.setId(instanceId);
        service.setName(serviceName);
        service.setPort(servicePort);
        service.setAddress("127.0.0.1"); // 可动态获取本机 IP

        // 添加标签或元数据
        service.setTags(Collections.singletonList("secure=false"));

        // 健康检查配置
        NewService.Check check = new NewService.Check();
        check.setHttp("http://127.0.0.1:" + servicePort + healthPath);
        check.setInterval("10s");
        check.setTimeout("3s");
        check.setDeregisterCriticalServiceAfter("30s");

        service.setCheck(check);

        consulClient.agentServiceRegister(service);

        log.info("✅ 注册服务到 Consul: [{}:{}]", serviceName, servicePort);
    }
}
