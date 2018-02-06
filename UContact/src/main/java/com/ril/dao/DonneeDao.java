package com.ril.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.ril.entity.Donnee;

@Repository
public class DonneeDao {
	@Autowired
	private Database database;
	
	public long Save(Donnee d)
	{
		long result=0;
			try {
				Connection conn = database.getSqlConnection();		
				
			    String sql="INSERT INTO donnees (dtenregistrement, valeur, idcontact, idchamp) VALUES (?,?,?,?)";
				
			 	System.out.println(sql);
			 				 	
				PreparedStatement ps = conn.prepareStatement(sql,com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

				ps.setDate(1,Date.valueOf(d.getDtenregistrement()));
				ps.setString(2,d.getValeur());
				ps.setLong(3,d.getIdcontact());
				ps.setLong(4, d.getIdchamp());

				result = ps.executeUpdate();
				ResultSet rspk = ps.getGeneratedKeys();
				rspk.next();
				result = rspk.getLong(1);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return result;
	}
}
