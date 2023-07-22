package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /*
    локация спринг конфигурации

    указывает, какой класс конфигурации Spring следует использовать
     для настройки контекста Spring MVC. В данном примере он возвращает массив,
      содержащий только один элемент - класс SpringConfig.class. Это означает,
       что конфигурация приложения будет загружаться из класса SpringConfig,
     который должен содержать настройки и бины для Spring контекста приложения.*/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }
/*
return new String[]{"/"};
Это означает, что ваш DispatcherServlet будет обрабатывать все HTTP-запросы,
которые приходят на корневой путь вашего веб-приложения ("/").*/
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
