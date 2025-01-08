package oussama.it.modular_calculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class CalculatorController {

    @FXML
    private TextField display;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private VBox pluginContainer;

    private double firstOperand = 0;
    private String currentOperation = null;

    private final List<Command> loadedPlugins = new ArrayList<>();

    @FXML
    public void initialize() {
        // Initialisation du champ d'affichage
        display.setText("0");
    }

    @FXML
    private void handleNumber(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String number = button.getText();
        if ("0".equals(display.getText())) {
            display.setText(number);
        } else {
            display.setText(display.getText() + number);
        }
    }

    @FXML
    private void handleEquals() {
        if (currentOperation != null && !display.getText().isEmpty()) {
            try {
                double secondOperand = Double.parseDouble(display.getText());
                for (Command plugin : loadedPlugins) {
                    if (plugin.getOperationName().equals(currentOperation)) {
                        double result = plugin.execute(firstOperand, secondOperand);
                        display.setText(String.valueOf(result));
                        firstOperand = result;
                        currentOperation = null;
                        return;
                    }
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'exécution de l'opération", e.getMessage());
            }
        }
    }

    @FXML
    private void handleClear() {
        // Réinitialisation de l'affichage et des variables
        display.setText("0");
        firstOperand = 0;
        currentOperation = null;
    }

    @FXML
    private void handleAddPlugin() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ajouter un plugin");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Java Class or JAR", "*.class", "*.jar")
            );
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                URL[] urls = {selectedFile.toURI().toURL()};
                URLClassLoader loader = new URLClassLoader(urls);

                String className = selectedFile.getName().replace(".class", "").replace(".jar", "");
                Class<?> clazz = loader.loadClass("oussama.it.modular_calculator.Plugins." + className);

                Object instance = clazz.getDeclaredConstructor().newInstance();
                if (instance instanceof Command) {
                    Command plugin = (Command) instance;
                    loadedPlugins.add(plugin);
                    addPluginButton(plugin);
                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger le plugin", "Vérifiez que le fichier sélectionné est valide.");
        }
    }

    private void addPluginButton(Command plugin) {
        Button pluginButton = new Button(plugin.getOperationName());
        pluginButton.setOnAction(e -> {
            currentOperation = plugin.getOperationName();
            firstOperand = Double.parseDouble(display.getText());
            display.setText("0");
        });

        // Ajout d'un menu contextuel pour supprimer le plugin
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(e -> handleDeletePlugin(pluginButton, plugin));
        contextMenu.getItems().add(deleteItem);

        pluginButton.setOnContextMenuRequested(event ->
                contextMenu.show(pluginButton, event.getScreenX(), event.getScreenY())
        );
        pluginContainer.getChildren().add(pluginButton);
    }

    @FXML
    private void handleDeletePlugin() {
        // Méthode vide pour correspondre au fichier FXML
    }

    private void handleDeletePlugin(Button pluginButton, Command plugin) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer ce plugin ?");
        confirmationAlert.setContentText("Opération : " + plugin.getOperationName());

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                loadedPlugins.remove(plugin);
                pluginContainer.getChildren().remove(pluginButton);
            }
        });
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}




//package oussama.it.modular_calculator;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//
//import java.io.File;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CalculatorController {
//
//    @FXML
//    private TextField display;
//    @FXML
//    private GridPane buttonGrid;
//    @FXML
//    private VBox pluginContainer;
//
//    private double firstOperand = 0;
//    private String currentOperation = null;
//
//    private List<Command> loadedPlugins = new ArrayList<>();
//
//    @FXML
//    public void initialize() {
//        display.setText("0");
//    }
//
//    @FXML
//    private void handleNumber(javafx.event.ActionEvent event) {
//        Button button = (Button) event.getSource();
//        String number = button.getText();
//        if (display.getText().equals("0")) {
//            display.setText(number);
//        } else {
//            display.setText(display.getText() + number);
//        }
//    }
//
//    @FXML
//    private void handleEquals() {
//        try {
//            if (currentOperation != null && !display.getText().isEmpty()) {
//                double secondOperand = Double.parseDouble(display.getText());
//                for (Command plugin : loadedPlugins) {
//                    if (plugin.getOperationName().equals(currentOperation)) {
//                        double result = plugin.execute(firstOperand, secondOperand);
//                        display.setText(String.valueOf(result));
//                        firstOperand = result;
//                        currentOperation = null;
//                        return;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Erreur lors de l'exécution de l'opération");
//            alert.setContentText(e.getMessage());
//            alert.showAndWait();
//        }
//    }
//
//    @FXML
//    private void handleClear() {
//        display.setText("0");
//        firstOperand = 0;
//        currentOperation = null;
//    }
//
//    @FXML
//    private void handleAddPlugin() {
//        try {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Ajouter un plugin");
//            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Class or JAR", "*.class", "*.jar"));
//            File selectedFile = fileChooser.showOpenDialog(null);
//
//            if (selectedFile != null) {
//                URL[] urls = {selectedFile.toURI().toURL()};
//                URLClassLoader loader = new URLClassLoader(urls);
//
//                String className = selectedFile.getName().replace(".class", "").replace(".jar", "");
//                Class<?> clazz = loader.loadClass("oussama.it.modular_calculator.Plugins." + className);
//
//                Object instance = clazz.getDeclaredConstructor().newInstance();
//                if (instance instanceof Command) {
//                    Command plugin = (Command) instance;
//                    loadedPlugins.add(plugin);
//                    addPluginButton(plugin);
//                }
//            }
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Impossible de charger le plugin");
//            alert.setContentText("Vérifiez que le fichier sélectionné est un plugin valide.");
//            alert.showAndWait();
//        }
//    }
//
//    private void addPluginButton(Command plugin) {
//        Button pluginButton = new Button(plugin.getOperationName());
//        pluginButton.setOnAction(e -> {
//            currentOperation = plugin.getOperationName();
//            firstOperand = Double.parseDouble(display.getText());
//            display.setText("0");
//        });
//
//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem deleteItem = new MenuItem("Supprimer");
//        deleteItem.setOnAction(e -> handleDeletePlugin(pluginButton, plugin));
//        contextMenu.getItems().add(deleteItem);
//
//        pluginButton.setOnContextMenuRequested(event -> contextMenu.show(pluginButton, event.getScreenX(), event.getScreenY()));
//        pluginContainer.getChildren().add(pluginButton);
//    }
//
//    @FXML
//    private void handleDeletePlugin() {
//        // Méthode vide pour correspondre au fichier FXML
//    }
//
//    private void handleDeletePlugin(Button pluginButton, Command plugin) {
//        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmationAlert.setTitle("Confirmation");
//        confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer ce plugin ?");
//        confirmationAlert.setContentText("Opération : " + plugin.getOperationName());
//
//        confirmationAlert.showAndWait().ifPresent(response -> {
//            if (response == ButtonType.OK) {
//                loadedPlugins.remove(plugin);
//                pluginContainer.getChildren().remove(pluginButton);
//            }
//        });
//    }
//}
