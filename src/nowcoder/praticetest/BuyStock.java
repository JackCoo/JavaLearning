package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 牛客网数据有问题，不足n行，使用in.hasNextInt()已终止输入读取，利用Arrays数组拷贝去除末尾空置位置。
 * 
 * 炒股票-完全背包
 * 
 * 牛牛得知了一些股票今天买入的价格和明天卖出的价格，他找犇犇老师借了一笔钱，现在他想知道他最多能赚多少钱。
 * 
 * 每个输入包含一个测试用例。
 * 输入的第一行包括两个正整数,表示股票的种数N(1<=N<=1000)和牛牛借的钱数M(1<=M<=1000)。
 * 接下来N行，每行包含两个正整数，表示这只股票每一股的买入价X(1<=X<=1000)和卖出价Y(1<=Y<=2000)。
 * 每只股票可以买入多股，但必须是整数。
 * 
 * 输出一个整数表示牛牛最多能赚的钱数。
 * 
 * 思路：
 * 	完全背包。
 * 	dp[i][j]：前i种股票买j元所能赚取的最大利润
 * 	dp[i][j]=max{dp[i-1][j-k*x] + k * profit[i]}，其中k>=0且j-kx>=0，表示当前股票最多买j元。
 * 
 * @author Yanjie
 *
 */
public class BuyStock {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		
		in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\BuyStock.txt"));
            
		int stockNum = in.nextInt();
		int money = in.nextInt();
		int[] buyPrice = new int[stockNum];
		int[] profit = new int[stockNum];
		
		// 标记真实股票种类。测试数据有问题，不足n行，使用in.hasNextInt()已终止输入读取
		int stockCount = 0;
		for (int x = 0; x < stockNum && in.hasNextInt(); x++) {
			stockCount++;
			buyPrice[x] = in.nextInt();
			profit[x] = in.nextInt();
			profit[x] -= buyPrice[x];
		}
		
		// 去除末尾空置位置
		buyPrice = Arrays.copyOf(buyPrice, stockCount);
		profit = Arrays.copyOf(profit, stockCount);
		
		System.out.println(process(buyPrice, profit, money));	
		in.close();
	}
	
	public static int process(int[] buyPrice, int[] profit, int money) {
		
		//dp
		int[][] dp = new int[buyPrice.length][money + 1];
		
		// base case
		if (profit[0] > 0) {
			for (int j = 0; j <= money; j++) {
				dp[0][j] = j / buyPrice[0] * profit[0];
			}
		}
		
		// normal
		for (int i = 1; i < buyPrice.length; i++) {
			for (int j = 1; j <= money; j++) {
				int max = Integer.MIN_VALUE;
				for (int k = 0; j - k * buyPrice[i] >= 0; k++) {
					max = Math.max(max, dp[i - 1][j - k * buyPrice[i]] + k * profit[i]);
				}
				dp[i][j] = max;
			}
		}
		return dp[buyPrice.length - 1][money];
	}
}
