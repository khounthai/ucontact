package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ril.entity.DataType;

@Repository
public class DataTypeDao {
	@Autowired
	private Database database;
	
	public DataType getDataType(long iddatatype) {
		DataType d = null;

		try {
			Connection conn = database.getSqlConnection();

			String sql = "SELECT iddatatype,libelle,regex FROM datatype WHERE iddatatype=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iddatatype);
			ResultSet rs = ps.executeQuery();
			String regex;
			
			while (rs.next()) {
				if (rs.getString(3)==null)
					regex="";
				else
					regex=rs.getString(3);
					
				d = new DataType(rs.getLong(1), rs.getString(2),regex);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}
}
