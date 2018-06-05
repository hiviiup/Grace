package com.evayinfo.grace.http;

/**
 * Created by hiviiup on 16/11/27.
 */

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
//        ResultResponse 只解析result字段
            HttpResult httpResult = gson.fromJson(response, HttpResult.class);

            if (httpResult.isFlag()) {
                T responseBody = getResponseBody(response);
                return responseBody;
            } else {
                //ErrResponse 将msg解析为异常消息文本
                throw new RuntimeException(httpResult.getMsg());
            }
        } finally {
        }
    }

    private T getResponseBody(String response) {
        if (type instanceof Class) {
            Class clazz = (Class) type;
            if (clazz.equals(JSONObject.class)) {//如果返回类型 为JsonObject
                try {
                    return (T) new JSONObject(response);
                } catch (JSONException e) {

                }
            }
            if (clazz.equals(JSONArray.class)) {
                try {
                    return (T) new JSONArray(response);
                } catch (JSONException e) {

                }
            }
        }
        return gson.fromJson(response, type);
    }
}

