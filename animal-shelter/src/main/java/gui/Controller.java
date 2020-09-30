package gui;

import animals.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import webshop.AnimalShelterGUI;
import webshop.AnimalShelterWebshop;
import webshop.Product;
import webshop.Sellable;

import java.time.LocalDate;
import java.util.List;

public class Controller implements AnimalShelterGUI {

    private String animalName;
    private Gender animalGender;
    private Species animalSpecies;
    private LocalDate lastWalk;
    private String badHabbit;
    private String productName;
    private int productPrice = 0;
    private String username;
    private int totalPrice = 0;
    private ObservableList<String> animals = FXCollections.observableArrayList();
    private ObservableList<String> products = FXCollections.observableArrayList();
    private ObservableList<String> sellables = FXCollections.observableArrayList();

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
    private ListView<String> lvAnimals;

    @FXML
    private ListView<String> lvProducts;

    @FXML
    private ListView<String> lvCart;

    @FXML
    private TextField tfTotalPrice;

    @FXML
    private Button btCheckout;

    private AnimalShelterWebshop animalShelterWebshop;

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
        lvAnimals.setItems(animals);
        lvProducts.setItems(products);
        lvCart.setItems(sellables);

        animalShelterWebshop = new AnimalShelterWebshop();
        animalShelterWebshop.registerGUI(this);
    }

    @FXML
    void buttonAddAnimalClicked(ActionEvent event) {
        switch (animalSpecies) {
            case CAT:
                animalShelterWebshop.addAnimal(new Cat(tfAnimalName.getText(),animalGender,tfBadHabbit.getText()));
                break;
            case DOG:
                animalShelterWebshop.addAnimal(new Dog(tfAnimalName.getText(),animalGender));
                break;
        }
    }

    @FXML
    void buttonAddProductClicked(ActionEvent event) {
        animalShelterWebshop.addProduct(new Product(tfProductName.getText(), spProductPrice.getValue()));
    }

    @FXML
    void buttonAddToCartClicked(ActionEvent event) {

    }

    @FXML
    void buttonCheckoutClicked(ActionEvent event) {
        animalShelterWebshop.checkoutCart();
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

    @Override
    public void updateAnimals(List<Animal> animals) {
        this.animals.setAll(animals.toString());
    }

    @Override
    public void updateInventory(List<Product> products) {
        this.products.setAll(products.toString());
    }

    @Override
    public void updateShoppingCart(List<Sellable> sellables) {
        this.sellables.setAll(sellables.toString());
    }

    @Override
    public void updateTotalPrice(int totalPrice) {
        tfTotalPrice.setText(String.valueOf(totalPrice));
    }
}
