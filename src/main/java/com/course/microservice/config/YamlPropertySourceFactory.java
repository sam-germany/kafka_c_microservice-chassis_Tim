package com.course.microservice.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {

  //      System.out.println(name+"########");
  //      System.out.println(resource+"-----");

        Properties propertiesFromYaml = loadYamlIntoProperties22(resource);
        String sourceName = name != null ? name : resource.getResource().getFilename();

        return new PropertiesPropertySource(sourceName, propertiesFromYaml);
    }

    private Properties loadYamlIntoProperties22(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();

        return factory.getObject();

        } catch (IllegalStateException e) {
            // for ignoreResourceNotFound
            Throwable cause = e.getCause();

            if (cause instanceof FileNotFoundException) throw (FileNotFoundException) e.getCause();

           throw e;
        }
    }
}
/*(1)
createPropertySource(@Nullable String name, EncodedResource resource)

@Nullable String name   <-- as we do not pass any name, so no exception as @Nullable is their , name=null is alloweed

EncodedResource resource  <--  as in the ExternalConfig class we are passing 3 path as
              value = { "classpath:microservice.yml","file:./conf/microservice.yml", "file:c:/conf/microservice.yml" }
these 3 path will come as resource as argument
(2)
rest all code is just to fetch the  key-value pairs from the  microservice.yml file , so i do not go deep into it

 */
