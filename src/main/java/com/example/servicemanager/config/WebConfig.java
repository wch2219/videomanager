package com.example.servicemanager.config;

        import org.springframework.beans.factory.annotation.Configurable;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configurable
public class WebConfig extends WebMvcConfigurationSupport {

//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE",
//                "OPTIONS", "TRACE");
//
//
//
//    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:D://upload/");

    }
}
