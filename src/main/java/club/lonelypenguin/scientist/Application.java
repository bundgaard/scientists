/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.scientist;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author dbundgaard
 */
@SpringBootApplication
public class Application {
    
    /**
     *
     */
    public static final Class<Application> applicationClass = Application.class;
    
    public static final Logger log = LoggerFactory.getLogger(applicationClass);
    
    public static void main(String args[]){
        ApplicationContext ctx = SpringApplication.run(applicationClass, args);
        log.debug("Let's inspect the beans provided by Spring Boot:");
        String beans[] = ctx.getBeanDefinitionNames();
        Arrays.sort(beans);
        for(String s : beans){
            log.debug(s);
        }
    }
    
    @Bean
    public CommandLineRunner loadData(ScientistRepository repository){
        return (args) -> {
          repository.save(new Scientist("Albert Einstein","http://www.google.com", "Germany"));
          repository.save(new Scientist("Bill Nye","http://google.com/","America"));
          repository.save(new Scientist("Max Planck", "http://www.google.com/", "Germany"));
          
          log.info("Scientist found with findAll():");
          log.info("-------------------------------");
          for(Scientist scientist : repository.findAll()) {
              log.info(scientist.toString());
          }
          log.info("");
          
          Scientist scientistOne = repository.findOne(1L);
          log.info("Scientist found with findOne(1L):");
          log.info("---------------------------------");
          log.info(scientistOne.toString());
          log.info("");
          
          log.info("Scientist found with findByName():");
          log.info("-------------------------------------");
          for(Scientist s : repository.findByName("Albert Einstein")){
              log.info(s.toString());
          }
          log.info("");
          
          log.info("Scientist found by findByCountry():");
          log.info("-----------------------------------");
          for(Scientist s : repository.findByCountry("Germany")) {
              log.info(s.toString());
          }
          log.info("");
        };
    }
    
}
