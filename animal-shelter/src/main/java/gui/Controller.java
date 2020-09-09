package gui;

import javafx.fxml.FXML;
import webshop.Webshop;

public class Controller {

    private Webshop webshop;

    @FXML
    public void initialize() {
        webshop = new Webshop();
    }
}
