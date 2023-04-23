/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Produit;
import changemakers.services.ProduitCRUD;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class BaseFrontProduitController implements Initializable {

    private List<Produit> listeproduit;
    @FXML
    private TextField search;
    @FXML
    private GridPane productGrid;
    @FXML
    private ScrollPane scrollPane1;
    ObservableList<Produit> Listproduit = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Produit p = new Produit();
        ProduitCRUD pc = new ProduitCRUD();
        FilteredList<Produit> filteredData = new FilteredList<>(Listproduit, b -> true);
        //controleRegime controle = new controleRegime();

        List<Produit> listemps = null;
        try {
            listemps = pc.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutpProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listemps.isEmpty()) {
            for (int i = 0; i < listemps.size(); i++) {
                Listproduit.add(listemps.get(i));
            }
        }

// Set up the search text field listener
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(regime -> {
                // If search text is empty, show all data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (p.getNom_produit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (p.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else {
                    return String.valueOf(p.getNom_produit()).contains(lowerCaseFilter); // Does not match.
                }
            });
            // Get filtered data and update the grid
            System.out.println("filteredData: " + filteredData);
            List<Produit> filteredProduit = filteredData.stream().collect(Collectors.toList());
            try {
                System.out.println("filteredRegimes: " + filteredProduit);
                updateGrid(filteredProduit);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        });

        try {
            // TODO
            listRegimetfeed();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutpProdController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(BaseFrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(AjoutpProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateGrid(List<Produit> filteredProduit) throws SQLException, IOException {

        productGrid.getChildren().clear();
        Label noResultsLabel = new Label();
        VBox vbox = new VBox();
        if (filteredProduit.isEmpty()) {
            productGrid.setVisible(false);
            scrollPane1.setVisible(false);
            noResultsLabel.setVisible(true);
            noResultsLabel.setAlignment(Pos.CENTER);
            noResultsLabel.setStyle("-fx-font-size: 14; -fx-text-fill: red;-fx-background-color: red");
            vbox.setStyle("-fx-background-color: red");
            noResultsLabel.setText("Aucun résultat");
            vbox.getChildren().add(noResultsLabel);
            productGrid.add(vbox, 1, 1);

        } else {
            int col = 0;
            int row = 1;
            scrollPane1.setVisible(true);
            productGrid.setVisible(true);
            noResultsLabel.setVisible(false);

            for (Produit produits : filteredProduit) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitFront.fxml"));
                VBox vBox = fxmlLoader.load();
                ProduitFrontController produitController = fxmlLoader.getController();
                produitController.setData(produits);
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
        //listeproduit controle = new controleRegime();
        ProduitCRUD pc = new ProduitCRUD();

        int row = 1;
        int col = 0;

        listeproduit = pc.selectAll();
        for (Produit produits : listeproduit) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProduitFront.fxml"));
            VBox hbox = fxmlLoader.load();
            ProduitFrontController produitController = fxmlLoader.getController();

            produitController.setData(produits);

            hbox.setStyle("-fx-background-color: #F7F8FD;");

            if (col == 3) {
                col = 0;
                row++;
            }
            productGrid.add(hbox, col++, row);
            productGrid.setMargin(hbox, new Insets(10));
        }
    }

    @FXML
    private void GoToPanier(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
        Parent root = loader.load();

        // Create a new scene and set it in the stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Panier");

        // Show the new stage
        stage.show();
    }

}
