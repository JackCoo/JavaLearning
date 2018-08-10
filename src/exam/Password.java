package exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Password {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\exam\\Password"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			process(in.nextLine());
		}
		
	}
	
	private static void process(String password) {
		
		if (password.contains("password") || password.contains("admin") || password.contains("qwert") || password.contains("hello")
				|| password.contains("iloveyou") || password.contains("112233")) {
			System.out.println("no");
			return;
		}
		
		if (password.length() < 8) {
			System.out.println("no");
			return;
		}
		
		boolean containBig = false;
		boolean containSmall = false;
		boolean containNum = false;
		boolean containSpcial = false;
		
		char pre2 = password.charAt(0);
		char pre1 = password.charAt(1);;
		
		for (int i = 0; i < password.length(); i++) {
			char cur = password.charAt(i);
			if (cur >= 'A' && cur <= 'Z') {
				containBig = true;
			} else if (cur >= 'a' && cur <= 'z') {
				containSmall = true;
			} else if (cur >= '0' && cur <= '9') {
				containNum = true;
			} else if ((cur >= '!' && cur <= '/' && cur != '"') || (cur >= '<' && cur <= '@'
					|| (cur == '[' || cur == ']' || cur == '^' || cur == '_' 
					|| cur == '{' || cur == '}') )) {
				containSpcial = true;
			}
			
			if (cur >= 2 && cur - pre1 == pre1 - pre2 && cur - pre1 >= 0) {
				if ((cur >= '0' && cur <= '9' && pre1 >= '0' && pre1 <= '9' && pre2 >= '0' && pre2 <= '9') ||
						(cur >= 'A' && cur <= 'Z' && pre1 >= 'A' && pre1 <= 'Z' && pre2 >= 'A' && pre2 <= 'Z' && cur - pre1 != 0) ||
						(cur >= 'a' && cur <= 'z' && pre1 >= 'a' && pre1 <= 'z' && pre2 >= 'a' && pre2 <= 'z' && cur - pre1 != 0)) {
					System.out.println("no");
					return;
				} 
			} 
			
			pre2 = pre1;
			pre1 = cur;
			
		}
		
		if (containBig && containSmall && containNum && containSpcial) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		
	}
}
