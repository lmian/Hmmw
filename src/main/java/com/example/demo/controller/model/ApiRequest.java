package com.example.demo.controller.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * @author limian
 * @time 2023-11-17
 **/
public class ApiRequest<T> {

    public T data;
    public String sign;
    public long timestamp;

    @Override
    public String toString() {
        return javaBeanToJSON(this).toString();
    }

    public static JSONObject javaBeanToJSON(Object object) {
        return JSONObject.parseObject(JSONObject.toJSONString(object), Feature.OrderedField);
    }

}