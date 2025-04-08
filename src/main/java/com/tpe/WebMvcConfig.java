package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe") //eger buraya parametre yazmasaydik, bagli oldugu package default tarayacakti
@EnableWebMvc//MVC yi etkinleştirir
public class WebMvcConfig implements WebMvcConfigurer { //WebMvcConfigurer interface bize gerekli metodlari verir

    //view resolver-->controller sayfanın ismini döndürür:students-->/WEB-INF/views/students.jsp
    @Bean
    public InternalResourceViewResolver viewResolver(){ //InternalResourceViewResolver klasi ile websiteyi buluyoruz
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");//view dosyası nerede:/WEB-INF/views/students
        viewResolver.setSuffix(".jsp");//dosyasnın uzantısı :/WEB-INF/views/students.jsp
        viewResolver.setViewClass(JstlView.class);
        //Jstl:Java Standar Tag Library: JSP içinde java kodu yerine etiket kullanmayı sağlar
        return viewResolver;
    }

    //handlermapping:statik sayfa içeren isteklerin servleta gönderilmesine gerek yok
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")//bu url ile gelen istekleri statik olarak sun, static olanlari Dispatcher Servlet e göndermez
                .addResourceLocations("/resources/")//statik olan kaynakların yeri
                .setCachePeriod(0);//cache periyodu için süre verilebilir.
    }
}
