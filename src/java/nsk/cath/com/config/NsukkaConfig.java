/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author LEOGOLD
 */
@Configuration
@EnableWebMvc
//@ImportResource("WEB-INF/spring-servlet.xml")
@ComponentScan(basePackages = "nsk.cath.com")
public class NsukkaConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry); //To change body of generated methods, choose Tools | Templates.
    }
    @Bean
    public ViewResolver viewResolver(){
        UrlBasedViewResolver vr =new  UrlBasedViewResolver();
        vr.setViewClass(FaceletView.class);
        vr.setPrefix("/view/");
        vr.setSuffix(".xhtml");
        return vr;
    
    
    }
}
