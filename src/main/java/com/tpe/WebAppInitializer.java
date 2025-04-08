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
/*
    1-Java ile Web uygulamaları geliştirirken gelen isteklerin ele alınıp
        uygun cevapların hazırlanması için Servlet classlar geliştirilir.
        SpringMVC ile bizim servlet class geliştirmemize gerek yoktur, SpringMVC
        yazdığımız kodları özel bir servlet class olan Dispatcher Servlet'a dönüştürür.

        2-Dispatcher Servlet aynı zamanda Front Controller(ön kontrolcü) görevi görür.
        Gelen istekleri merkezi olarak karşılar ilgili controllera yönlendirir. (Örneğin
        Student ile ilgili istekleri StudentController, Course ile ilgili istekleri
        CourseController'a yönlendirir). Bir "SEVK MEMURU" gibi çalışır.

        3-Handler Mapping : DispactherServlet ile karşılanan isteklerin uygun controllera yönlendirilmesini
        sağlar

        4-ModelAndView : Controllera gelen istek işlenir, model gerekirse manipule edilir ve
        cevap olarak model ile birlikte görüntülenecek sayfanın ismi ModelAndView objesinde birlikte
        gönderilir. Controllera gelen istek cevaplanırken ModelAndView veya String(sadece görüntülenecek
        sayfanın ismi veya yeniden yönlendirme) döndürülebilir.

        5-ViewResolver : Controllerdan gelen ModelAndView içindeki sayfa view katmanında bulunur, cevap olan
         model sayfanın içine yerleştirilir(bind edilir)

        6-View Katmanı : Görüntülenecek sayfalar bu katmanda yer alır, Dinamik veya statik sayfalar olabilir.


*/
