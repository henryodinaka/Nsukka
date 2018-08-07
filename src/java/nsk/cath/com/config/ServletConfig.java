/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author LEOGOLD
 */
@Configuration
//@ImportResource("WEB-INF/spring-servlet.xml")
@EnableWebMvc
public class ServletConfig extends WebMvcConfigurerAdapter{
    
//    @Bean
//    public UrlBasedViewResolver faceletViewResolver(){
//        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
//        viewResolver.setViewClass(FaceletView.class);
//        viewResolver.setPrefix("/view/");
//        viewResolver.setSuffix(".xhtml");
//        return viewResolver;
//    }
//    @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/*.xhtml").addResourceLocations("/view/");
//}
//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/").setViewName("forward:index.xhtml");
//  }
}
