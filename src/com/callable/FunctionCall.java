package com.callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;



public class FunctionCall {
	
	Statement st;
	ResultSet rs;
	public Connection con=null;
	CallableStatement cst;
	static Scanner sc = new Scanner(System.in);
	
	public FunctionCall()
	{
		con=DatabaseConnection.getconnect();
		//System.out.println("connected");
		
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
	
	public void countFunction()
	{
		try 
		{
			cst = con.prepareCall("{?=call countCustomer}");
			cst.registerOutParameter(1,Types.INTEGER);
			
			
			
			boolean status=cst.execute();			
			if(!status)
			{
				System.out.println("Total no OF Customers are: "+cst.getInt(1));
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchModel()
	{
		System.out.println("Enter Model ID: ");
		int mid = sc.nextInt();
		
		try
		{
			cst = con.prepareCall("{?=call searchModel(?)}");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, mid);
			
			boolean status = cst.execute();
			if(!status)
			{
				System.out.println("Model Name: "+cst.getString(1));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		
		FunctionCall obj = new FunctionCall();
		obj.countFunction();
	    //obj.searchModel();
	}

}
