package gui;

import animals.Gender;
import animals.Species;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import webshop.Webshop;

import java.time.LocalDate;

public class Controller {

    private String animalName;
    private Gender animalGender;
    private Species animalSpecies;
    private LocalDate lastWalk;
    private String badHabbit;
    private String productName;
    private int productPrice = 0;
    private String username;
    private int totalPrice = 0;

    @FXML
    private GridPane grid;

    @FXML
    private TextField tfAnimalName;

    @FXML
    private ChoiceBox<Species> cbAnimalSpecies;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    private ToggleGroup tgGender;

    @FXML
    private DatePicker dpLastWalk;

    @FXML
    private TextField tfBadHabbit;

    @FXML
    private Button btAddAnimal;

    @FXML
    private TextField tfProductName;

    @FXML
    private Spinner<Integer> spProductPrice;

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
        tgGender = new ToggleGroup();
        rbMale.setToggleGroup(tgGender);
        rbMale.setUserData(Gender.MALE);
        rbFemale.setToggleGroup(tgGender);
        rbFemale.setUserData(Gender.FEMALE);
        rbMale.setSelected(true);
        tgGender.selectedToggleProperty().addListener((observableValue, oldToggle, newToggle) -> {
            animalGender = (Gender) tgGender.getSelectedToggle().getUserData();
            System.out.println(animalGender.getDescription());
        });

        cbAnimalSpecies.getItems().addAll(Species.values());
        cbAnimalSpecies.setConverter(new StringConverter<>() {
            @Override
            public String toString(Species species) {
                return species.getDescription();
            }

            @Override
            public Species fromString(String s) {
                return null;
            }
        });
        cbAnimalSpecies.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSpecies, newSpecies) -> animalSpecies = newSpecies);
        cbAnimalSpecies.setValue(Species.CAT);

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, productPrice);
        spProductPrice.setValueFactory(valueFactory);
        spProductPrice.valueProperty().addListener((observable, oldValue, newValue) -> {
            productPrice = newValue;
        });

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
