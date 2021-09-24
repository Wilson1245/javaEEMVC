/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ConnectionDataBase {
       public List connectionDataSource(){
           Connection conn = null;
           String url = "jdbc:postgresql://john.db.elephantsql.com:5432/jfxrsbdq";
           List list = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, "jfxrsbdq", "IwjwxOnT88lCjGHGp5LVdehUoOvl_67a");
			System.out.println("Database connected...");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".purchase ");
			while (rs.next()) {
				System.out.println(rs.getString(1));
                                list.add(rs);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Test failed");
		} finally {
			return list;
		}
       }
}
