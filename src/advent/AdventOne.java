package advent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdventOne {
	
	public static void main(String[] args) {
		File f = new File("C:\\Users\\leowa\\OneDrive\\Documents\\20190112.txt");
		
		double sum = 0;
		try(Scanner sc = new Scanner(f)){
			while(sc.hasNext()) {
				double num = sc.nextDouble();
				sum += calculateGasRecursive(num);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
//		System.out.println(calculateGasRecursive(14));
		System.out.println(sum);
		
	}

	
	public static double calculateGas(double mass) {
		return (Math.floor(mass / 3)) - 2;
	}
	
	public static double calculateGasRecursive(double mass) {
//		System.out.println("init=" + mass);
		
		double num = ((Math.floor(mass / 3)) - 2);
		if(num <= 0) {
//			System.out.println("returning 0");
			return 0;
		}
		
//		System.out.println("num=" + num);
		return num + calculateGasRecursive(num);
	}
	
}


