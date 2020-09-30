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

    private final ObservableList<Animal> animalObservableList = FXCollections.observableArrayList();
    private final ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private final ObservableList<Sellable> sellableObservableList = FXCollections.observableArrayList();
    private String animalName = "";
    private Gender animalGender = Gender.MALE;
    private Species animalSpecies = Species.CAT;
    private LocalDate lastWalk;
    private String badHabit = "";
    private String productName = "";
    private int productPrice = 0;
    private String username = "";
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
    private TextField tfBadHabit;

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
    private ListView<Animal> lvAnimals;

    @FXML
    private ListView<Product> lvProducts;

    @FXML
    private ListView<Sellable> lvCart;

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

        cbAnimalSpecies.getItems().setAll(Species.values());
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
        lvAnimals.setItems(animalObservableList);
        lvAnimals.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
        lvProducts.setItems(productObservableList);
        lvProducts.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
        lvCart.setItems(sellableObservableList);
        lvCart.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Sellable item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        animalShelterWebshop = new AnimalShelterWebshop();
        animalShelterWebshop.registerGUI(this);
    }

    @FXML
    void buttonAddAnimalClicked(ActionEvent event) {
        switch (animalSpecies) {
            case CAT:
                animalShelterWebshop.addAnimal(new Cat(tfAnimalName.getText(), animalGender, tfBadHabit.getText()));
                break;
            case DOG:
                animalShelterWebshop.addAnimal(new Dog(tfAnimalName.getText(), animalGender));
                break;
        }
    }

    @FXML
    void buttonAddProductClicked(ActionEvent event) {
        animalShelterWebshop.addProduct(new Product(tfProductName.getText(), spProductPrice.getValue()));
    }

    @FXML
    void buttonAddToCartClicked(ActionEvent event) {
        animalShelterWebshop.addToCart(lvProducts.getSelectionModel().getSelectedItem());
    }

    @FXML
    void buttonCheckoutClicked(ActionEvent event) {
        animalShelterWebshop.checkoutCart();
    }

    @FXML
    void buttonRemoveFromCartClicked(ActionEvent event) {
        animalShelterWebshop.removeFromCart(lvCart.getSelectionModel().getSelectedItem());
    }

    @FXML
    void buttonReserveAnimalClicked(ActionEvent event) {
        animalShelterWebshop.reserveAnimal(lvAnimals.getSelectionModel().getSelectedItem(), tfUserName.getText());
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
        badHabit = tfBadHabit.getText();
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
        this.animalObservableList.setAll(animals);
    }

    @Override
    public void updateInventory(List<Product> products) {
        this.productObservableList.setAll(products);
    }

    @Override
    public void updateShoppingCart(List<Sellable> sellables) {

        this.sellableObservableList.setAll(sellables);
    }

    @Override
    public void updateTotalPrice(double totalPrice) {
        tfTotalPrice.setText(String.valueOf(totalPrice));
    }
}
