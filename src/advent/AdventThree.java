package advent;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AdventThree {

	public static void main(String[] args) {
		File f = new File("C:\\Users\\leowa\\OneDrive\\Documents\\advent3.txt");
		
		List<String> wires = new ArrayList<>();
		try(Scanner sc = new Scanner(f)){
			while(sc.hasNext()) {
				wires.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		
		String[] wire1 = wires.get(0).split(",");
		String[] wire2 = wires.get(1).split(",");

//		System.out.println(mapGrid(wire1,wire2));
//		System.out.println(shortestIntersection(wire1,wire2));
		
//		List<int[]> wireList1 = mapGrid2(wire1);
//		List<int[]> wireList2 = mapGrid2(wire2);
//		System.out.println(wireList1.size());
//		System.out.println(wire2.length);
//		int min = Integer.MAX_VALUE;
		

//		System.out.println(mapGrid3(wire1,wire2));
		
		Set<Point> wireSet1 = mapGrid4(wire1);
		Set<Point> wireSet2 = mapGrid4(wire2);
		wireSet1.retainAll(wireSet2);
//		System.out.println(wireSet1.size());

		List<Integer> inters = new ArrayList<>();
		
		for(Point p : wireSet1) {
			int x = Math.abs(p.x);
			int y = Math.abs(p.y);
			inters.add(x+y);
//			System.out.println(x+y);
		}
		Collections.sort(inters);
		System.out.println(inters.get(1));
		
		Map<Point,Integer> wireSet3 = mapGrid5(wire1);
		Map<Point,Integer> wireSet4 = mapGrid5(wire2);
//		System.out.println(wireSet1.size());
		
		List<Integer> closest = new ArrayList<>();

		for(Point p : wireSet1) {
			if(p.x == 0 && p.y == 0) {
				continue;
			}
			int a = wireSet3.get(p);
			int b = wireSet4.get(p);
//			System.out.println(a);
//			System.out.println(b);
			closest.add(a+b);
		}
		Collections.sort(closest);
		System.out.println(closest.get(0));
	}
	
	
	static Map<Point, Integer> mapGrid5(String[] wire1) {
		
		Map<Point, Integer> grid = new HashMap<>();
		int row = 0;
		int col = 0;
		
		int steps = 0;
		
		System.out.println("wire 1");
		for(int i=0;i<wire1.length;i++) {
			String val = wire1[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						
						grid.put(new Point(row, col), steps);
						steps++;
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						
						grid.put(new Point(row, col), steps);
						steps++;
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						
						grid.put(new Point(row, col), steps);
						steps++;
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						
						grid.put(new Point(row, col), steps);
						steps++;
					}
				}
				break;
			}
		}
		return grid;
	}

	
	static int mapGrid(String[] wire1, String[] wire2) {
		
		int size = 20000;
		int center = size/2;
		boolean[][] grid = new boolean[size][size];
		
		int row = center;
		int col = center;
		
		System.out.println("wire 1");
		for(int i=0;i<wire1.length;i++) {
			String val = wire1[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						grid[row][col] = true;
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						grid[row][col] = true;
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						grid[row][col] = true;
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						grid[row][col] = true;
					}
				}
				break;
			}
		}
		
		
		row = center;
		col = center;
		List<Integer> inters = new ArrayList<>();
		
		System.out.println("wire 2");
		
		for(int i=0;i<wire2.length;i++) {
			String val = wire2[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						if(grid[row][col] == true && row != center && col != center) {
							inters.add(Math.abs(row - center) + Math.abs(col - center));
						}
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						if(grid[row][col] == true&& row != center && col != center) {
							inters.add(Math.abs(row - center) + Math.abs(col - center));
						}
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						if(grid[row][col] == true&& row != center && col != center) {
							inters.add(Math.abs(row - center) + Math.abs(col - center));
						}
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						if(grid[row][col] == true&& row != center && col != center) {
							inters.add(Math.abs(row - center) + Math.abs(col - center));
						}
					}
					break;
				}
				
			}
		}
