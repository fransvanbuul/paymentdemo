package com.example.paymentdemo.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
public class AxonConfig {

    @Autowired
    public void config(EventHandlingConfiguration config) {
        config.usingTrackingProcessors();
    }

    @Autowired
    public void config(Serializer serializer) {
        if(serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer)serializer).getXStream();
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[] { "com.example.paymentdemo.**", "org.axonframework.**" });
        }
    }

    @Qualifier("eventSerializer")
    @Bean
    public Serializer eventSerializer(ObjectMapper objectMapper) {
        return new JacksonSerializer(objectMapper);
    }

}
