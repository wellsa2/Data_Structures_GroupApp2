import sun.awt.geom.AreaOp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Boolean userFinished = false;
		Boolean usedGUI = false;
		String file;
		Scanner userScanner = new Scanner(System.in);

		while (!userFinished)
		{
			System.out.printf("Choose GUI or file:%nGUI%nValid Single%nValid Multi%nInvalid Multi%n");
			switch (userScanner.nextLine())
			{
				case "GUI":
					if (!usedGUI)
					{
						GUIRunner.main(new String[] {});
						usedGUI = true;
					}
					else
					{
						System.out.println("GUI cannot be run twice");
					}
					break;

				case "Valid Single":
					file = System.getProperty("user.dir") + "/Infix Calculator Expressions - valid -- 2016-10-13 01.txt";
					solveFile(file);
					break;

				case "Valid Multi":
					file = System.getProperty("user.dir") +"/Infix Calculator Expressions - valid multi-digit -- 2016-10-04 01.txt";
					solveFile(file);
					break;

				case "Invalid Multi":
					file = System.getProperty("user.dir") + "/Infix Calculator Expressions - multi-digit with invalid expressions -- 2016-10-04 01.txt";
					solveFile(file);
					break;

				default:
					userFinished = true;
			}
		}
	}//end main


	private static void solveFile(String fileName)
	{
		try
		{
			Scanner fileScanner = new Scanner(new FileReader(fileName));
			Calculator calculator = new Calculator();
			while (fileScanner.hasNextLine())
			{
				calculator.setEquationString(fileScanner.nextLine());
				if (calculator.getResult().equals("java.lang.ArithmeticException: / by zero"))
				{
					System.out.println(calculator.getEquationString() + " = " + "Error: Cannot divide by zero");
				}
				else
				{
					System.out.println(calculator.toString());
				}
			}
		}
		catch (FileNotFoundException fileNotFound)
		{
			System.out.println("File " + fileName + " not found!");
		}
	}
}
		
