package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
/*@Configuration: Это аннотация Spring, которая указывает, что этот класс
 содержит конфигурацию бинов (компонентов) для Spring контекста приложения.*/
@Configuration
/*@ComponentScan("java"): Эта аннотация указывает Spring сканировать пакет
"java" для поиска классов, помеченных аннотациями компонентов (@Component, @Service, @Controller и т.д.).
 Это позволяет Spring обнаруживать и автоматически создавать бины на основе этих классов.*/
@ComponentScan("java")
/*@EnableWebMvc: Эта аннотация активирует поддержку Spring MVC в приложении.*/
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    /*Приватное поле класса, которое хранит ссылку на контекст приложения Spring.*/
    private final ApplicationContext applicationContext;

    /*@Autowired: Эта аннотация указывает, что контекст приложения Spring будет автоматически внедрен в конструктор класса.
     Внедрение зависимости позволяет использовать ApplicationContext внутри класса без явного создания экземпляра.*/
    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /*@Bean: Методы, помеченные этой аннотацией, указывают, что они создают и возвращают Spring bean.*/

    /*Этот метод создает и настраивает объект SpringResourceTemplateResolver,
    который отвечает за разрешение и обработку шаблонов Thymeleaf.*/
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }



/*Этот метод создает и настраивает объект SpringTemplateEngine, который представляет собой движок для обработки шаблонов Thymeleaf.*/
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
/*Этот метод настраивает реестр разрешителей представлений в Spring MVC.*/
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
/*Таким образом, класс SpringConfig выполняет конфигурацию Spring MVC для использования
 Thymeleaf в качестве шаблонизатора, настраивая разрешитель представлений для обработки шаблонов Thymeleaf.
 Это позволяет создавать динамические веб-страницы, вставляя данные из серверной части приложения в HTML-шаблоны.*/