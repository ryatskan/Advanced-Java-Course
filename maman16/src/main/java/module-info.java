module q {
    requires javafx.controls;
    requires javafx.fxml;


    opens q1 to javafx.fxml;
    exports q1;
}