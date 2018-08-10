package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 背包的方案数
 * 
 * 妞妞参加了Nowcoder Girl女生编程挑战赛, 但是很遗憾, 她没能得到她最喜欢的黑天鹅水晶项链。
 * 于是妞妞决定自己来制作一条美丽的项链。一条美丽的项链需要满足以下条件:
 * 	1、需要使用n种特定的水晶宝珠
 * 	2、第i种水晶宝珠的数量不能少于li颗, 也不能多于ri颗
 * 	3、一条美丽的项链由m颗宝珠组成
 * 妞妞意识到满足条件的项链种数可能会很多, 所以希望你来帮助她计算一共有多少种制作美丽的项链的方案。
 * 
 * 输入包括n+1行, 第一行包括两个正整数(1 <= n <= 20, 1 <= m <= 100), 表示水晶宝珠的种数和一条美丽的项链需要的水晶宝珠的数量。
 * 接下来的n行, 每行两个整数li, ri(0 <= li <= ri <= 10), 表示第i种宝珠的数量限制区间。
 * 
 * 输出一个整数, 表示满足限定条件的方案数。保证答案在64位整数范围内。
 * 
 * 思路：	
 * 	1.混合背包的方案树?:
 * 		dp[i][j]:使用前i种珠宝凑齐j长的项链的方案数
 * 		base case：
 * 			第一行：使用0号珠宝凑齐j（0 - m）长珠宝的方案数，j在li和ri之间为1否则为0
 * 			第一列：使用前i种珠宝凑齐0长项链的方案数，li从0开始为1否则为0
 * 		状态转移方程：dp[i][j] = sum{dp[i - 1][j - k]}, li <=k <= ri，dp[i - 1][j - k]表示当前珠宝使用k个，凑齐j长项链的方案数。
 * 			越界为0.
 * 
 * 	2.每种珠宝具有选择0-n件，遍历至尾部判断是否为m
 * 
 * 由dp方程写动态规划的步骤：
 * 		1.确定状态dp[i][j]
 * 		2.画图确定base case
 * 		3.填表，归纳出状态转移方程，填完整张表验证。
 * 
 * @author Yanjie
 *
 */
public class Jewelry {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Jewelry.txt"));
		
		int n = in.nextInt();
		int m = in.nextInt();
		int[] li = new int[n];
		int[] ri = new int[n];
		for (int x = 0; x < n; x++) {
			li[x] = in.nextInt();
			ri[x] = in.nextInt();
		}
		System.out.println(process(li, ri, m));
	}
	
	private static long process(int[] li, int[] ri, int m) {
		
		// dp
		long[][] dp = new long[li.length][m + 1];
		
		// base case
		for (int j = 0; j <= m; j++) {
			if (j >= li[0] && j <= ri[0]) {
				dp[0][j] = 1;
			}
		}
		for (int i = 1; i < li.length; i++) {
			if (0 == li[i]) {
				dp[i][0] = 0;
			}
		}
		
		// normal
		for (int i = 1; i < li.length; i++) {
			for (int j = 1; j <= m; j++) {
				long sum = 0;
				for (int k = li[i]; k <= ri[i]; k++) {
					if (j - k >= 0) {
						sum += dp[i - 1][j - k];
					}
				}
				dp[i][j] = sum;
			}
		}
		
		
		return dp[li.length - 1][m];
	}
}
