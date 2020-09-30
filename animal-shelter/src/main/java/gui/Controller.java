package gui;

import animals.Gender;
import animals.Species;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import webshop.Webshop;

import java.time.LocalDate;

public class Controller {

    private String animalName;
    private Gender animalGender;
    private Species animalSpecies;
    private LocalDate lastWalk;
    private String badHabbit;
    private String productName;
    private double productPrice;
    private String username;
    private double totalPrice;

    @FXML
    private GridPane grid;

    @FXML
    private TextField tfAnimalName;

    @FXML
    private ChoiceBox<?> cbAnimalGender;

    @FXML
    private RadioButton rbCat;

    @FXML
    private RadioButton rbDog;

    @FXML
    private DatePicker dpLastWalk;

    @FXML
    private TextField tfBadHabbit;

    @FXML
    private Button btAddAnimal;

    @FXML
    private TextField tfProductName;

    @FXML
    private Spinner<?> spProductPrice;

    @FXML
    private Button btAddProduct;

    @FXML
    private TextField tfUserName;

    @FXML
    private Button btReserveAnimal;

    @FXML
    private Button btWalkDog;

    @FXML
    private Button btAddToCart;

    @FXML
    private Button btRemoveFromCart;

    @FXML
    private ListView<?> lvAnimals;

    @FXML
    private ListView<?> lvProducts;

    @FXML
    private ListView<?> lvCart;

    @FXML
    private TextField tfTotalPrice;

    @FXML
    private Button btCheckout;

    private Webshop webshop;

    @FXML
    public void initialize() {
        webshop = new Webshop();
    }

    @FXML
    void buttonAddAnimalClicked(ActionEvent event) {

    }

    @FXML
    void buttonAddProductClicked(ActionEvent event) {

    }

    @FXML
    void buttonAddToCartClicked(ActionEvent event) {

    }

    @FXML
    void buttonCheckoutClicked(ActionEvent event) {

    }

    @FXML
    void buttonRemoveFromCartClicked(ActionEvent event) {

    }

    @FXML
    void buttonReserveAnimalClicked(ActionEvent event) {

    }

    @FXML
    void buttonWalkDogClicked(ActionEvent event) {

    }

    @FXML
    void animalNameChanged(ActionEvent actionEvent) {
        animalName = tfAnimalName.getText();
    }

    @FXML
    void lastWalkDateChanged(ActionEvent actionEvent) {
        lastWalk = dpLastWalk.getValue();
    }

    @FXML
    void badHabbitChanged(ActionEvent actionEvent) {
        badHabbit = tfBadHabbit.getText();
    }

    @FXML
    void productNameChanged(ActionEvent actionEvent) {
        productName = tfProductName.getText();
    }

    @FXML
    void usernameChanged(ActionEvent actionEvent) {
        username = tfUserName.getText();
    }
}
