package com.jdbcconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo {
	
	static Connection con = null;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	static Scanner sc = new Scanner(System.in);
	
	public PreparedStatementDemo() {
		// TODO Auto-generated constructor stub
		con = DatabaseConnection.getconnect();
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
	
	public void InsertModel()
	{
		System.out.println("Enter No. Of Models To Be Inserted:");
		int num = sc.nextInt();
		
		for(int i=0;i<num;i++)
		{
		
		System.out.println("Enter Model ID:");
		int mid = sc.nextInt();
		System.out.println("Enter Model Name:");
		String mname = sc.next();
		System.out.println("Enter Model Cost:");
		int cost = sc.nextInt();
		
		try 
		{
			pst = con.prepareStatement("insert into model () values(?,?,?)");
			pst.setInt(1, mid);
			pst.setString(2, mname);
			pst.setInt(3, cost);
			
			int noOfRowsInserted = pst.executeUpdate();
			
			if(noOfRowsInserted>=1)
				System.out.println("Model Inserted Succefully...");
			else
				System.err.println("Something Went Wrong");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
	public void deleteModel()
	{
		// static --> Statement : Compiled Everytime
		System.out.println("Enter Model ID To Be Deleted:");
		int mid = sc.nextInt();
		
		
		try 
		{
			int noOfRowsDeleted = st.executeUpdate("delete from model where modelid='"+mid+"'");
			
			if(noOfRowsDeleted>=1)
				System.out.println("Model Deleted...");
			else
				System.err.println("Something Went Wrong...");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void DeleteModel2()
	{
		//Dynamic Way: Faster Execution
		System.out.println("Enter Model ID To Be Deleted:");
		int mid = sc.nextInt();
		
		try 
		{
			pst = con.prepareStatement("delete from model where modelid=?");
			pst.setInt(1, mid);
			
			int noOfRowsDeleted=pst.executeUpdate();
			if(noOfRowsDeleted>=1)
				System.out.println("Model Deleted...");
			else
				System.out.println("Something Went Wrong...");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void UpdateModel()
	{
		System.out.println("Enter Model ID To Update Cost:");
		int mid = sc.nextInt();
		System.out.println("Enter New Cost: ");
		int ncost = sc.nextInt();
		
		try 
		{
			pst = con.prepareStatement("update model\r\n"
					+ "set cost =?\r\n"
					+ "where modelid=?;");                                 
			pst.setInt(1, ncost);
			pst.setInt(2, mid);
			
			int noOfRowsUpdated=pst.executeUpdate();
			if(noOfRowsUpdated>=1)
				System.out.println("Model Updated...");
			else
				System.err.println("Something Went Wrong...");
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		PreparedStatementDemo obj = new PreparedStatementDemo();
		//obj.InsertModel();
		//obj.deleteModel();
		//obj.DeleteModel2();
		//obj.UpdateModel();
	}

}
