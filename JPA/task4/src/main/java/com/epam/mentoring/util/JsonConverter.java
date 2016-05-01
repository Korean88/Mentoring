package com.epam.mentoring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Andrey on 03.04.2016.
 */

@Component
public class JsonConverter {

    private Logger LOG = Logger.getLogger(JsonConverter.class);

    public String convertToJson(Object entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = "";
        try {
            res = objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            LOG.error("Could not convert " + entity.getClass().getName() + " to JSON", e);
        }
        return res;
    }

    public Object convertFromJson(String json, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object res = null;
        try {
            res = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("Could not convert json to object of class " + clazz.getName(), e);
        }
        return res;
    }
}
