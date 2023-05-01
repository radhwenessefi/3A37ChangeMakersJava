/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Favoris;
import com.changemakers.atpeace.entites.Rate;
import com.changemakers.atpeace.services.ServiceFavoris;
import com.changemakers.atpeace.services.ServiceRate;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ChartsController implements Initializable {

    @FXML
    private PieChart pieChartRate;
    @FXML
    private PieChart piechartFavoris;
    @FXML
    private Label ntrate;
    @FXML
    private Label nbttfavoris;
    @FXML
    private Label nbfavorisone;
    @FXML
    private Label nbrateone;
    int nbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Create an empty ObservableList of PieChart.Data objects
        ObservableList<PieChart.Data> pieChartDataRate = FXCollections.observableArrayList();

// Get the list of Rate objects from your database
        ServiceRate sr = new ServiceRate();
        List<Rate> rate_test = null;
        try {
            rate_test = sr.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        double sum1 = 0;
        double sum2 = 0;
        for (Rate rate_t : rate_test) {
            double totale_rate = rate_t.getNum_totale();
            double totale_rate_one = rate_t.getNub_of_rate();
            sum2 += totale_rate_one;
            sum1 += totale_rate;
            ntrate.setText(Double.toString(sum1));
            nbrateone.setText(Double.toString(sum2));
        }
// Iterate over the list of Rate objects and add a new PieChart.Data object for each one
        for (Rate rate_t : rate_test) {
            // Create a new PieChart.Data object with the name and value properties set to the name_regime and num_totale properties of the Rate object, respectively
            PieChart.Data data = new PieChart.Data(rate_t.getName_regime() + " (" + rate_t.getNum_totale() + ")", rate_t.getNum_totale());
            pieChartDataRate.add(data);
        }

// Set the data for your PieChart
        pieChartRate.setData(pieChartDataRate);

// Create an empty ObservableList of PieChart.Data objects
        ObservableList<PieChart.Data> pieChartDataFavoris = FXCollections.observableArrayList();

// Get the list of Favoris objects from your database
        ServiceFavoris sf = new ServiceFavoris();
        List<Favoris> favoris_test = null;
        try {
            favoris_test = sf.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Favoris favoris_t : favoris_test) {
            double totale_rate = favoris_t.getNb_total();
            double totale_rate_one = favoris_t.getNb_favori();
            sum2 += totale_rate_one;
            sum1 += totale_rate;
            nbttfavoris.setText(Double.toString(sum1));
            nbfavorisone.setText(Double.toString(sum2));
        }
// Iterate over the list of Favoris objects and add a new PieChart.Data object for each one
        for (Favoris favoris_t : favoris_test) {
            // Create a new PieChart.Data object with the name and value properties set to the name_regime and num_totale properties of the Favoris object, respectively
            PieChart.Data data = new PieChart.Data(favoris_t.getRegime_name() + " (" + favoris_t.getNb_total() + ")", favoris_t.getNb_total());
            pieChartDataFavoris.add(data);
        }

// Set the data for your PieChart
        piechartFavoris.setData(pieChartDataFavoris);

    }

}
