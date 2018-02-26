package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ril.entity.DataType;
import com.ril.entity.Preselection;

@Repository
public class PreselectionDao {
	@Autowired
	private Database database;

	public List<Preselection> getPreselectionsByIdChamp(long idchamp) {
		 List<Preselection> liste = new ArrayList<Preselection>();

		try {
			Connection conn = database.getSqlConnection();

			String sql = "SELECT idpreselect,valeur,idchamp FROM preselection WHERE idchamp=? order by valeur";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, idchamp);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Preselection p = new Preselection(rs.getLong(1), rs.getString(2), rs.getLong(3));
				liste.add(p);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}
}
