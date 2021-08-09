//package net.javaguides.springboot.Security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsSecurity implements WebMvcConfigurer {
//    private final String[] CORS_ORIGIN_ALLOW = {"/login","/api/**"};
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        for (String cors : CORS_ORIGIN_ALLOW)
//            registry.addMapping(cors).allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
//    }
//}
