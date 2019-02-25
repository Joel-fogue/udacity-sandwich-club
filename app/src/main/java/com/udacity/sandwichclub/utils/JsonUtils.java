package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /****PUTTING THIS TEXT HERE AS A GUIDE TO HELP ME VISUALIZE WHAT I'M PARSING****/

    /*{
        "name ": {
        "mainName ": "Medianoche ",
        "alsoKnownAs ": [
        "Cuban Sandwich "
        ]
    },
        "placeOfOrigin ": "Cuba ",
            "description ": "Medianoche (   midnight   in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban communities in the United States. It is so named because of the sandwich 's popularity asa staple served in Havana 's night clubs right around or after midnight. ",
            "image ": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Sandwich_de_Medianoche.jpg/800px-Sandwich_de_Medianoche.jpg ",
            "ingredients ": [
                "Egg bread ",
                "Roast pork ",
                "Ham ",
                "Mustard ",
                "Swiss cheese ",
                "Dill pickles "
      ]
    }*/

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichObject = null;
        JSONObject nameJsonObject = null;
        String mainName=null;
        JSONArray alsoKnownAsItemArray=new JSONArray();
        JSONArray ingredientsArray=new JSONArray();
        List<String> alsoKnownAsList = new ArrayList<>();
        List<String> ingredientsList = new ArrayList<>();
        String placeOfOrigin=null, description=null, image=null;
        try {
            sandwichObject = new JSONObject(json);
            nameJsonObject = sandwichObject.getJSONObject("name");
            mainName = nameJsonObject.getString("mainName");
            placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            description = sandwichObject.getString("description");
            image = sandwichObject.getString("image");
            alsoKnownAsItemArray = nameJsonObject.getJSONArray("alsoKnownAs");
            ingredientsArray = sandwichObject.getJSONArray("ingredients");

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!alsoKnownAsItemArray.equals(null) && alsoKnownAsItemArray.length() !=0){
            for(int i=0; i<alsoKnownAsItemArray.length(); i++){
                try {
                    String singleOtherName = alsoKnownAsItemArray.get(i).toString();
                    alsoKnownAsList.add(singleOtherName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end loop
        }

        if(!ingredientsArray.equals(null) && ingredientsArray.length() !=0){
            for(int i=0; i<ingredientsArray.length(); i++){
                try {
                    String singleIngredient = ingredientsArray.get(i).toString();
                    ingredientsList.add(singleIngredient);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(ingredientsList.size());
            }//end for loop
        }

        Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
        return sandwich;
    }
}
