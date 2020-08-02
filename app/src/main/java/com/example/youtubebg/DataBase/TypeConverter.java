package com.example.youtubebg.DataBase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TypeConverter  {
    Gson gson = new Gson();

    @androidx.room.TypeConverter
    public List<String> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public  String someObjectListToString(List<String> someObjects) {
        return gson.toJson(someObjects);
    }
}
