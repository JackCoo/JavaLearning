package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串碎片-网易18秋招
 * 
 * 一个由小写字母组成的字符串可以看成一些同一字母的最大碎片组成的。
 * 例如,"aaabbaaac"是由下面碎片组成的:'aaa','bb','c'。牛牛现在给定一个字符串,
 * 请你帮助计算这个字符串的所有碎片的平均长度是多少。
 * 
 * 输入包括一个字符串s,字符串s的长度length(1 ≤ length ≤ 50),s只含小写字母('a'-'z')
 * 
 * 输出一个整数,表示所有碎片的平均长度,四舍五入保留两位小数。
 * 
 * 思路：
 * 	注意审题。
 * 	按题意，每片碎片大小一致，即只需统计有字符串可以分为几段即可。
 * 	保留2位小数，使用String.format("%2f", d)
 * 
 * @author Yanjie
 *
 */
public class StringPiece {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\StringPiece"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str = in.nextLine();
		char[] strArray = str.toCharArray();
		
		int pieceNum = 0;
		char pre = '0';
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i] != pre) {
				pieceNum++;
				pre = strArray[i];
			}
		}
		
		System.out.println(String.format("%.2f",(double)strArray.length / pieceNum));

		in.close();
	}
	
}
