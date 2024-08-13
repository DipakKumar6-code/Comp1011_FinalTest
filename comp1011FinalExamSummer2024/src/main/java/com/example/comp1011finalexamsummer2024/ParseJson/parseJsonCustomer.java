//package com.example.comp1011finalexamsummer2024.ParseJson;
//
//// Author: Dipak Bhul
//// Student Number: 200564268
//
//import com.example.comp1011finalexamsummer2024.Model.Customer;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
//public class parseJsonCustomer {
//
//    // Method to parse JSON file into ArrayList of Customer objects
//    public static ArrayList<Customer> loadCustomerData(String filePath) {
//        Gson gson = new Gson();
//        ArrayList<Customer> customers = new ArrayList<>();
//
//        try (FileReader reader = new FileReader(filePath)) {
//            Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
//            customers = gson.fromJson(reader, customerListType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return customers;
//    }
//
//    public static void main(String[] args) {
//        // Assuming the JSON file is in the resources directory
//        String filePath = "/com/example/comp1011finalexamsummer2024/customers.json";
//        ArrayList<Customer> customers = loadCustomerData(filePath);
//
//        // Print out customers for verification
//        for (Customer customer : customers) {
//            System.out.println(customer.getFirstName() + " " + customer.getLastName());
//        }
//    }
//}
//

package com.example.comp1011finalexamsummer2024.ParseJson;

import com.example.comp1011finalexamsummer2024.Model.Customer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class parseJsonCustomer {

    // Method to parse JSON file into ArrayList of Customer objects
    public static ArrayList<Customer> loadCustomerData(String filePath) {
        Gson gson = new Gson();
        ArrayList<Customer> customers = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(parseJsonCustomer.class.getResourceAsStream(filePath))) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
            customers = gson.fromJson(jsonObject.get("customers"), customerListType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
}





