package com.ril.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.ril.entity.Contact;
import com.ril.entity.Donnee;

@Repository
public class ContactDao {

	@Autowired
	private Database database;
	
	@Autowired 
	private DonneeDao donneeDao;

	public List<Contact> findByIduser(long iduser,boolean actif ) {

		List<Contact> liste = new ArrayList<Contact>();

		try {
			Connection conn = (Connection) database.getSqlConnection();

			String sql= "select idcontact,dtcreation,favoris,actif from contact where iduser=? and actif=?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ps.setLong(1, iduser);
			ps.setBoolean(2, actif);

			System.out.println(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contact c = new Contact(rs.getLong(1), rs.getDate(2).toLocalDate(), rs.getBoolean(3), iduser, rs.getBoolean(4), new ArrayList<Donnee>() );
				liste.add(c);
			}

			rs.close();
			ps.close();
			
			liste.forEach(x->{
				List<Donnee> listeDonnees=donneeDao.findByIdContact(x.getIdcontact());
				x.setDonnees(listeDonnees);
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}
	
	public Contact findByIdcontact(long idcontact,boolean actif ) {
		 Contact c=null;

		try {
			Connection conn = (Connection) database.getSqlConnection();

			String sql= "select idcontact,dtcreation,favoris,actif from contact where idcontact=? and actif=?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ps.setLong(1, idcontact);
			ps.setBoolean(2, actif);

			System.out.println(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				 c = new Contact(rs.getLong(1), rs.getDate(2).toLocalDate(), rs.getBoolean(3), 0, rs.getBoolean(4), new ArrayList<Donnee>() );				
			}

			rs.close();
			ps.close();			
							
			if (c!=null)
				c.setDonnees(donneeDao.findByIdContact(c.getIdcontact()));
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}


	public long Save(Contact c) {
		long result = 0;

		try {
			Connection conn = (Connection) database.getSqlConnection();

			String sql = "INSERT INTO CONTACT (idcontact,dtcreation,favoris,iduser,actif) VALUES (?,?,?,?,?) "+
						"ON DUPLICATE KEY UPDATE dtcreation=VALUES(dtcreation), favoris=VALUES(favoris), iduser=VALUES(iduser), actif=VALUES(actif)";

			System.out.println(sql);
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			ps.setLong(1, c.getIdcontact());
			ps.setDate(2, Date.valueOf(c.getdtcreation()));
			ps.setBoolean(3, c.getFavoris());
			ps.setLong(4, c.getIduser());
			ps.setBoolean(5, c.getActif());
			
			result = ps.executeUpdate();
			
			ResultSet rspk = ps.getGeneratedKeys();
			rspk.next();
			result = rspk.getLong(1);

			rspk.close();
			ps.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public long ActiverDesactiverByIdContact(long idcontact,boolean actif) throws Exception {
		long result = 0;

		try {
			Connection conn = (Connection) database.getSqlConnection();

			String sql = "UPDATE CONTACT SET actif=? WHERE idcontact=?";

			System.out.println(sql);
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

			ps.setBoolean(1, actif);
			ps.setLong(2, idcontact);
			
			result = ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result==0) 
			throw new Exception(String.format("Erreur dans ContactDao.ActiverDesactiver: idcontact=%d, actif=%s",idcontact,actif));
		
		return result;
	}
	
	public long ActiverDesactiverByContact(Contact c,boolean actif) throws Exception {		
		return ActiverDesactiverByIdContact(c.getIdcontact(), actif);
	}

}
