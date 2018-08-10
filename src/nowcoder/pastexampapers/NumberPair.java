package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * 数对-网易18Java实习生
 * 牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。
 * 但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
 * 牛牛希望你能帮他计算一共有多少个可能的数对。
 * 
 * 输入包括两个正整数n,k(1 <= n <= 10^5, 0 <= k <= n - 1)。
 * 
 * 对于每个测试用例, 输出一个正整数表示可能的数对数量。
 * 
 * 思路：
 * 	x % y = k+，则x = y * z + k+，遍历y计算x的可能情况。
 * 	保证y > k，除数大于余数。
 * 	对于每一个特定的y，
 * 		xMin = y * factor + k
 * 		xMax = Math.min(y * (factor + 1) - 1, n)
 * 		其差值即为当前y下x的取值，累加即可。
 * 	
 * 注意：
 * 	1.参数的取值，本题xy均为正数，k>=0，故存在x等于0的情况，去除之。
 * 	2.除数大于余数，y从k+1开始。
 *  3.累加值小于答案时，考虑是否累积了负值。
 * 
 * https://www.nowcoder.com/question/next?pid=10780940&qid=168789&tid=16592030
 * @author Yanjie
 *
 */
public class NumberPair {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\NumbetPair"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		long count = 0; 
		for (int y = k + 1; y <= n; y++) {
			int factor = 0;
			while (true) {
				int xMin = y * factor + k;
				xMin = xMin == 0 ? 1 : xMin;
				if (xMin > n) {
					break;
				}
				int xMax = Math.min(y * (factor + 1) - 1, n);
				count += xMax - xMin + 1;
				factor++;
			}
		}
		System.out.println(count);
		in.close();
	}
}
