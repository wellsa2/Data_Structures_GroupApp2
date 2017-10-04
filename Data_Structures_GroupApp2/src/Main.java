import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try 
		{
			System.out.print("Enter file name here: ");
			
			Scanner input = new Scanner(System.in);
			
			input = new Scanner(file);
			
			while (input.hasNextline())
			{
				String line = input.nextInt();
				System.out.println(line);
			}
			input.close();
		}
	catch (FileNotFoundException e)
		{
		e.printStackTrace();
		}
	}
}
