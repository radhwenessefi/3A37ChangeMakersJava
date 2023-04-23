/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Produit;
import changemakers.services.ProduitCRUD;
import changemakers.utils.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class AjoutpProdController implements Initializable {

    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Button ao_btn_manage_users1;
    @FXML
    private Button ao_btn_manage_users11;
    @FXML
    private Button ao_btn_manage_users12;
    @FXML
    private Button ao_btn_manage_users13;
    @FXML
    private Button ao_btn_manage_users131;
    @FXML
    private TextField descrip;
    @FXML
    private TextField tnom;
    @FXML
    private TextField priix;
    @FXML
    private TextField qt;
    private ObservableList<Produit> ProduitList;

    private ProduitCRUD pc;

    @FXML
    private ComboBox<?> tfcategories;
    @FXML
    private AnchorPane main_form1;
    @FXML
    private ImageView imageViewProduit1;
    private File file2;
    @FXML
    private TextField tfimage;
    @FXML
    private Button Pajoutertab;
    private Label labelAffich;

    @FXML
    private TableView<Produit> PMtable;
    @FXML
    private TableColumn<Produit, String> nomProdtab;
    @FXML
    private TableColumn<Produit, Float> Prixtab;
    @FXML
    private TableColumn<Produit, Integer> Pquantitetab;
    @FXML
    private TableColumn<Produit, String> PMtypetab;
    @FXML
    private TableColumn<Produit, String> imagetab;
    @FXML
    private TableColumn<Produit, String> descriptionprix;

    @FXML
    private TextField PMcherchertab;
    @FXML
    private Button Pmodifier;
    @FXML
    private Button suppPM;
    @FXML
    private AnchorPane main_form;
    @FXML
    private ImageView imageViewProduit;
    @FXML
    private TableColumn<?, ?> Actiontab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ChoisirCateg();
        pc = new ProduitCRUD();

        try {
            ProduitList = FXCollections.observableArrayList(pc.selectAll());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nomProdtab.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        Prixtab.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        Pquantitetab.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
        PMtypetab.setCellValueFactory(cellData -> {
            StringProperty selectedCategoryProperty = new SimpleStringProperty(cellData.getValue().getCategorie_produit());
            String selectedCategory = (String) tfcategories.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                selectedCategoryProperty.set(selectedCategory);
            }
            return selectedCategoryProperty;
        });
       
        imagetab.setCellValueFactory(new PropertyValueFactory<>("image_produit"));
        descriptionprix.setCellValueFactory(new PropertyValueFactory<>("description"));

        PMtable.setItems(ProduitList);

    
        try {

            FilteredList<Produit> filteredData = new FilteredList<>(ProduitList, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            PMcherchertab.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(produit -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (produit.getNom_produit().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches  name.
                    } else if (produit.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    } else {
                        return String.valueOf(produit.getNom_produit()).contains(lowerCaseFilter); // Does not match.
                    }
                });
            });
            SortedList<Produit> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(PMtable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            PMtable.setItems(sortedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            // TODO
        }

    }

    // Method to delete the selected command
    /*private void deleteProduit(Produit produit) throws SQLException {
        // Get the ID of the selected command
        int id = produit.getId_produit();

        // Delete the command from the database using the CommandeCRUD class
        pc.supprimerProduit(id);

        // Remove the command from the table view
        ProduitList.remove(produit);

    }*/
    @FXML
    private void GoToDashboard(ActionEvent event) {
    }

    @FXML
    private void GoToManagUsers(ActionEvent event) {
    }

    @FXML
    private void GoToManagProd(ActionEvent event) {
    }

    @FXML
    private void GoToManagMusic(ActionEvent event) {
    }

    @FXML
    private void GoToManagReclamation(ActionEvent event) {
    }

    @FXML
    private void GoToManagBlog(ActionEvent event) {
    }

    @FXML
    private void AjoutP(ActionEvent event) throws FileNotFoundException, IOException {
        /*File ImportProduitImage;*/

        if (tnom.getText().isEmpty() || descrip.getText().isEmpty() || tfcategories.getSelectionModel().getSelectedItem() == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        } else {
            // Get the image file name from the text field
           /* String imagename = tfimage.getText();*/
   FileInputStream fl = new FileInputStream(file2);
        byte[] data = new byte[(int) file2.length()];
        String image_produit = file2.getName();
        fl.read(data);
        fl.close();

          
 //String imagename = file2.getName();
// Create a file object for the image file
         //   File imageFile = new File(imagename);



// Read the image data from the file
/* byte[] data;
            try ( FileInputStream fl = new FileInputStream(imageFile)) {
                data = new byte[(int) imageFile.length()];
                fl.read(data);*/
            
             
 String selectedCategory = (String) tfcategories.getSelectionModel().getSelectedItem();
 Produit p = new Produit(tnom.getText(), Float.parseFloat(priix.getText()), Integer.parseInt(qt.getText()), selectedCategory, image_produit, descrip.getText());
            /*String selectedCategory = (String) tfcategories.getSelectionModel().getSelectedItem();*/
            /*Produit p = new Produit(tnom.getText(), Float.parseFloat(priix.getText()), Integer.parseInt(qt.getText()), selectedCategory, descrip.getText());*/
            ProduitCRUD pc = new ProduitCRUD();
            try {
                pc.ajouterProduit(p);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

            }

        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProd.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    String listStatus[] = {"categ1", "categ2"};

    @FXML
    public void ChoisirCateg() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        tfcategories.setItems(listData);

    }

    /* private File ImportProduitImage(ActionEvent event) {
           Path to1 = null;
        
        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file2 = chooser.getSelectedFile();
            String fileName = file2.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file2);

                } catch (IOException ex) {
                    System.out.println();
                }
                 String iamge = file2.getName();
                        tfimage.setText(iamge);

            }

        }
        return file2;

   
                    }*/
    @FXML
    private File ImportProduitImage(MouseEvent event) {
        Path to1 = null;

        String m = null;
String path = "C:\\xampp\\htdocs\\img"; 
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file2 = chooser.getSelectedFile();
            String fileName = file2.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file2);

                } catch (IOException ex) {
                    System.out.println();
                }
                String image = file2.getName();
                tfimage.setText(image);

            }

        }
        return file2;

    }

    @FXML
    private void ModifP(ActionEvent event) throws SQLException, IOException {

        Produit p = new Produit(tnom.getText(), Float.parseFloat(priix.getText()), Integer.parseInt(qt.getText()), tfcategories.getSelectionModel().getSelectedItem().toString(), descrip.getText());
        ProduitCRUD pc = new ProduitCRUD();

        PMtable.getSelectionModel().getSelectedItem();
        pc.updateProduit(PMtable.getSelectionModel().getSelectedItem().getId_produit(), p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProd.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        /* Connection cnx2 = null;
        String sql = "UPDATE produit SET nom_produit = '"
                + tnom.getText() + "', prix_produit = '" + priix.getText()
                + "',quantite_produit  = '" + qt.getText() + "',catégorie_produit  = '"
                + tfcategories.getSelectionModel().getSelectedItem() + "', description = '" + descrip.getText()
                + "' WHERE nom_produit = '" + tnom.getText() + "'";

        PreparedStatement ste = cnx2.prepareStatement(sql);
        ste.executeUpdate();*/

 /*
        try {
            ProduitList = FXCollections.observableArrayList(pc.selectAll());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nomProdtab.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        Prixtab.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        Pquantitetab.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
        PMtypetab.setCellValueFactory(cellData -> {
            StringProperty selectedCategoryProperty = new SimpleStringProperty(cellData.getValue().getCategorie_produit());
            String selectedCategory = (String) tfcategories.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                selectedCategoryProperty.set(selectedCategory);
            }
            return selectedCategoryProperty;
        });

        imagetab.setCellValueFactory(new PropertyValueFactory<>("image_produit"));
        descriptionprix.setCellValueFactory(new PropertyValueFactory<>("description"));

        PMtable.setItems(ProduitList);*/

 /*  String sql = "UPDATE flowers SET name = '"
                + availableFlowers_flowerName.getText() + "', status = '"
                + availableFlowers_status.getSelectionModel().getSelectedItem() + "', price = '"
                + availableFlowers_price.getText() + "', image = '"
                + uri + "' WHERE flower_id = '" + availableFlowers_flowerID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (availableFlowers_flowerID.getText().isEmpty()
                    || availableFlowers_flowerName.getText().isEmpty()
                    || availableFlowers_status.getSelectionModel().getSelectedItem() == null
                    || availableFlowers_price.getText().isEmpty()
                    || uri == null || uri == "" || getData.path == null || getData.path == "") {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Flower ID: " + availableFlowers_flowerID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // SHOW UPDATED TABLEVIEW
                    availableFlowersShowListData();

                    // CLEAR ALL FIELDS
                    availableFlowersClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
 /*int idr = Integer.parseInt(id_text.getText()); 
        String desc = description_text.getText();
        String nom = nom_text.getText();
       float price =Float.parseFloat(prix_text.getText());
         int  cat=Integer.parseInt(categorie_text.getText());
          
 Produit pdt = new Produit(desc, nom, price, cat); 
 pdt.setId(idr);
 pdt.setDescription(desc);
 pdt.setName(nom);
 pdt.setPrice(price);
pdt.setId_categorie(cat); ; 
 ProduitService sp = new ProduitService(); 
 sp.modifierProduit(idr, desc, nom, price); */
    }

    @FXML
    private void SelectProduit(MouseEvent event) {

        Produit p = PMtable.getSelectionModel().getSelectedItem();

        int num = PMtable.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        tnom.setText(p.getNom_produit());
        priix.setText(String.valueOf(p.getPrix_produit()));
        qt.setText(String.valueOf(p.getQuantite_produit()));
        descrip.setText(p.getDescription());

    }

    @FXML
    private void SupprimerProd(ActionEvent event) throws IOException {
        Produit p = new Produit(tnom.getText(), Float.parseFloat(priix.getText()), Integer.parseInt(qt.getText()), tfcategories.getSelectionModel().getSelectedItem().toString(), descrip.getText());
        ProduitCRUD pc = new ProduitCRUD();

        PMtable.getSelectionModel().getSelectedItem();
        pc.supprimerProduit(PMtable.getSelectionModel().getSelectedItem().getId_produit());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProd.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
