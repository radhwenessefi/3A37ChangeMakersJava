/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Regime;
import com.changemakers.atpeace.entities.Session;
import com.changemakers.atpeace.services.SessionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class BaseFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Regime> listeRegime;
    @FXML
    private TextField search;
    @FXML
    private GridPane productGrid;
    @FXML
    private ScrollPane scrollPane1;
    ObservableList<Regime> regimes = FXCollections.observableArrayList();
    @FXML
    private TextField tfTaille;
    @FXML
    private TextField tfPoids;
    @FXML
    private TextField tfResult;
    int taille;
    int poids;
    int BMI;
    private Pagination pagination;
    private List<Regime> filteredRefimes; // the data to display
    @FXML
    private Pane changepane;
    @FXML
    private Label lbusername;
    int id;
    Session s = new Session();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLbusename(String username) {
        this.lbusername.setText(username);
    }

    @FXML
    private Button btncalculer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SessionService ss = new SessionService();
        s = ss.ConnectUser();
        id = s.getId_user();
        System.out.println(id);

        // Define a FilteredList to hold the filtered data
        FilteredList<Regime> filteredData = new FilteredList<>(regimes, b -> true);
        controleRegime controle = new controleRegime();

        List<Regime> listemps = null;
        try {
            listemps = controle.AfficheToutRegime();
            System.out.println(listemps);
        } catch (SQLException ex) {
            Logger.getLogger(MenuInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listemps.isEmpty()) {
            for (int i = 0; i < listemps.size(); i++) {
                regimes.add(listemps.get(i));
            }
        }

// Set up the search text field listener
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(regime -> {
                // If search text is empty, show all data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare regime title, description, and level with search text
                String lowerCaseFilter = newValue.toLowerCase();
                if (regime.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (regime.getDiscription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (regime.getLevel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });

            // Get filtered data and update the grid
            System.out.println("filteredData: " + filteredData);
            List<Regime> filteredRegimes = filteredData.stream().collect(Collectors.toList());
            try {
                System.out.println("filteredRegimes: " + filteredRegimes);
                updateGrid(filteredRegimes);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        });

        try {
            // TODO
            listRegimetfeed();
        } catch (SQLException ex) {
            Logger.getLogger(BaseFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateGrid(List<Regime> filteredRefimes) throws SQLException, IOException {
        this.filteredRefimes = filteredRefimes;
        int itemsPerPage = 9; // Number of items to display on each page
        productGrid.getChildren().clear();
        Label noResultsLabel = new Label();
        pagination = new Pagination((filteredRefimes.size() / itemsPerPage + 1), 0);

        VBox vbox = new VBox();
        if (filteredRefimes.isEmpty()) {
            productGrid.setVisible(false);
            scrollPane1.setVisible(false);
            noResultsLabel.setVisible(true);
            noResultsLabel.setAlignment(Pos.CENTER);
            noResultsLabel.setStyle("-fx-font-size: 14; -fx-text-fill: red;-fx-background-color: red");
            vbox.setStyle("-fx-background-color: red");
            noResultsLabel.setText("Aucun résultat");
            vbox.getChildren().add(noResultsLabel);
            productGrid.add(vbox, 1, 1);
            productGrid.add(pagination, 0, 0);

        } else {
            int col = 0;
            int row = 1;
            scrollPane1.setVisible(true);
            productGrid.setVisible(true);
            noResultsLabel.setVisible(false);

            for (Regime regimes : filteredRefimes) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/changemakers/atpeace/gui/RegimeFront.fxml"));
                VBox vBox = fxmlLoader.load();
                RegimeFrontController regimeController = fxmlLoader.getController();
                regimeController.setData(regimes);
                regimeController.setId(id);
                if (col == 3) {
                    col = 0;
                    row++;
                }

                productGrid.add(vBox, col++, row);
                productGrid.setMargin(vBox, new Insets(10));
            }

        }

    }

    private void listRegimetfeed() throws SQLException, IOException {

        controleRegime controle = new controleRegime();
        int row = 1;
        int col = 0;

        listeRegime = controle.AfficheToutRegime();
        for (Regime regimes : listeRegime) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/changemakers/atpeace/gui/RegimeFront.fxml"));
            VBox hbox = fxmlLoader.load();
            RegimeFrontController regimeController = fxmlLoader.getController();
            System.out.println("giiiiiiiiiiiiilllllllllllesbouasba");
            regimeController.setData(regimes);
            regimeController.setId(id);
            System.out.println("giiiiiiiiiiiiillllllllllles");
            hbox.setStyle("-fx-background-color: #F7F8FD;");

            if (col == 3) {
                col = 0;
                row++;
            }
            productGrid.add(hbox, col++, row);
            productGrid.setMargin(hbox, new Insets(10));
            System.out.println("qdlnsdl" + id);

        }
    }

    @FXML
    private void CalculeBMI(ActionEvent event) throws SQLException, IOException {
        controleRegime controle = new controleRegime();
        String tailleStr = tfTaille.getText();
        double taille = Double.parseDouble(tailleStr);
        String poidsStr = tfPoids.getText();
        double poids = Double.parseDouble(poidsStr);
        double bmi = poids / (taille * taille);

        List<Regime> listergs = null;
        if (bmi < 18.5) {

            listergs = controle.AffichebyLevel("underweghit");
            System.out.print(listergs);
            updateGrid(listergs);
            String bmiStr = String.format("%.2f", bmi);
            tfResult.setText(bmiStr);

        } else if (bmi >= 18.5 && bmi < 25) {

            listergs = controle.AffichebyLevel("ideal");
            System.out.print(listergs);
            updateGrid(listergs);
            String bmiStr = String.format("%.2f", bmi);
            tfResult.setText(bmiStr);

            System.out.print(regimes);
        } else if (bmi >= 25 && bmi < 30) {

            listergs = controle.AffichebyLevel("overweghit");
            System.out.print(listergs);
            updateGrid(listergs);
            String bmiStr = String.format("%.2f", bmi);
            tfResult.setText(bmiStr);

        } else {
            String bmiStr = String.format("%.2f", bmi);
            tfResult.setText(bmiStr);
            listergs = null;
            updateGrid(listergs);
            System.out.print(regimes);
        }
    }

    @FXML
    private void goToSport(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/sportFront.fxml"));
        Pane content = loader.load();

        Pane pane = new Pane(content); // create a new Pane and add the content to it

// Set the constraints of the content node within the Pane
        content.setLayoutX(0);
        content.setLayoutY(0);
        content.setPrefWidth(Double.MAX_VALUE);
        content.setPrefHeight(Double.MAX_VALUE);

// Replace the existing content in your main Pane with the new content
        changepane.getChildren().setAll(pane);

    }

    @FXML
    private void goToRegimeFront(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/baseFront.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToFavoris(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/listFavoris.fxml"));

        Pane content = loader.load();

        Pane pane = new Pane(content); // create a new Pane and add the content to it

// Set the constraints of the content node within the Pane
        content.setLayoutX(0);
        content.setLayoutY(0);
        content.setPrefWidth(Double.MAX_VALUE);
        content.setPrefHeight(Double.MAX_VALUE);

// Replace the existing content in your main Pane with the new content
        changepane.getChildren().setAll(pane);
    }

    @FXML
    private void goToRdv(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/Rdv.fxml"));

            Pane content = loader.load();

            Pane pane = new Pane(content); // create a new Pane and add the content to it

// Set the constraints of the content node within the Pane
            content.setLayoutX(0);
            content.setLayoutY(0);
            content.setPrefWidth(Double.MAX_VALUE);
            content.setPrefHeight(Double.MAX_VALUE);

// Replace the existing content in your main Pane with the new content
            changepane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(BaseFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
