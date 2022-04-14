import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BonusProject {
	public static void main(String[] args) throws FileNotFoundException {
		guessGame("numGuesses");
	}
	
	public static void guessGame(String outFile) throws FileNotFoundException{
		//Scanner gradesScan = new Scanner(new File(inFile));
		PrintWriter pw = new PrintWriter(outFile);

		Scanner sc = new Scanner(System.in);
		int temp = 0;
		int random = 0;
		String tempStr = "";
		String wantToPlay = "Y";
		int numGuesses = 0;
		
		while(wantToPlay.equals("Y")) {
			temp = 0;
			random = (int)(Math.random() * 10) + 1;
			System.out.print("Start your guessing: ");
			while(temp != random) {
				temp = sc.nextInt();
				numGuesses += 1;
				if(temp > random) {
					System.out.println("Too High");
					System.out.print("Try again: ");
				} else if(temp < random) {
					System.out.println("Too Low");
					System.out.print("Try again: ");
				}
				else {
					System.out.println("Correct!!!");
					System.out.println(numGuesses);
				}
			}
			System.out.print("Would you like to play again (Please enter either a Y or N): ");
			tempStr = sc.next();
			while(!(tempStr.equals("Y")) && !(tempStr.equals("N"))) {
				System.out.print("Please enter either Y or N: ");
				tempStr = sc.next();
			}
			wantToPlay = tempStr;
			pw.println(numGuesses);
			numGuesses = 0;
		}
		//gradesScan.close();
		pw.close();
	}
}
