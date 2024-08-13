
module com.example.comp1011finalexamsummer2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    // Open packages to JavaFX and Gson for reflection
    opens com.example.comp1011finalexamsummer2024.Controllers to javafx.fxml;
    opens com.example.comp1011finalexamsummer2024.Model to com.google.gson;
    opens com.example.comp1011finalexamsummer2024.ParseJson to com.google.gson;

    // Export packages for other modules to use
    exports com.example.comp1011finalexamsummer2024;
    exports com.example.comp1011finalexamsummer2024.Model;
    exports com.example.comp1011finalexamsummer2024.Controllers;
    exports com.example.comp1011finalexamsummer2024.ParseJson;
}