//		System.out.println(inters.size());
		return Collections.min(inters);
	}
	
	
	static int shortestIntersection(String[] wire1, String[] wire2) {
		
		int size = 20000;
		int center = size/2;
		int[][] grid = new int[size][size];
		
		int row = center;
		int col = center;
		
		System.out.println("wire 1");
		for(int i=0;i<wire1.length;i++) {
			String val = wire1[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						grid[row][col] = 1;
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						grid[row][col] = 1;
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						grid[row][col] = 1;
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						grid[row][col] = 1;
					}
				}
				break;
			}
		}
		
		
		row = center;
		col = center;
		List<Integer> inters = new ArrayList<>();
		
		System.out.println("wire 2");
		
		for(int i=0;i<wire2.length;i++) {
			String val = wire2[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						if(grid[row][col] == 1) {
							grid[row][col] = 3;
						}
						else {
							grid[row][col] = 2;
						}
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						if(grid[row][col] == 1) {
							grid[row][col] = 3;
						}
						else {
							grid[row][col] = 2;
						}
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						if(grid[row][col] == 1) {
							grid[row][col] = 3;
						}
						else {
							grid[row][col] = 2;
						}
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						if(grid[row][col] == 1) {
							grid[row][col] = 3;
						}
						else {
							grid[row][col] = 2;
						}
					}
					break;
				}
				
			}
		}
		
		
		// walk wires
		System.out.println("walking1");
		row = center;
		col = center;
		int current = 1;
		int steps = 0;
		while(current == 1) {
			steps++;
//			System.out.println(steps);
			if(grid[row+1][col] == 1 || grid[row+1][col] == 3) {
				row++;
				if(grid[row+1][col] == 3) {
					inters.add(stepsFor2(grid,row,col,center)+steps);
				}
			}
			else if(grid[row-1][col] == 1 ||grid[row-1][col] == 3) {
				row--;
				if(grid[row-1][col] == 3) {
					inters.add(stepsFor2(grid,row,col,center)+steps);
				}
			}
			else if(grid[row][col+1] == 1 || grid[row][col+1] == 3) {
				col++;
				if(grid[row][col+1] == 3) {
					inters.add(stepsFor2(grid,row,col,center)+steps);
				}
			}
			else if(grid[row][col-1] == 1 || grid[row][col-1] == 3) {
				col--;
				if(grid[row][col-1] == 3) {
					inters.add(stepsFor2(grid,row,col,center)+steps);
				}
			}
			else break;
		}
		System.out.println(inters.size());
		return Collections.min(inters);
	}
	
	static int stepsFor2(int[][] grid, int interRow, int interCol, int center) {
		int row = center;
		int col = center;
		int current = 2;
		int steps = 0;
		boolean end = false;
		System.out.println("walking2");
		while(current == 2) {
			if(grid[row+1][col] == 2 || grid[row+1][col] == 3) {
				
				if(grid[row+1][col] == 3 && row == interRow && col == interCol) {
					end = true; 
				}
				row++;
			}
			else if(grid[row-1][col] == 2 ||grid[row-1][col] == 3) {
				if(grid[row-1][col] == 3 && row == interRow && col == interCol) {
					end = true; 
				}
				row--;
			}
			else if(grid[row][col+1] == 2 || grid[row][col+1] == 3) {
				if(grid[row][col+1] == 3 && row == interRow && col == interCol) {
					end = true; 
				}
				col++;
			}
			else if(grid[row][col-1] == 2 || grid[row][col-1] == 3) {
				if(grid[row][col-1] == 3 && row == interRow && col == interCol) {
					end = true; 
				}
				col--;
			}
			else break;
			steps++;
			System.out.println(steps);
			if(end) {
				break;
			}
		}
		return steps;
	}
	
	
	
	static List<int[]> mapGrid2(String[] wire1) {
		

		List<int[]> wireList1 = new ArrayList<>();
		int row = 0;
		int col = 0;
		
		System.out.println("wire 1");
		for(int i=0;i<wire1.length;i++) {
			String val = wire1[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						wireList1.add(new int[] {row,col});
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						wireList1.add(new int[] {row,col});
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						wireList1.add(new int[] {row,col});
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						wireList1.add(new int[] {row,col});
					}
				}
				break;
			}
		}
		return wireList1;
	}
	
	
	static int mapGrid3(String[] wire1, String[] wire2) {
		
		Set<String> grid = new HashSet<>();
		int row = 0;
		int col = 0;
		
		System.out.println("wire 1");
		for(int i=0;i<wire1.length;i++) {
			String val = wire1[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						String curr = row + "," + col;
						grid.add(curr);
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						String curr = row + "," + col;
						grid.add(curr);
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						String curr = row + "," + col;
						grid.add(curr);
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						String curr = row + "," + col;
						grid.add(curr);
					}
				}
				break;
			}
		}
		
		
		row = 0;
		col = 0;
		Set<Integer> inters = new HashSet<>();
		
		System.out.println("wire 2");
		
		for(int i=0;i<wire2.length;i++) {
			String val = wire2[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						String curr = row + "," + col;
						if(!grid.add(curr)) {
							inters.add(Math.abs(row)+Math.abs(col));
						}
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						String curr = row + "," + col;
						if(!grid.add(curr)) {
							inters.add(Math.abs(row)+Math.abs(col));
						}
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						String curr = row + "," + col;
						if(!grid.add(curr)) {
							inters.add(Math.abs(row)+Math.abs(col));
						}
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						String curr = row + "," + col;
						if(!grid.add(curr)) {
							inters.add(Math.abs(row)+Math.abs(col));
						}
					}
					break;
				}
				
			}
		}
		System.out.println(inters.size());
		return Collections.min(inters);
	}
	
	static Set<Point> mapGrid4(String[] wire1) {
		
		Set<Point> grid = new HashSet<>();
		int row = 0;
		int col = 0;
		
		System.out.println("wire 1");
		for(int i=0;i<wire1.length;i++) {
			String val = wire1[i];
			char dir = val.charAt(0);
			int dist = Integer.parseInt(val.substring(1));
			
			switch(dir) {
				case 'R' : {
					int limit = row + dist;
					for(;row<limit;++row) {
						grid.add(new Point(row, col));
					}
					break;
				}
				case 'L' : {
					int limit = row - dist;
					for(;row>limit;--row) {
						grid.add(new Point(row, col));
					}
					break;
				}
				case 'U' : {
					int limit = col - dist;
					for(;col>limit;--col) {
						grid.add(new Point(row, col));
					}
					break;
				}
				case 'D' : {
					int limit = col + dist;
					for(;col<limit;++col) {
						grid.add(new Point(row, col));
					}
				}
				break;
			}
		}
		return grid;
	}
	
	


}
