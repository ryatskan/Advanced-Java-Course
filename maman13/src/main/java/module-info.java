module q {
    requires javafx.controls;
    requires javafx.fxml;


    opens q1 to javafx.fxml;
    exports q1;
    opens q2 to javafx.fxml;
    exports q2;
}