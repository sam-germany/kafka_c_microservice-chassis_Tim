package com.course.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//(video 10)
@Configuration
@PropertySources({ @PropertySource(   factory = YamlPropertySourceFactory.class,
		                               value = { "classpath:microservice.yml",
	                                             "file:./conf/microservice.yml",
						                         "file:c:/conf/microservice.yml"
				                                },
		                               ignoreResourceNotFound = true)})
public class ExternalConfig {
}

/*
(1)
@Configuration
@PropertySources( @PropertySource, @PropertySource)   <-- we can provide many PropertySource   in Sources.

@PropertySource <-- (video 10) this Annotation tell Spring to look for the file name  microservice.yml  from one of
                  the given class path, as above we have define 3 path their, this is a default name microservice.yml
in this file microservices.yml  we have defined   key-value  pairs as we are defining in the .properties file and
after that anywhere in the  application we can call that  @Value("${title}")  and value will be fetched form this
microservice.yml

Note in our example above   "file:./conf/microservice.yml",   <-- by this way the microservice.yml file is called
(2)
factory = YamlPropertySourceFactory.class  <-- the main logic of retreiving these key-value is written in the
                                                YamlPropertySourceFactory class

(3)
ignoreResourceNotFound = true   <-- if in above example it Spring does not found any microservice.yml file
                                    in all 3 locations then it should not throw any exception
 */