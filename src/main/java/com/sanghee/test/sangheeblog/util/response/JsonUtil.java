package com.sanghee.test.sangheeblog.util.response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {


    public static String writeValueAsString(ResponseData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(data);
        return value;
    }

    public static String writeValueAsString(Map<String, Object> data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(data);
        return value;
    }

    public static String writeValueAsString(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(data);
        return value;
    }


    @SuppressWarnings("unchecked")
    public static Map<String, String> fromObjectToMap(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(obj, Map.class);
    }

    public static Object convertFromTo(String src, Class<?> clz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(src, clz);
    }

    public static List<Object> jsonContentToList(String string) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(string, objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));
    }

    public static Object jsonStrToObject(String string) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(string, Object.class);
    }

}
