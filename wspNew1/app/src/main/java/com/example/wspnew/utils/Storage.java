package com.example.wspnew.utils;

import com.example.wspnew.users.User;

import org.json.JSONArray;

public class Storage {
    public static String urlString = "https://api.jsonbin.io/v3/b/639cb034dfc68e59d56a0e4c", newsUrlString = "https://api.jsonbin.io/v3/b/63a22735dfc68e59d56d443c";
    public static String masterKey = "$2b$10$vpowIrRYKnDpIN1ZKuft1.oi7QxTZih7f2/H6.U44Kb1zj2laSuai";
    public static String accessKey = "$2b$10$uHrp/nRYrcO0CfunaTw8eeHid4Lplz.ohrq3PKmcW9A03briljT3y";
    public static JSONArray users;
    public static JSONArray news = new JSONArray();
    public static User currentUser;
}
