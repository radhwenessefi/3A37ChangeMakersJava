package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Commentaires;
import Entities.Poste;
import Utils.DbConnection;

public class CommentaireService {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //String continueCommentaireField2;
    private Object continueCommentaireField2;
    
    public CommentaireService() {
        con = DbConnection.getInstance().getConnection();
    }

    
    
    
    
    
public List<Commentaires> getAllCommentaires() {
    List<Commentaires> commentaires = new ArrayList<>();


    try {
        String requete = "SELECT * FROM commentaire WHERE continue_commentaire = continueCommentaireField2";
        ps = con.prepareStatement(requete);
        rs = ps.executeQuery();

        while (rs.next()) {
            Commentaires commentaire = new Commentaires();
            commentaire.setId(rs.getInt("id"));
            //commentaire.setContinueCommentaire(rs.getString("continue_commentaire").replaceAll("\\" + continueCommentaireField2.getText(), "****"));
            commentaire.setContinueCommentaire(rs.getString("continue_commentaire").replaceAll("\\" + continueCommentaireField2, "****"));
            commentaires.add(commentaire);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return commentaires;
}


    public void updateCommentaire(Commentaires commentaire) {
        try {
            String requete = "UPDATE commentaire SET continue_commentaire = ? WHERE id = ?";
            ps = con.prepareStatement(requete);
            ps.setString(1, commentaire.getContinueCommentaire());
            ps.setInt(2, commentaire.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCommentaire(int id) {
        try {
            String requete = "DELETE FROM commentaire WHERE id = ?";
            ps = con.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertOne1(Commentaires t) throws SQLException{
String req = "INSERT INTO commentaire ( `id_post`,`continueCommentaire`) VALUES (?,?)";
        
PreparedStatement ps = con.prepareStatement(req); 
ps.setInt(1, t.getPoste_id());
ps.setString(2, t.getContinueCommentaire());


ps.executeUpdate();
System.out.println("Commentaire ajouté !");

    }

    public Commentaires getCommentaireById(int id) {
        Commentaires commentaire = null;

        try {
            String requete = "SELECT * FROM commentaire WHERE id = ?";
            ps = con.prepareStatement(requete);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                commentaire = new Commentaires();
                commentaire.setId(rs.getInt("id"));
                commentaire.setContinueCommentaire(rs.getString("continue_commentaire"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentaire;
    }
public List<Commentaires> getByPoste(int postId) {
    List<Commentaires> commentaires = new ArrayList<>();

    try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Poste WHERE poste_id = ?");
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Commentaires commentaire = new Commentaires();
            commentaire.setId(rs.getInt("id"));
            commentaire.setContinueCommentaire(rs.getString("continue_commentaire"));
            commentaire.setPoste_id(rs.getInt("poste_id"));
            commentaires.add(commentaire);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return commentaires;
}





    public Commentaires[] getCommentairesByPoste(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
           
            
}