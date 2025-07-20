package book.store.api.v1.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.*;

public class jsonToObject {
    private Gson gson = new Gson();
    public List<Map<String, Object>> listObject(String responseBody){
        return gson.fromJson(responseBody, new TypeToken<List<Map<String, Object>>>() {}.getType());

    }
    public Map<String, Object> toObject(String responseBody){
        return gson.fromJson(responseBody, new TypeToken<Map<String, Object>>() {}.getType());

    }
}
