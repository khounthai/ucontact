package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ril.entity.Champ;
import com.ril.entity.DataType;
import com.ril.entity.Donnee;
import com.ril.entity.Preselection;

@Repository
public class ChampDao {
	@Autowired
	private Database database;
	
	@Autowired DataTypeDao datatypedao;
	
	@Autowired PreselectionDao preselectiondao;

	public List<Champ> getChamps(long idtemplate,boolean champactif) {

		List<Champ> liste = new ArrayList<Champ>();

		try {
			Connection conn = database.getSqlConnection();

			String sql = "SELECT a.idchamp,a.libelle,a.multivaleur,a.iddatatype,b.accueil FROM champ a "
					+ "join lientemplatechamp b using(idchamp) " + "where b.idtemplate=? and b.champactif=? "
					+ "order by b.ordre";

			sql = String.format(sql, idtemplate);

			//System.out.println(sql);

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, idtemplate);
			ps.setBoolean(2, champactif);
			ResultSet rs = ps.executeQuery();
			

			while (rs.next()) {
				Champ c = new Champ(rs.getLong(1), rs.getString(2), rs.getBoolean(3), rs.getLong(4),new Donnee(),new DataType(),
						new ArrayList<String>(),rs.getBoolean(5));
				
				DataType d=datatypedao.getDataType(c.getIddatatype());
				c.setDatatype(d);				
				List<Preselection> p=preselectiondao.getPreselectionsByIdChamp(c.getIdchamp());
					
				p.forEach(x->{
					c.getPreselection().add(x.getValeur());
				});
				
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
		Champ c = null;

		try {
			Connection conn = database.getSqlConnection();

			String sql = "SELECT idchamp,libelle,multivaleur,iddatatype FROM champ WHERE idchamp=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, idchamp);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Champ(rs.getLong(1), rs.getString(2), rs.getBoolean(3), rs.getLong(4),new Donnee(),new DataType(),new ArrayList<String>(),false);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	public int ActiverDesactiver(long idtemplate, long idchamp, boolean actif) throws Exception {
		int result = 0;

		try {
			Connection conn = database.getSqlConnection();

			String sql = "update lientemplatechamp set champactif=? where idtemplate=? and idchamp=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, actif);
			ps.setLong(2, idtemplate);
			ps.setLong(3, idchamp);

			result = ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result == 0)
			throw new Exception(
					String.format("Erreur dans ChampDao.ActiverDesactive: idtemplate=%d, idchamp=%d, actid=%s",
							idtemplate, idchamp, actif));

		return result;
	}
}
