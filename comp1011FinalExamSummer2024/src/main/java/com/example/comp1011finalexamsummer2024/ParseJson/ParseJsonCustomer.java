// Author Name: Dipak Bhul
// Student Number: 200564268

package com.example.comp1011finalexamsummer2024.ParseJson;

import com.example.comp1011finalexamsummer2024.Model.Customer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParseJsonCustomer {

    // Method to parse JSON file into List of Customer objects
    public static List<Customer> loadCustomerData(String filePath) {
        Gson gson = new Gson();
        List<Customer> customers = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(ParseJsonCustomer.class.getResourceAsStream(filePath))) {
            if (reader == null) {
                throw new RuntimeException("Resource not found: " + filePath);
            }

            // Parse the JSON data
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            System.out.println("JSON Data: " + jsonElement);  // Print JSON structure for debugging

            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.has("customers")) {
                    JsonArray jsonArray = jsonObject.getAsJsonArray("customers");
                    Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
                    customers = gson.fromJson(jsonArray, customerListType);
                } else {
                    throw new IllegalStateException("Expected 'customers' key in JSON object.");
                }
            } else if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
                customers = gson.fromJson(jsonArray, customerListType);
            } else {
                throw new IllegalStateException("Unexpected JSON structure.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
}









