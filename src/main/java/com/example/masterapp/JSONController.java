package com.example.masterapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONController {
    JSONObject obj = new JSONObject();
    JSONArray array = new JSONArray();

    public JSONController(){
        this.readJson();
    }
    public void saveJson(String userName,String userPassword, String userRol){
        obj.put("usuario", userName);
        obj.put("password", userPassword);
        obj.put("rol",userRol);

        this.array.add(obj);

        try (FileWriter file = new FileWriter("users.json")) {
            file.write(this.array.toJSONString());
            file.flush();
        } catch (IOException e) {throw new RuntimeException(e);}
    }


    public void readJson(){
        try {
            JSONParser parser = new JSONParser();
            JSONArray data = (JSONArray) parser.parse(new FileReader("users.json"));
            this.array = data;
            String json = data.toJSONString();
            System.out.println(json);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getLista(){
        return this.array;
    }
}
