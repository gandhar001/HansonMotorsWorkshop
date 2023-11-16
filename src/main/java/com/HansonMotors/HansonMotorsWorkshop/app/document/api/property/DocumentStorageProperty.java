package com.HansonMotors.HansonMotorsWorkshop.app.document.api.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "document")
@Data
@Component
public class DocumentStorageProperty {

  private String uploadDirectory;

}
