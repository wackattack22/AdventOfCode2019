package advent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class AdventEight {

	public static void main(String[] args) throws Exception {
		File f = new File("C:\\Users\\leowa\\eclipse-workspace\\AdventCode2019\\src\\resources\\advent8.txt");
		
		String input = null;
		try(Scanner sc = new Scanner(f)){
			if(sc.hasNext()) {
				input = sc.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0;i<input.length();i++) {
			q.add(Character.getNumericValue(input.charAt(i)));
		}
		
//		System.out.println(calculate(q, 25, 6));
		
		List<int[][]> layers = populateLayers(q, 25, 6);
		int[][] image = combineLayers(layers, 25, 6);
		printImage(image, 25, 6);
	}
	
	static int calculate(Queue<Integer> input, int width, int length) {
		Map<Integer, Integer> leastZeroes = new HashMap<>();
		int zeroes = 0;
		int ones = 0;
		int twos = 0;
		int count = 0;
		
		while(!input.isEmpty()) {
			int current = input.poll();
			
			if(current == 0) {
				zeroes++;
			}
			if(current == 1) {
				ones++;
			}
			else if(current == 2) {
				twos++;
			}
			
			if(++count == (width * length)) {
				System.out.println("count="+count);
				System.out.println("zeroes="+zeroes);
				System.out.println("ones="+ones);
				System.out.println("twos="+twos);
				System.out.println("-------------");
				leastZeroes.put(zeroes, (ones * twos));
				zeroes = 0;
				ones = 0;
				twos = 0;
				count = 0;
			}
		}
		return leastZeroes(leastZeroes);
	}
	
	static int leastZeroes(Map<Integer, Integer> map) {
		int min = Integer.MAX_VALUE;
		int val = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
		    int key = entry.getKey();
		    int value = entry.getValue();
		    
		    if(key < min) {
		    	min = key;
		    	val = value;
		    }
		}
		return val;
	}
	
	static List<int[][]> populateLayers(Queue<Integer> input, int width, int length) {
		List<int[][]> layers = new ArrayList<>();
		
		while(!input.isEmpty()) {
			int[][] layer = new int[width][length];
			for(int j = 0;j<length;j++) {
				for(int i=0;i<width;i++) {
					layer[i][j] = input.poll();
				}
			}
			layers.add(layer);
		}
		return layers;
	}
	
	static int[][] combineLayers(List<int[][]> layers, int width, int length) {
		int size = layers.size();
		
		int[][] combined = layers.get(size - 1);
		
		for(int i = size-2;i>=0;i--) {
			int[][] layer = layers.get(i);
			
			for(int j = 0;j<length;j++) {
				for(int k = 0;k<width;k++) {
					int newVal = layer[k][j];
					
					if(newVal != 2) {
						combined[k][j] = newVal;
					}
				}
			}
		}
		return combined;
	}
	
	static void printImage(int[][] image,int width, int length) {
		for(int i=0;i<length;i++) {
			for(int j=0;j<width;j++) {
				System.out.print(image[j][i]);
			}
			System.out.println();
		}
	}
}
