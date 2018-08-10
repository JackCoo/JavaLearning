package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// TODO 思路1 40%case，未想出可行优化方案。
/**
 * 
 * 小易喜欢的数列-网易18秋招
 * 
 * 小易非常喜欢拥有以下性质的数列:
 * 1、数列的长度为n
 * 2、数列中的每个数都在1到k之间(包括1和k)
 * 3、对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
 * 例如,当n = 4, k = 7
 * 那么{1,7,7,2},它的长度是4,所有数字也在1到7范围内,并且满足第三条性质,所以小易是喜欢这个数列的
 * 但是小易不喜欢{4,4,4,2}这个数列。小易给出n和k,希望你能帮他求出有多少个是他会喜欢的数列。
 * 
 * 输入包括两个整数n和k(1 ≤ n ≤ 10, 1 ≤ k ≤ 10^5)
 * 
 * 输出一个整数,即满足要求的数列个数,因为答案可能很大,输出对1,000,000,007取模的结果。
 * 
 * 
 * 思路1：
 * 	动态规划：
 * 		dp[i][j]数列长度为i最后一个数字为j的情况下，有多少种情况。	
 * 		dp[i][j] = sum{dp[i-1][k]}，其中k的取值满足题设的AB约束。
 * 	所求答案为dp[n][j]之和，即dp数组最后一行的和。
 * 
 * 思路2：
 * 	思路1只能过40%case，复杂度为O(n*K^2)，数列的下一个数只售上一个数约束，只要上一个数确定，下一个数的取值确定/
 * 	故，思路1中仍有重复计算。
 * 
 * https://www.nowcoder.com/questionTerminal/33531f7052f64fb5838e74fc7e869edd
 * @author Yanjie
 *
 */
public class Sequence {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\Sequence"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		System.out.println(process(n, k));
	}
	
	
	private static int process(int n, int k) {
		
		if (n * k == 0) {
			return 0;
		}
		
		// dp，数列长度为i，数列最后一个数字为j，数列每个元素的取值在[1, k]
		int[][] dp = new int[n + 1][k + 1];
		
		
		// base case
		for (int i = 1; i <= n; i++) {
			dp[i][1] = 1;
		}
		for (int j = 1; j <= k; j++) {
			dp[1][j] = 1;
		}
		
		// noraml
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				int sum = 0;
				for (int x = 1; x <= k; x++) {
					if (j >= x || x % j != 0) {
						sum += dp[i - 1][x] % 1000000007;
					}
				}
				dp[i][j] = sum ;
			}
		}
		
		return Arrays.stream(dp[n]).sum();
	}
	
	
	@Deprecated
	private static int process2(int n, int k) {
		
		// temp[a]:数列前一个位置为数字a，那么当前位置的取值种类。
		int[] temp = new int[k];
		
		
		for (int a = 1; a <= k; a++) {
			
			// B >= A
			temp[a] = k - temp[a] + 1;
			
			// A % B != 0
			for (int b = 1; b < a; b++) {
				if (a % b != 0) {
					temp[a]++;
				}
			}
		}
		
		// dp，数列长度为i，数列最后一个数字为j，数列每个元素的取值在[1, k]
		int[][] dp = new int[n + 1][k + 1];
		
		// base case
		for (int i = 1; i <= n; i++) {
			dp[i][1] = 1;
		}
		for (int j = 1; j <= k; j++) {
			dp[1][j] = 1;
		}
		
		// noraml
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				
				
			}
		}
		
		return 0;
		
		
	}
}
