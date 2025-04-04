package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Java tabanlı web uygulamaları web.xml ile config edilir
//bu classı web.xml yerine kullanacağız

//AbstractAnnotationConfig... : DispatcherServlet konfigurasyomu ve başlatılması için grekli olan
//metodları(adımları) içerir
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { //dataya erisim : Hibernate, JDBC
        return new Class[] {
                RootConfig.class //springe root ile ilgili ayarlarin bu classta yapilacagini söylüyoruz
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //viewesolver, handlermapping
        return new Class[]{
                WebMvcConfig.class //ayarlar icin bu classa yönlediriyoruz
        };
    }

    @Override //hangi url ile gelen istekler karşılanacak
    protected String[] getServletMappings() {
        return new String[]{
                "/"  //base url den sonraki tüm pathleri karsila
        };

    }
}
