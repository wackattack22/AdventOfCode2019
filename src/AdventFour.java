
public class AdventFour {

	public static void main(String[] args) {
		int min = 284639;
//		int min = 288888;
		
		int max = 748759;
		
		
		System.out.println(calculate(max));
		
	}
	
	static int calculate2(int min, int max, int len) {
		String nu = String.valueOf(min);
		int[] num = {nu.charAt(0), nu.charAt(1), nu.charAt(2), nu.charAt(3), nu.charAt(4), nu.charAt(5)};
		
		return 0;
	}
	
	static int calculate(int max) {
		boolean hasAdjacent = false;
		int count = 0;
		
		for(int a = 3;a < 10;a++) {
			for(int b = a; b<10;b++) {
				for(int c=b;c<10;c++) {
					for(int d=c;d<10;d++) {
						for(int e=d;e<10;e++) {
							for(int f=e;f<10;f++) {
								StringBuilder sb = new StringBuilder();
								int curr = Integer.parseInt(sb.append(a).append(b).append(c).append(d).append(e).append(f).toString());
								if(a==b || b==c || c==d || d==e || e==f) {
									count++;
									System.out.println(curr);
								}
								
								
								if(curr >= max) {
									return count;
								}
							}
						}
					}
				}
			}
		}
		return count;
	}
}
