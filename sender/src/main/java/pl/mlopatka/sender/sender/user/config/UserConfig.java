package pl.mlopatka.sender.sender.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("pl.mlopatka.rabbit")
public class UserConfig {

    private String exchange;
    private String routingKey;
}
