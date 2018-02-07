package com.ril.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ril.entity.Donnee;

@Repository
public class DonneeDao {
	@Autowired
	private Database database;

	public long Save(Donnee d) throws Exception {
		long result = 0;
		try {
			Connection conn = database.getSqlConnection();

			String sql = "INSERT INTO donnees (dtenregistrement, valeur, idcontact, idchamp) VALUES (?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE valeur=VALUES(valeur)";

			System.out.println(sql);

			PreparedStatement ps = conn.prepareStatement(sql, com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

			ps.setDate(1, Date.valueOf(d.getDtenregistrement()));
			ps.setString(2, d.getValeur());
			ps.setLong(3, d.getIdcontact());
			ps.setLong(4, d.getIdchamp());

			result = ps.executeUpdate();
			ResultSet rspk = ps.getGeneratedKeys();
			rspk.next();
			result = rspk.getLong(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result == 0)
			throw new Exception(String.format("Erreur dans DonneeDao.Save: donnee=%s", d.toString()));

		return result;
	}

	public List<Donnee> findByIdContact(long idcontact) {

		List<Donnee> liste = new ArrayList<Donnee>();

		try {
			Connection conn = (Connection) database.getSqlConnection();

			String sql = "select iddonnee,idchamp,idcontact,valeur,dtenregistrement from donnees where idcontact=?";

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setLong(1, idcontact);

			System.out.println(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Donnee d = new Donnee(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4),
						rs.getDate(5).toLocalDate());
				liste.add(d);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}

	public List<Donnee> findByIdContactAndIdtemplate(long idcontact, long idtemplate) {

		List<Donnee> liste = new ArrayList<Donnee>();
		try {
			Connection conn = (Connection) database.getSqlConnection();

			String sql = "select a.iddonnee,a.idchamp,a.idcontact,a.valeur,a.dtenregistrement from donnees a "
					+ "JOIN lientemplatechamp b using (idchamp) " + "join template c on c.idtemplate=b.idtemplate "
					+ "JOIN contact d on a.idcontact=d.idcontact " + "where d.idcontact=? and c.idtemplate=? "
					+ "order by b.ordre ";

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setLong(1, idcontact);
			ps.setLong(1, idtemplate);

			System.out.println(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Donnee d = new Donnee(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4),
						rs.getDate(5).toLocalDate());
				liste.add(d);
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
