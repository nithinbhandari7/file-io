import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FileIO {
	public static void main(String[] args) throws FileNotFoundException {
		cleanFile("messy", "neat");
		sales("Sales", "Sales Data");
		grades("Grades", "GradesOutput");
	}
	
	public static void cleanFile(String inFile, String outFile) throws FileNotFoundException {
		Scanner fScan = new Scanner(new File(inFile));
		PrintWriter pw = new PrintWriter(outFile);
		
		while(fScan.hasNextLine()) {
			String line = fScan.nextLine();
			int commaIndex = line.indexOf(",");
			String firstHalf = line.substring(0, commaIndex);
			String secondHalf = line.substring(commaIndex + 1);
			firstHalf = firstHalf.trim();
			secondHalf = secondHalf.trim();
			pw.println(firstHalf + "," + secondHalf);
		}
		pw.close();
		fScan.close();
	}
	
	public static double findAverage(String fileName) throws FileNotFoundException {
		double sum = 0;
		int count = 0;
		
		Scanner fileScan = new Scanner(new File(fileName));
		
		//1. connect Scanner to file
		//2. process file
		//3. close Scanner
		
		while(fileScan.hasNextDouble()) {
			//read in a number
			//add it to our sum
			//add to our count
			sum += fileScan.nextDouble();
			count += 1;
		}
		
		fileScan.close();
		
		double average = sum / count;
		
		return average;
	}
	
	public static void sales(String inFile, String outFile) throws FileNotFoundException{
		Scanner salesScan = new Scanner(new File(inFile));
		PrintWriter pw = new PrintWriter(outFile);
		DecimalFormat df = new DecimalFormat(".00");
		
		double monthTotal = 0;
		double highest = 0;
		String greatestMonth = "";
		String currentMonth;
		
		pw.println("Month				TotalSales");
		pw.println();
		
		while(salesScan.hasNextLine()) {
			currentMonth = salesScan.nextLine();
			pw.print(currentMonth);
			while(salesScan.hasNextDouble()) {
				monthTotal += salesScan.nextDouble();
			}
			if(monthTotal > highest) {
				highest = monthTotal;
				greatestMonth = currentMonth;
			}
			
			if(monthTotal > 0) {
				for(int i = 0; i < 20 - currentMonth.length(); i++) {
					pw.print(" ");
				}
				pw.println("$" + df.format(monthTotal));
			}
			monthTotal = 0;
		}
		
		pw.println();
		pw.print("Month with highest sales: " + greatestMonth);
		
		salesScan.close();
		pw.close();
	}
	
	public static void grades(String inFile, String outFile) throws FileNotFoundException{
		Scanner gradesScan = new Scanner(new File(inFile));
		PrintWriter pw = new PrintWriter(outFile);
		
		double totalPoints = 0;
		double studentTotal = 0;
		String student = "";
		
		gradesScan.next();
		while(gradesScan.hasNextDouble()) {
			totalPoints += gradesScan.nextDouble();
		}
		
		while(gradesScan.hasNextLine()) {
			student = gradesScan.next();
			pw.print(student);
			while(gradesScan.hasNextDouble()) {
				studentTotal += gradesScan.nextDouble();
			}
			for(int i = 0; i < 20 - student.length(); i++) {
				pw.print(" ");
			}
			pw.println((int)(((studentTotal/totalPoints) * 10000) + 0.5) / 100.0 + "%");
			studentTotal = 0;
		}
		
		gradesScan.close();
		pw.close();
	}
}


/*
Example 1: Find the average of the numbers in a file

Example 2: For a file that contains data in the format:
			some word or words   ,     some more words
			Write the data to an output file in the format:
			some word or words,some more words
			**see messy.txt

Example 3: see the input file sales.txt
			process the data in such a way that produces the 
			output file salesData.txt
			This is a partner activity
*/