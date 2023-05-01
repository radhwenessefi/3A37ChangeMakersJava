    package Services;

import Entities.Commentaire;
import Entities.Poste;
import java.sql.SQLException;
import java.util.List;
import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
    
public class PosteService {
    
    private static Poste selectedPoste;

    public static void setSelectedPoste(Poste poste) {
        selectedPoste = poste;
    }
    
    public static Poste getSelectedPoste() {
        return selectedPoste;
    }



        Connection con;
        Statement stm;
        PreparedStatement ste;
        public PosteService() {
            con = DbConnection.getInstance().getConnection();
        }
        
        

        public void addPoste(Poste p) throws SQLException {
        String requete = "INSERT INTO `Poste` (`titre`, `description`,`image`)VALUES ( '"
         + p.getDescription()+ "', '" + p.getTitre()+ "', '" + p.getImage()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(requete);
            }
               
        
        
           public List<Poste> getAll() throws SQLException {
        String  requete = "SELECT * FROM poste";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(requete);
        System.out.println(rst.toString());
        List<Poste> Poste = new ArrayList<Poste>();
        while(rst.next()){
            
            Poste c;
            c = new Poste(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4));
            Poste.add(c);
            
    }
        return Poste;
        
    }   

           
             public void updatePoste(Poste p, int id) throws SQLException {
        String req = "UPDATE Games SET  titre = ?, description = ?, imgage = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
          pre.setString(1, p.getTitre());
          pre.setString(2, p.getDescription());
          pre.setString(4, p.getImage());

        pre.executeUpdate();  
    
    }   
      
             
         public void deletePoste(int id )throws SQLException  {

                String req = "DELETE FROM Poste WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

public List<Poste> getByTitre(String titre) {
    List<Poste> postes = new ArrayList<>();
    try {
        String query = "SELECT * FROM `Poste` WHERE `titre` LIKE '%" + titre + "%'";
        Connection con = DbConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Poste poste = new Poste(
                rs.getInt("id"),
                rs.getString("titre"),
                rs.getString("description"),
                rs.getString("image")
            );
            postes.add(poste);
        }
        st.close();
        rs.close();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return postes;
}
}


/*
        public Poste[] searchPoste(String search) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }



        
        
        
                public Poste getById(int id) {
            Poste p = new Poste();
            try {
                String requete = "SELECT * FROM Poste WHERE id=?";
                PreparedStatement pst = con.prepareStatement(requete);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    p.setTitre(rs.getString("titre"));
                    p.setDescription(rs.getString("description"));
                    p.setImage(rs.getString("image"));
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return p;
        }



    public Poste getPosteById(int id) {
        try {
            String requete = "SELECT * FROM Poste WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Poste p = new Poste();
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                return p;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }


*/
               
             
        

    
    

    

