module animalshelter {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens gui;
    opens animals;
    opens webshop;
    exports gui;
}
