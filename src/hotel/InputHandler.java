package hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

	
	public int getIntegerInput(int min,int max)
	{
		int n=getIntegerInput();
		while(n<min||n>max)
		{
			System.out.println("Enter input within "+ min+" to "+ max);
			n=getIntegerInput();
		}
		
		return n;
	
	}
	public  int getIntegerInput()
	{   int n=0;
	    boolean error=false;
		do
	    {try
		 {Scanner sc=new Scanner(System.in);
		  n=sc.nextInt();
		  error=false;
	     }
		 catch( InputMismatchException ex)
			{System.out.println("Enter Integer Input");
			 error=true;
			}
		
		}while(error);
		return n;
	}
	public String getStringInput()
	{
		Scanner sc=new Scanner(System.in);
		String string=sc.next();
		return string;
	}
	public Date getDateInput()
	{
		Date date=null;
		boolean error=false;
		do
		{
			try
			{
				Scanner s=new Scanner(System.in);
				SimpleDateFormat ft = 
					      new SimpleDateFormat ("yyyy-MM-dd");
				System.out.println("Enter date in yyyy-mm-dd format");
				String dateString=s.next();
                date=ft.parse(dateString);
                error=false;
			}
			catch( InputMismatchException | ParseException ex)
			{
				System.out.println("Wrong input, Enter date in yyyy-mm-dd format");
			   error=true;
			}
			
		}while(error);
		return date;
	}
	
	
	
	
	
	
	
}
