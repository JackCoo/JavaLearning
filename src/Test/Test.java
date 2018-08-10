package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
//		Pattern p = Pattern.compile("J20|24\\.J-[1-16]\\-[A-I]\\d");
		boolean isMatch = Pattern.matches("J20|24\\.J[1-16]\\-[A-I]\\d", "J24.J4-A9");
		System.out.println(isMatch);
		
		int[] test = {5, 4, 10, 1, 6, 0};
		IntStream stream = Arrays.stream(test).sorted();
		
		System.out.println(stream.findFirst().getAsInt());
		System.out.println(stream.sum());
		
		Stack<Character> statk = new Stack<>();
	}
	
	
	private class T6{
		
	}
}

class Test2{
	
	public static class innerClass{
		private String str;
		
	}
	
}

