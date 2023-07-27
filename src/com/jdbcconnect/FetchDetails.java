package com.jdbcconnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchDetails {
	
	static Connection con=null;
	Statement st;
	ResultSet rs;
	
	public FetchDetails() {
		
		con=DatabaseConnection.getconnect();
		System.out.println("Connection Done...");
		
		try 
		{
			st=con.createStatement();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchModelDetails()
	{
		try 
		{
			rs=st.executeQuery("select * from model");
			System.out.println("---------------------ModelDetails-------------------");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"         "+rs.getInt(3));
			}
			System.out.println("------------------------------------------------------");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchCustomerDetails()
	{
		try 
		{
			rs=st.executeQuery("select * from customer");
			System.out.println("---------------------CustomerDetails-------------------");
			while(rs.next())
			{
				System.out.println(rs.getInt("custid")+"   "+rs.getString(2)+"   "+rs.getString(3));
				
			}
			System.out.println("----------------------------------------------------------");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		FetchDetails f1 = new FetchDetails();
		f1.fetchModelDetails();
		f1.fetchCustomerDetails();
	}
	
	
}
