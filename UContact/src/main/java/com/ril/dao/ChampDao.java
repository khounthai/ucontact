package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ril.entity.Champ;
import com.ril.entity.Donnee;

@Repository
public class ChampDao {
	@Autowired
	private Database database;

	public List<Champ> getChamps(long idtemplate) {
		
			List<Champ> liste=new ArrayList<Champ>();
		
			try {
				Connection conn=database.getSqlConnection();
			     
	            String sql = "SELECT a.idchamp,a.libelle,a.multivaleur,a.actif,a.iddatatype FROM champ a " + 
	            			"join lientemplatechamp b using(idchamp) " + 
	            			"where b.idtemplate=%d and a.actif=1 " + 
	            			"order by b.ordre";
	            
	            sql=String.format(sql, idtemplate);

				System.out.println(sql);
				
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            
	            while (rs.next()) {                                
	            	Champ c=new Champ(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getLong(5),new Donnee());	                                
	                liste.add(c);
	            }
	            
	            rs.close();
	            ps.close();
	            
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return liste;		
		}
	
	public Champ getChamp(long idchamp) {
		
		Champ c=null;
	
		try {
			Connection conn=database.getSqlConnection();
		     
            String sql = "SELECT * FROM champ WHERE idchamp=%d and a.actif=1";
            sql=String.format(sql, idchamp);
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                
            	 c=new Champ(rs.getLong(0),rs.getString(1),rs.getInt(2),rs.getBoolean(3),rs.getLong(4),null);
            }
            rs.close();
            ps.close();
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
		
}
