package Services;

import java.util.List;
import Entities.Poste;
import Utils.DbConnection;
import java.sql.*;
import java.util.ArrayList;

public class PosteService {

    Connection cnx;

    public PosteService() {
        cnx = DbConnection.getInstance().getConnection();
    }

    public void addPoste(Poste p) {
        try {
            String requete = "INSERT INTO Poste (titre, description, image) VALUES (?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getTitre());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getImage());
            pst.executeUpdate();
            System.out.println("Poste ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void updatePoste(Poste p, int id) {
        try {
            String requete = "UPDATE Poste SET titre=?, description=?, image=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getTitre());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getImage());
            pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Poste modifié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deletePoste(String titre) {
        try {
            String requete = "DELETE FROM Poste WHERE titre=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, titre);
            pst.executeUpdate();
            System.out.println("Poste supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Poste> getAll() {
        List<Poste> listPoste = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Poste";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Poste p = new Poste();
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                listPoste.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listPoste;
    }

    public Poste getById(int id) {
        Poste p = new Poste();
        try {
            String requete = "SELECT * FROM Poste WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
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

    public List<String> getAllTitles() {
        List<String> listTitres = new ArrayList<>();
        try {
            String requete = "SELECT titre FROM Poste";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String titre = rs.getString("titre");
                listTitres.add(titre);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listTitres;
    }

public Poste getPosteById(int id) {
    try {
        String requete = "SELECT * FROM Poste WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
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

}