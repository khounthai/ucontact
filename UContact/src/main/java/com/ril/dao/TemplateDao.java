package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

	public List<Template> getTemplates(long iduser, long idtemplate,boolean champactif) {
		List<Template> liste = new ArrayList<Template>();
		
		System.out.println("idtemplate="+idtemplate+" iduser="+ iduser);
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql;
			
			PreparedStatement ps =null;
			
			if (idtemplate==0)
			{
				sql = "select * from template where iduser is null";
				ps=conn.prepareStatement(sql);
			}				
			else
			{
				sql = "select * from template where idtemplate = ? and (iduser= ? or iduser is null)";
				ps=conn.prepareStatement(sql);
				ps.setLong(1, idtemplate);
				ps.setLong(2, iduser);
			}
			
			//System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				
				System.out.println("idtemplate="+rs.getLong(1)+" libelle="+rs.getString(2)+" iduser="+ rs.getLong(3));
				Template t = new Template(rs.getLong(1), rs.getString(2), rs.getLong(3),null);
				liste.add(t);
			}
			
			rs.close();
			ps.close();
									
			liste.forEach(t->{
				List<Champ> c=chamDao.getChamps(t.getIdtemplate(),champactif);
				t.setChamps(c);
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}
	
}
