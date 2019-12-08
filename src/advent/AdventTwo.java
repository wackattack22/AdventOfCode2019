package advent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdventTwo {

	public static void main(String[] args) {
		File f = new File("C:\\Users\\leowa\\OneDrive\\Documents\\advent2.txt");
		
		String input = null;
		try(Scanner sc = new Scanner(f)){
			if(sc.hasNext()) {
				input = sc.next();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
//		System.out.println(input);
		String[] s = input.split(",");
		
		int[] opCode = new int[s.length];
		
		for(int i=0;i<s.length;i++) {
			opCode[i] = Integer.parseInt(s[i]);
		}
//		System.out.println(opCode.length);
		// replace here
//		opCode[1] = 12;
//		opCode[2] = 2;
		int[] reset = opCode.clone();
		for(int i = 0;i<100;i++) {
			for(int j = 0;j<100;j++) {
				opCode = reset.clone();
				opCode[1] = i;
				opCode[2] = j;
				if(parseOpCode(opCode) == 19690720) {
					System.out.println(100 * i + j);
					break;
				}
			}
		}
//		System.out.println(parseOpCode(opCode));
	}
	
	static int parseOpCode(int[] opCode) {
	
		for(int i=0;i<opCode.length;i+=4) {
			if(opCode[i] == 99) {
				break;
			}
			int val = 0;
			if(opCode[i] == 1) {
				int in1 = opCode[i+1];
				int in2 = opCode[i+2];
				
				val = opCode[in1] + opCode[in2];
			}
			else if(opCode[i] == 2) {
				int in1 = opCode[i+1];
				int in2 = opCode[i+2];
				
				val = opCode[in1] * opCode[in2];
			}
			int index = opCode[i+3];
			opCode[index] = val;
		}
		
		return opCode[0];
	}

}
