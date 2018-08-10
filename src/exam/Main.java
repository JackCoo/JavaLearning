package exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.DoubleToLongFunction;


public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\exam\\Main"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str = in.nextLine();

		in.close();
	}
	
	private static void process(String str) {
		char[] strArray = str.toCharArray();
		Map<Character, Integer> result = new TreeMap<>();
		for (int i = 0; i < strArray.length; i++) {
			result.put(strArray[i], result.getOrDefault(strArray[i], 0) + 1);
		}
		for (Entry<Character, Integer> entry : result.entrySet()) {
			if (entry.getValue() > 1) {
				System.out.print(entry.getKey());
			}
		}
		
	}
}
