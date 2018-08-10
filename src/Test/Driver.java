package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\Test\\Driver"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		int g = in.nextInt();
		
		int[] log = new int[n];
		for (int i = 0; i < n;i++) {
			log[i] = in.nextInt();
		}
		
		int[] result = new int[n];
		result[0] = log[0];
		for (int i = 1; i < n; i++) {
			result[i] = result[i - 1] + log[i];
		}
		
		int max = Arrays.stream(result).max().getAsInt();
		int min = Arrays.stream(result).min().getAsInt();
		
		int originalMax = g - max;
		int originalMin = 0 - min >= 0 ? 0 - min : 0;
		System.out.println(originalMax - originalMin + 1);
		
		in.close();
	}
}
