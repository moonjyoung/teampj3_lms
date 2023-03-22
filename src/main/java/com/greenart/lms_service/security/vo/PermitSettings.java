package com.greenart.lms_service.security.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "permission")
@Data
public class PermitSettings {
    String[] permitAllUrls;
}
