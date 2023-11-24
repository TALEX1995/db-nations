package org.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	//	SELECT countries.country_id, countries.name as country_name, regions.name as region_name, continents.name as continent_name
	//	FROM `countries`
	//	JOIN regions
	//	ON countries.region_id = regions.region_id
	//	JOIN continents
	//	ON regions.continent_id = continents.continent_id
	//	ORDER BY countries.name;
	
	private final static String url = "jdbc:mysql://localhost:3306/nations";
	private final static String username = "root";
	private final static String password = "root";
	
	public static void main(String[] args) throws SQLException {
		
		
		Connection conn = null;
		// Create connection witd db
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			final String SQL = "SELECT countries.country_id, countries.name AS country_name, regions.name AS region_name, continents.name AS continent_name " 
								+ "FROM countries " 
								+ "JOIN regions ON countries.region_id = regions.region_id " 
								+ "JOIN continents ON regions.continent_id = continents.continent_id " 
								+ "ORDER BY countries.name";
			
			
			try (PreparedStatement ps = conn.prepareStatement(SQL)){
				
				try(ResultSet rs = ps.executeQuery()){
			    	
			    	while(rs.next()) {
			    		
			    		int country_id = rs.getInt(1);
			    		String country_name = rs.getString(2);
			    		String region_name = rs.getString(3);
			    		String continent_name = rs.getString(4);
			    		
			    		System.out.println("[" + country_id + "] - " + country_name + " - " + region_name + " - " + continent_name);
			    	}
			    }
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
	        if(conn != null) {
	        	conn.close();
	        }
	    }
	}
}
