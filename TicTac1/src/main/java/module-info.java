module com.example.tictac1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictac1 to javafx.fxml;
    exports com.example.tictac1;
}