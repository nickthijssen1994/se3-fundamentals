package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import webshop.Webshop;

public class Controller {

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
}
