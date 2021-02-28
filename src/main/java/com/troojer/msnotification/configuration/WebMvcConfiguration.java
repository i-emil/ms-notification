package com.troojer.msnotification.configuration;


import com.troojer.msnotification.interceptor.MDCInterceptor;
import com.troojer.msnotification.interceptor.UserHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final MDCInterceptor mdcInterceptor;
    private final UserHandlerInterceptor userHandlerInterceptor;

    public WebMvcConfiguration(MDCInterceptor mdcInterceptor, UserHandlerInterceptor userHandlerInterceptor) {
        this.mdcInterceptor = mdcInterceptor;
        this.userHandlerInterceptor = userHandlerInterceptor;
    }

    // Static Resource Config
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userHandlerInterceptor).order(10);
        registry.addInterceptor(mdcInterceptor).order(2);
    }

}
