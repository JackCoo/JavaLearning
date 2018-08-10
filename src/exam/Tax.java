package exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tax {

	
	public static void main(String[] args) {
		
		
		Scanner in = new Scanner(System.in);
		
		 try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\exam\\Tax"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		int t = in.nextInt();
		
		for (int i = 0; i < t; i++) {
			int a = in.nextInt() - 5000; // 应纳税金额
			double tax = 0;
			while (a > 0) {
				if (a > 80000) {
					tax += (a - 80000) * 0.45;
					a = 80000;
				} else if (a > 55000) {
					tax += (a - 55000) * 0.35;
					a = 55000;
				} else if (a > 35000) {
					tax += (a - 35000) * 0.30;
					a = 35000;
				} else if (a > 25000) {
					tax += (a - 25000) * 0.25;
					a = 25000;
				} else if (a > 12000) {
					tax += (a - 12000) * 0.20;
					a = 12000;
				} else if (a > 3000) {
					tax += (a - 3000) * 0.10;
					a = 3000;
				} else if (a > 0) {
					tax += (a - 0) * 0.03;
					a = 0;
				}
			}
			System.out.println(Math.round(tax));
			
		}
	}
}
