package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.ResultSetMetaData;
import com.ril.entity.Champ;
import com.ril.entity.Template;

@Repository
public class TemplateDao {
	@Autowired
	private Database database;
	
	@Autowired
	ChampDao chamDao;

	public List<Template>  getTemplates(long iduser, long idtemplate) {
		List<Template> liste = new ArrayList<Template>();
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql;
			if (idtemplate==0)
				sql = "select * from template where iduser is null";
			else
			{
				sql = "select * from template where idtemplate = %d and (iduser= %d or iduser is null)";
				sql=String.format(sql, idtemplate,iduser);
			}
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				
				Template t = new Template(rs.getLong(1),rs.getString(2),rs.getLong(3),null);
				liste.add(t);
			}
			rs.close();
			ps.close();
			
						
			liste.forEach(t->{
				List<Champ> c=chamDao.getChamps(t.getIdtemplate());
				t.setChamps(c);
			});
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}
	
}
