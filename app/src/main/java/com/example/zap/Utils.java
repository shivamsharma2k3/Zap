package com.example.zap;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;
    private static int ORDER_ID = 0;

    private static final String ALL_ITEMS_KEY = "all_items";
    private static final String DB_NAME = "fake_database";
    private static final String CART_ITEMS_KEY = "cart_items";
    private static Gson gson = new Gson();
    private static Type listType = new TypeToken<ArrayList<Items>>(){}.getType();

    public static void initSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<Items> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), listType);
        if (null == allItems) {
            initAllItems(context);
        }
    }

    public static void clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private static void initAllItems(Context context) {
        ArrayList<Items> allItems = new ArrayList<>();
        Items ice = new Items("Ice Cream", "Milk is a nutrient-rich, white liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for infant mammals before they are able to digest other types of food.",
                "https://www.milkmaid.in/sites/default/files/2022-03/Strawberry-IceCream-590x436.jpg",
                "Drink", 70, 8);
        allItems.add(ice);

        Items milk = new Items("Milk", "Milk is a nutrient-rich, white liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for infant mammals before they are able to digest other types of food.",
                "https://purewows3.imgix.net/images/articles/2022_05/milk-subsitutes.jpg?auto=format,compress&cs=strip",
                "Drink", 50, 8);
        allItems.add(milk);

        Items soda = new Items("Soda", "A soft drink is a drink that usually contains carbonated water, a sweetener, and a natural or artificial flavoring. The sweetener may be a sugar, high-fructose corn syrup, fruit juice, a sugar substitute, or some combination of these",
                "https://www.eatthis.com/wp-content/uploads/sites/4/2021/06/soda-2.jpg?quality=82&strip=1",
                "Drink", 40, 15);
        allItems.add(soda);

        Items shampoo = new Items("Shampoo", "Shampoo is a hair care product, typically in the form of a viscous liquid, that is used for cleaning hair. Less commonly, shampoo is available in bar form, like a bar of soap. Shampoo is used by applying it to wet hair, massaging the product into the scalp, and then rinsing it out. Some users may follow a shampooing with the use of hair conditioner.",
                "https://asset-americas.unileversolutions.com/content/dam/unilever/tresemme/united_states_of_america/pack_shot/category_pages_keratin_smooth_shampoo-32461748-jpg.jpg.ulenscale.375x375.jpg",
                "Cleanser", 120, 9);
        allItems.add(shampoo);

        Items spaghetti = new Items("Spaghetti",
                "Spaghetti is a long, thin, solid, cylindrical pasta. It is a staple food of traditional Italian cuisine. Like other pasta, spaghetti is made of milled wheat and water and sometimes enriched with vitamins and minerals. Italian spaghetti is typically made from durum wheat semolina.",
                "https://veganwithgusto.com/wp-content/uploads/2021/05/speedy-spaghetti-arrabbiata-featured-e1649949762421.jpg",
                "Food", 60, 6);

        allItems.add(spaghetti);

        Items soap = new Items("Soap", "Soap is a salt of a fatty acid[1] used in a variety of cleansing and lubricating products. In a domestic setting, soaps are surfactants usually used for washing, bathing, and other types of housekeeping. In industrial settings, soaps are used as thickeners, components of some lubricants, and precursors to catalysts.",
                "https://cdn.shopify.com/s/files/1/2690/0106/products/CeramicLiquidSoapDispenser_11.jpg?v=1614666913",
                "Cleanser", 25, 14);
        allItems.add(soap);

        Items juice = new Items("Juice", "Juice is a drink made from the extraction or pressing of the natural liquid contained in fruit and vegetables. It can also refer to liquids that are flavored with concentrate or other biological food sources, such as meat or seafood, such as clam juice. Juice is commonly consumed as a beverage or used as an ingredient or flavoring in foods or other beverages, as for smoothies. Juice emerged as a popular beverage choice after the development of pasteurization methods enabled its preservation without using fermentation (which is used in wine production)",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRikvBfTIKtQjpn3JUZl-Ob7SHbsGDFBOTihA&usqp=CAU",
                "Drink", 40, 25);
        allItems.add(juice);

        Items walnut = new Items("Walnut", "A walnut is the nut of any tree of the genus Juglans (Family Juglandaceae), particularly the Persian or English walnut, Juglans regia. A walnut is the edible seed of a drupe, and thus not a true botanical nut. It is commonly consumed as a nut.",
                "https://5.imimg.com/data5/HZ/AB/FX/SELLER-84813594/walnut-kernel-500x500.jpg",
                "Nuts", 200, 4);
        allItems.add(walnut);

        Items pistachio = new Items("Pistachio", "The pistachio (/pɪˈstɑːʃiˌoʊ, -ˈstæ-/, Pistacia vera), a member of the cashew family, is a small tree originating from Central Asia and the Middle East. The tree produces seeds that are widely consumed as food. Pistacia vera often is confused with other species in the genus Pistacia that are also known as pistachio.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzUPj-thZtjYYuul0nfRIhq_on4PsE94AXbA&usqp=CAU",
                "Nuts", 150, 15);
        allItems.add(pistachio);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItems));
        editor.commit();
    }

    public static ArrayList<Items> getAllItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<Items> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), listType);
        return allItems;
    }

    public static void changeRate(Context context, int itemId, int newRate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ArrayList<Items> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), listType);
        if (null != allItems) {
            ArrayList<Items> newItems = new ArrayList<>();
            for (Items i: allItems) {
                if (i.getId() == itemId) {
                    i.setRate(newRate);
                    newItems.add(i);
                }else {
                    newItems.add(i);
                }
            }

            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static void addReview(Context context, Review review) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            ArrayList<Items> newItems = new ArrayList<>();
//            for (Items i: allItems) {
//                if (i.getId() == review.getGroceryItemId()) {
//                    ArrayList<Review> reviews = i.getReviews();
//                    reviews.add(review);
//                    i.setReviews(reviews);
//                    newItems.add(i);
//                }else {
//                    newItems.add(i);
//                }
//            }

            editor.remove(ALL_ITEMS_KEY);
//            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static ArrayList<Review> getReviewById(Context context, int itemId) {
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            for (Items i: allItems) {
//                if (i.getId() == itemId) {
//                    ArrayList<Review> reviews = i.getReviews();
//                    return reviews;
//                }
            }
        }

        return null;
    }

    public static void addItemToCart (Context context, Items item) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<Items> cartItems = gson.fromJson(sharedPreferences.getString(CART_ITEMS_KEY, null), listType);
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        cartItems.add(item);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_ITEMS_KEY);
        editor.putString(CART_ITEMS_KEY, gson.toJson(cartItems));
        editor.commit();
    }

    public static ArrayList<Items> getCartItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<Items> cartItems = gson.fromJson(sharedPreferences.getString(CART_ITEMS_KEY, null), listType);
        return cartItems;
    }

    public static ArrayList<Items> searchForItems(Context context, String text) {
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            ArrayList<Items> items = new ArrayList<>();
            for (Items item: allItems) {
                if (item.getName().equalsIgnoreCase(text)) {
                    items.add(item);
                }

                String[] names = item.getName().split(" ");
                for (int i=0; i<names.length; i++) {
                    if (text.equalsIgnoreCase(names[i])) {
                        boolean doesExist = false;

                        for (Items j: items) {
                            if (j.getId() == item.getId()) {
                                doesExist = true;
                            }
                        }

                        if (!doesExist) {
                            items.add(item);
                        }
                    }
                }
            }

            return items;
        }

        return null;
    }

    public static ArrayList<String> getCategories(Context context) {
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            ArrayList<String> categories = new ArrayList<>();
            for (Items item: allItems) {
                boolean doesExist = false;
                for (String s: categories) {
                    if (item.getCategory().equals(s)) {
                        doesExist = true;
                    }
                }

                if (!doesExist) {
                    categories.add(item.getCategory());
                }
            }

            return categories;
        }

        return null;
    }

    public static ArrayList<Items> getItemsByCategory (Context context, String category) {
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            ArrayList<Items> items = new ArrayList<>();
            for (Items item: allItems) {
                if (item.getCategory().equals(category)) {
                    items.add(item);
                }
            }

            return items;
        }

        return null;
    }

    public static void deleteItemFromCart(Context context, Items item) {
        ArrayList<Items> cartItems = getCartItems(context);
        if (null != cartItems) {
            ArrayList<Items> newItems = new ArrayList<>();
            for (Items i: cartItems) {
                if (i.getId() != item.getId()) {
                    newItems.add(i);
                }
            }

            SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(CART_ITEMS_KEY);
            editor.putString(CART_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static void clearCartItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_ITEMS_KEY);
        editor.commit();
    }

    public static void increasePopularityPoint(Context context, Items item, int points) {
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            ArrayList<Items> newItems = new ArrayList<>();
            for (Items i: allItems) {
                if (i.getId() == item.getId()) {
                    i.setPopularityPoint(i.getPopularityPoint() + points);
                    newItems.add(i);
                }else {
                    newItems.add(i);
                }
            }

            SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(ALL_ITEMS_KEY);
            Gson gson =new Gson();
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static void changeUserPoint (Context context, Items item, int points) {
        Log.d(TAG, "changeUserPoint: Attempting to add " + points + " to " + item.getName());
        ArrayList<Items> allItems = getAllItems(context);
        if (null != allItems) {
            ArrayList<Items> newItems = new ArrayList<>();
            for (Items i: allItems) {
                if (i.getId() == item.getId()) {
                    i.setUserPoint(i.getUserPoint() + points);
                }

                newItems.add(i);
            }

            SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static int getID() {
        ID++;
        return ID;
    }

    public static int getOrderId() {
        ORDER_ID++;
        return ORDER_ID;
    }

}