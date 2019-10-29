/*
 * Copyright (C)  Justson(https://github.com/Justson/DingDingHelper)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gary.commercial.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final Gson mGson;

    static {
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    private void JsonUtils() {
        throw new UnsupportedOperationException("This class unsupport create instance");
    }

    /**
     * 转化成Json字符串
     *
     * @param object
     * @return
     */
    public static String translateToJson(Object object) {
        return mGson.toJson(object);
    }

    public static String toJson(Object o) {
        return translateToJson(o);
    }

    /**
     * 解析Json字符串成实体类
     *
     * @param json
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T parserJson(String json, Class<T> cla) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = mGson;
        T t = null;
        try {
            t = gson.fromJson(json, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> listJson(String json, Class<T> cla) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        List<T> list = new ArrayList<>();
        Gson gson = mGson;
        try {
            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
            T t = null;
            for (JsonElement jsonElement : jsonArray) {
                t = gson.fromJson(jsonElement, cla);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<T> listParseAllJson(String json, Class<T> cla) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        List<T> list = null;
        try {
            list = JsonUtils.mGson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, Object> mapJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Map<String, Object> mMap = null;
        try {
            Gson mGson = JsonUtils.mGson;
            mMap = mGson.fromJson(json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mMap;
    }


    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson mGson = JsonUtils.mGson;
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    public static Gson getGson() {
        return JsonUtils.mGson;
    }
}

