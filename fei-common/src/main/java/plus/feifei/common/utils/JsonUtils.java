package plus.feifei.common.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
    /**
     * Object转成JSON数据
     */
    public static String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return JSONObject.toJSONString(object);
    }

    public static<T> T toObj(String toString,Class<T> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(toString);
        return jsonObject.toJavaObject(clazz);
    }
}
