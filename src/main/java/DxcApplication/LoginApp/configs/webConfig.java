package DxcApplication.LoginApp.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Adjust the origin as per your Angular app's URL
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(true);
    }
}
