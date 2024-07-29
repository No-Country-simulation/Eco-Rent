package tech.nocountry.eco_rent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    logger.info("Configuring resource handlers for static content");
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }
}
