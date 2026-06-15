package vdg.marcha.puertollano.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "shelly")
public class ShellyProperties {

    private String ip;

    private Integer relay;

    private Integer pulseDuration;

}
