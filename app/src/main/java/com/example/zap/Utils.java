package com.example.zap;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;

    private static final String ALL_ITEMS_KEY = "all_items";
    private static final String DB_NAME = "fake_database";
    private static Gson gson = new Gson();
    private static Type listType = new TypeToken<ArrayList<Items>>(){}.getType(); //to convert gson into arraylist

    public static void initSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        //looking inside sharedPreferences using gson
        ArrayList<Items> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), listType); //Creating ArrayList from SharedPreferences
        if (null == allItems) {
            initAllItems(context);
        }
    }

    private static void initAllItems(Context context) {
        ArrayList<Items> allItems = new ArrayList<>(); //created allItems ArrayList
        Items milk = new Items("Milk",
                "Milk is a nutrient-rich, white liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for infant mammals before they are able to digest other types of food.",
                "https://chriskresser.com/wp-content/uploads/raw-milk-1-e1563894986431.jpg",
                "drink", 100, 8);
        allItems.add(milk);

        Items iceCream = new Items("Ice Cream", "Delicious",
                "https://images.media-allrecipes.com/userphotos/453291.jpg",
                "food", 200, 10);
        iceCream.setPopularityPoint(10);
        iceCream.setUserPoint(7);
        allItems.add(iceCream);

        Items soda = new Items("Soda", "Tasty",
                "https://i0.wp.com/post.healthline.com/wp-content/uploads/2021/03/soda-beverage-bubbles-1296x728-header.jpg?w=1155&h=1528",
                "Drink", 50, 15);
        soda.setPopularityPoint(5);
        soda.setUserPoint(15);
        allItems.add(soda);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE); //creating SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();//creating editor
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItems));//using Gson library to put ArrayList inside our SharedPreferences
        editor.commit(); //to commit changes
    }

    public static ArrayList<Items> getAllItems(Context context) { //we need context to instantiate SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<Items> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), listType);
        return allItems;
    }

    public static int getID() {
        ID++;
        return ID;
    }
}
