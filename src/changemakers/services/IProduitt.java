/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package changemakers.services;
import changemakers.entities.Produit;
import java.sql.SQLException;
import java.util.List;
/*
 * @author zaiir
 */
public interface IProduitt {
         public void ajouterProduit(Produit p)throws SQLException ;
         public List<Produit> selectAll() throws SQLException ;
    
}
