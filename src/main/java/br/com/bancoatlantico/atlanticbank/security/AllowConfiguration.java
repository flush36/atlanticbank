package br.com.bancoatlantico.atlanticbank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AllowConfiguration implements WebMvcConfigurer {

	public static final String AUTHETICATION = "permission";
	
	@Bean
	public WebFilterInterceptor getWebFilterInterceptor() {
		return new WebFilterInterceptor();
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getWebFilterInterceptor())
                .addPathPatterns("/**/*")
                .excludePathPatterns("/usuario/logar");
    }
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders(
                        "Authorization",
                        "Content-Type",
                        "X-Requested-With",
                        "accept",
                        "Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers"
                );
    }
}
