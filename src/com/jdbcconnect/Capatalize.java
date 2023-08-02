package com.jdbcconnect;

public class Capatalize {
	
	public static void main(String[] args) {
		
		String str = "india is my contry";
		
		String str2[] = str.split(" ");

		System.out.println(str.toString());
		
		for(int i=0;i<str2.length;i++)
		{
			char ch[]=str2[i].toCharArray();
			
			ch[0]=(char) (ch[0]-32);
			System.out.print(ch);
			System.out.print(" ");
			
		}

		
		
		
		
	}

}
