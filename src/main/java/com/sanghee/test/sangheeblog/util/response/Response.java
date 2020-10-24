package com.sanghee.test.sangheeblog.util.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sanghee.test.sangheeblog.model.DataInterface;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    public static String toJson(ResponseData data) throws JsonProcessingException {
        return JsonUtil.writeValueAsString(data);
    }

    public static String toJson(DataInterface data) throws JsonProcessingException {
        return JsonUtil.writeValueAsString(data);
    }

//    public static String toJson(Map<String, Object> map) {
//        return JSONObject.toJSONString(map);
//    }
//
//    public static String toJsonMap(Map<String, List<? extends DataInterface>> map) {
//        return JSONObject.toJSONString(map);
//    }

    public static String SUCCESS(List<? extends DataInterface> data) throws JsonProcessingException {
        return JsonUtil.writeValueAsString(ResponseData.SUCCESS(data));
    }

    public static String SUCCESS(Object data) throws JsonProcessingException {
        return JsonUtil.writeValueAsString(data);
    }

    public static String FAIL() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "ImoInteralError");
        map.put("code", ResponseResult.FAIL.getStrCode());
        return JSONObject.toJSONString(map);
    }

    public static String FAIL(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("code", ResponseResult.FAIL.getStrCode());
        return JSONObject.toJSONString(map);
    }

    public static String FAIL(int code, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("code", code);
        return JSONObject.toJSONString(map);
    }

    public static String SUCCESS(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("code", ResponseResult.OK.getStrCode());
        return JSONObject.toJSONString(map);
    }

    public static String SUCCESS() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "");
        map.put("code", ResponseResult.OK.getStrCode());
        return JSONObject.toJSONString(map);
    }

    public static String SUCCESS(JSONObject obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ResponseResult.OK.getStrCode());
        map.put("singleData", obj);
        return JSONObject.toJSONString(map);
    }

    public static String SUCCESS(JSONObject obj, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ResponseResult.OK.getStrCode());
        map.put("message", message);
        map.put("singleData", obj);
        return JSONObject.toJSONString(map);
    }

    public static String Result(JSONObject obj, String message, String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("message", message);
        map.put("singleData", obj);
        return JSONObject.toJSONString(map);
    }

}
