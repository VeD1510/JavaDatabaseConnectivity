package com.jdbcconnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FetchDetails {
	
	static Connection con=null;
	Statement st;
	ResultSet rs;
	static Scanner sc = new Scanner(System.in);
	
	public FetchDetails(int a)
	{
		con=DatabaseConnection.getconnect();
		System.out.println("Connection Done...");
		
		try 
		{
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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
	
	public void fetchCustomerDetails(int a)
	{
		try 
		{
			rs=st.executeQuery("select * from customer");
			System.out.println();
			System.out.println("---------------------CustomerDetails-------------------");
			rs.afterLast();
			while(rs.previous())
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
	
	public void insertByResultset()
	{
		try 
		{
			rs=st.executeQuery("select * from model");
			rs.moveToInsertRow();
			
			System.out.println("Enter Model ID:");
			int mid = sc.nextInt();
			System.out.println("Enter Model Name:");
			String mname = sc.next();
			System.out.println("Enter Model Cost:");
			int cost = sc.nextInt();
			
			rs.updateInt(1, mid);
			rs.updateString(2, mname);
			rs.updateInt(3, cost);
			rs.insertRow();
			fetchModelDetails();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
	}
	
	public void deleteByResultset()
	{
		try 
		{
			rs=st.executeQuery("select * from model");
			fetchModelDetails();
			rs.beforeFirst();
			System.out.println("Enter Model ID To Be Deleted:");
			int mid = sc.nextInt();
			
			while(rs.next())
			{
				if(rs.getInt(1)==mid)
				{
					rs.deleteRow();
				}
			}
			fetchModelDetails();
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void updateByResultset()
	{
		try 
		{
			rs=st.executeQuery("select * from model");
			fetchModelDetails();
			rs.beforeFirst();
			System.out.println("Enter Model ID To Update: ");
			int mid = sc.nextInt();
			
			while(rs.next())
			{
				if(rs.getInt(1)==mid)
				{
					System.out.println("Model Name: "+rs.getString(2));
					System.out.println("Old Cost: "+rs.getInt(3));
					System.out.println("Enter New Cost: ");
					int mcost = sc.nextInt();
					rs.updateInt(3, mcost);
					rs.updateRow();
				}
			}
			fetchModelDetails();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		
		//FetchDetails f1 = new FetchDetails();
		FetchDetails f2 = new FetchDetails(2);
		//f1.fetchModelDetails();
		//f1.fetchCustomerDetails();
		//f2.fetchCustomerDetails(5);
		//f2.insertByResultset();
		//f2.deleteByResultset();
		f2.updateByResultset();
		
	}



	
	
}
