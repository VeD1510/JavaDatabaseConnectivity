package com.jdbcconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo extends FetchDetails {
	
	static Connection con = null;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	static Scanner sc = new Scanner(System.in);
	
	public PreparedStatementDemo(int a)
	{
		super(2);
	}
	
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
		
		//PreparedStatementDemo obj = new PreparedStatementDemo();
		PreparedStatementDemo obj1 = new PreparedStatementDemo(3);
		
		int ch;
		
		do
		{
			System.out.println("1. Insert  By Prepared/n2. Update By Prepared/n3. Delete By Prepared"
					+ "/n4. Insert  By Resultset/n5. Insert  By Resultse/n6. Insert  By ResultSet/n7. EXIT");
			
			
			ch=sc.nextInt();
		}while(ch!=7);
		
		//obj.InsertModel();
		//obj.deleteModel();
		//obj.DeleteModel2();
		//obj.UpdateModel();
	}

}
