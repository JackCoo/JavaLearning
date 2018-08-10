package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 密码翻译
 * 
 * 在情报传递过程中，为了防止情报被截获，往往需要对情报用一定的方式加密，简单的加密算法虽然不足以完全避免情报被破译，
 * 但仍然能防止情报被轻易的识别。我们给出一种最简的的加密方法，对给定的一个字符串，把其中从a-y,A-Y的字母用其后继字母替代，
 * 把z和Z用a和A替代，则可得到一个简单的加密字符串。 
 * 
 * 读取这一行字符串，每个字符串长度小于80个字符
 * 
 * 对于每组数据，输出每行字符串的加密字符串。
 * 
 * 思路：
 * 	按提意即可
 * https://www.nowcoder.com/test/question/136de4a719954361a8e9e41c8c4ad855?pid=9439037&tid=16479290
 * @author Yanjie
 *
 */
public class Encoding {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Encoding.txt"));
		
		while (in.hasNext()) {
			String line = in.nextLine();
			System.out.println(process(line));
		}
	}
	
	private static String process(String str) {
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ('a' <= charArray[i] && 'z' >= charArray[i] || 'A' <= charArray[i] && 'Z' >= charArray[i]) {
				if (charArray[i] == 'z') {
					charArray[i] = 'a';
				} else if (charArray[i] == 'Z') {
					charArray[i] = 'A';
				}
				charArray[i] += 1;
			}

		}
		return new String(charArray, 0, charArray.length);
	}
}
