/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Regime;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private Button btncalculer;
    @FXML
    private TextField tfResult;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Define a FilteredList to hold the filtered data
FilteredList<Regime> filteredData = new FilteredList<>(regimes, b -> true);
     controleRegime controle = new controleRegime();

        List<Regime> listemps = null;
        try {
            listemps = controle.AfficheToutRegime();
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

        productGrid.getChildren().clear();
        Label noResultsLabel = new Label();
        VBox vbox= new VBox();
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

        }else {
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

        regimeController.setData(regimes);

        hbox.setStyle("-fx-background-color: #F7F8FD;");

        if (col == 3) {
            col = 0;
            row++;
        }
        productGrid.add(hbox, col++, row);
        productGrid.setMargin(hbox, new Insets(10));
    }
}






}
