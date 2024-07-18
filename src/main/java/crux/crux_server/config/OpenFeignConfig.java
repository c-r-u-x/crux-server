package crux.crux_server.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "crux.crux_server")
public class OpenFeignConfig {
}
