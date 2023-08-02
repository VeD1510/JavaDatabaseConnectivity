package com.callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class FetchModelProcedure {
	
	public Connection con = null;
	Statement st;
	ResultSet rs,rs1;
	CallableStatement cst;
	PreparedStatement pst;
	static Scanner sc = new Scanner(System.in);
	
	public FetchModelProcedure() {
		
		con = DatabaseConnection.getconnect();
		
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
	
	public void fetchModelCustomers()
	{
		try 
		{
			cst=con.prepareCall("{call displayCustomers()}");
			System.out.println("-----------------------------Customers----------------------------------------");
			if(cst.execute())
			{
				rs=cst.getResultSet();
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getString(6));
				}
			}
			System.out.println();
			System.out.println("---------------------------------Models------------------------------------");
			
			if(cst.getMoreResults())
			{
				rs1=cst.getResultSet();
				while(rs1.next())
				{
					System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getInt(3));
				}
						
			}
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchModelCount() 
	{
		System.out.println("Enter Model ID:");
		int mid = sc.nextInt();
		try 
		{
			cst=con.prepareCall("{call countModel(?,?,?)}");
			cst.setInt(1, mid);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.registerOutParameter(3, Types.INTEGER);
			
			
			if(cst.execute())
			{
				ResultSet rs = cst.getResultSet();
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"  "+rs.getInt(2));
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		
		FetchModelProcedure obj = new FetchModelProcedure();
		//obj.fetchModelCustomers();
		obj.fetchModelCount();
		
		
	}





}
