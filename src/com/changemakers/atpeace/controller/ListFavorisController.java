/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.entities.Favoris;
import com.changemakers.atpeace.entities.Session;
import com.changemakers.atpeace.services.ServiceFavoris;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class ListFavorisController implements Initializable {
    int id;
    Session s = new Session();
    @FXML
    private TextField tfTaille;
    @FXML
    private TextField tfPoids;
    @FXML
    private Button btncalculer;
    @FXML
    private TextField tfResult;
    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private GridPane productGrid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        List<Favoris> listIdRegime ;
         private List<Regime> listeRegime;
             private Pagination pagination;
    private List<Regime> filteredRefimes; // the data to display

     ObservableList<Regime> favoris = FXCollections.observableArrayList();
    private TextField search;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
            controleRegime cr= new controleRegime();
        SessionService ss = new SessionService();
        s = ss.ConnectUser();
        id = s.getId_user();
        System.out.println("favoris id client"+id);
      
        //get all regime id for one patient 
        ServiceFavoris sf = new ServiceFavoris();
        try {
           listIdRegime= sf.ListFav(id);
           
              System.out.println("tessstid"+listIdRegime);
        } catch (SQLException ex) {
            Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //now i will get all the regime details of one patient 
  for (Favoris favoris_t : listIdRegime) {
      System.out.println(favoris_t.getRegime_id());
    int idF = favoris_t.getRegime_id().getId();
      System.out.println(idF);
    try {
        List<Regime> regimes = cr.Affichebyidregime(idF);
        for (Regime regime : favoris) {
            favoris.add(regime);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

        // Define a FilteredList to hold the filtered data
        FilteredList<Regime> filteredData = new FilteredList<>(favoris, b -> true);
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
                favoris.add(listemps.get(i));
            }
        }

// Set up the search text field listener
      /*  search.textProperty().addListener((observable, oldValue, newValue) -> {
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

        });*/

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
    private void CalculeBMI(ActionEvent event) {
    }

  
}
