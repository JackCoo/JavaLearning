package nowcoder.praticetest;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 动态规划——背包
 * 
 * 今年的世界杯要开始啦，牛牛作为一个球迷，当然不会放过去开幕式现场的机会。但是牛牛一个人去又觉得太过寂寞，便想叫上了他的n个小伙伴陪他一起去莫斯科(一共n+1人)。
 * 当牛牛开始订开幕式的门票时，发现门票有m种套餐，每种套餐需要花费x元，包含y张门票，每张门票也可以单独购买，此时这张门票的价格为k元。
 * 请问牛牛要怎样选择购买门票，使得他花费的钱最少。(每种套餐可以购买次数没有限制)。
 * 
 * 第一行输入三个数字n(0≤n≤999)、m(1≤m≤1000)和k(1≤k≤100000)
 * 接下来m行，每行输入两个数字xi(1≤xi≤100000)和yi(2≤yi≤1000), 表示套餐的价格和套餐内包含的门票数量。
 * 
 * 输出牛牛至少要花费的钱的数量。
 * 
 * 思路：
 * 	1. 遍历每种套餐组合，每种套餐均可购买 0 ~ 当前缺少门票/当前套餐提供的票数 （向上取整），最后所得门票大于需求 或者 到达数组尾部 时返回。
 * 	2. 完全背包变形，装满背包（等于或者大于）的最小花费。
 * 
 * 完全背包：
 * 	较01背包每件物品只有取、不取两种状态，完全背包每种物品有无数件。若利用01背包求解完全背包，则
 * 	状态：dp[i][j]表示前i种物品，重量不超过j时的最大价值。
 * 	状态转移：dp[i][j] = max{dp[i - 1][j - k * c[i]] + k * w[i]}, 0 <= k * c[i] <=j，k表示当前商品的件数。
 * 		k的取值意义为k件当前物品的最大重量不能超过j。
 * 
 * 
 * ps：
 * 	单买门票等价于票数为1的套餐。
 * 
 * @author Yanjie
 *
 */
public class BuyTicket {

	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\BuyTicakets.txt"));
		
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		
		// 套餐数组，dp[][0]:价格，dp[][1]:票数
		int[][] pacakges = new int[m + 1][2];
		pacakges[0][0] = k;
		pacakges[0][1] = 1;
		for (int i = 1; i <= m; i++) {
			pacakges[i][0] = in.nextInt();
			pacakges[i][1] = in.nextInt();
		}
		
		System.out.println(process1(pacakges, n, 0, 0));
		System.out.println(process2(pacakges, n));
		System.out.println(process3(pacakges, n));
	}
	
	/** 
	 * 完全背包的变形
	 * 	总人数 = 背包容量，总花费 = 总价值
	 * 	不超过背包 -> 不小于背包容量
	 * 	最大价值 -> 最小花费
	 * 
	 * 状态：dp[i][j]:使用前i种套餐凑齐j张票的最小花费
	 * 转移方程：dp[i][j] = min{dp[i - 1][j - k * ticket[i]] + k * price[i] }, k表示当前商品的件数。
	 * 	k的取值范围：0 ~ min(j - k * ticket[i] <= 0)，可略微越界，不同于经典完全背包0 <= k * weights[i] <= j，
	 * 	这里k可使dp数组越界，其意义在于当前当前套餐可以凑出大于j张票。
	 * 	越界情况的花费为k * price[i]，表示当前i套餐已凑足票数，不需要之前的套餐参与。
	 * 	最后所有情况取最小值。
	 * 
	 * base case:
	 * 	取值全为0的base case似乎无用。
	 * 
	 * ps:凑足票数的保障在于
	 * 	base case保证凑足j张票，则[i - 1][j - k * ticket[i]]票数大于等于j - k * ticket[i]，所以dp[i][j]的票数也大于等于j。
	 * 	而数组越界时的足票显而易见。
	 * 
	 * @author Yanjie
	 *
	 * @param packages
	 * @param n
	 * @return
	 */
	public static int process3(int[][] packages, int n) {
		
		// 套餐类型总数
		int itmeNum = packages.length;
		
		// 总票数
		int peopleNum = n + 1;
		
		int[][] dp = new int[itmeNum][peopleNum + 1];
		
		/**
		 * base case：
		 * 	1.只用0号套餐凑齐j张票的最小花费
		 * 	2.使用前i种套餐凑齐1张票的最小花费
		 */
		for (int j = 1; j <= peopleNum; j++) {
			dp[0][j] = (int) Math.ceil((double)j / packages[0][1]) * packages[0][0];
		}
		for (int i = 1; i < itmeNum; i++) {
			dp[i][1] = Math.min(dp[i - 1][1], packages[i][0]);
		}
		
		for (int i = 1; i < itmeNum; i++) {
			for (int j = 2; j <= peopleNum; j++) {
				int minCost = Integer.MAX_VALUE;
				
				/**
				 * k的取值范围可以略微越界
				 */
				int k = 0;
				while(true) {
					if (j - k * packages[i][1] <= 0) { // 当前i套餐凑数不小于j张票
						minCost = Math.min(minCost, k * packages[i][0]);
						break;
					} else {
						minCost = Math.min(minCost, dp[i - 1][j - k * packages[i][1]] + k * packages[i][0]);
					}
					k++;
				}
				dp[i][j] = minCost;
			}
		}
		
		return dp[itmeNum - 1][peopleNum];
	}
	
	/**
	 * 暴力递归
	 * 
	 * @author Yanjie
	 *
	 * @param pacakges
	 * @param n
	 * @param curIndex
	 * @param curTickets
	 * @return
	 */
	public static int process1(int[][] pacakges, int n, int curIndex, int curTickets) {
		
		// base case
		if (curTickets >= n + 1) { // 票数已够
			return 0;
		}
		if (curIndex == pacakges.length) { // 票数未够但已无可用套餐，该方案不满足条件，废弃。
			return -1;
		}
		
		int minMoney = (n + 1) * pacakges[0][0];
		
		int curMaxNum = (int) Math.ceil( (double) ((n + 1) - curTickets) / pacakges[curIndex][1] ); // 向上取整，即可以多买票。
		
		for (int k = 0; k <= curMaxNum; k++) {
			int money = process1(pacakges, n, curIndex + 1, curTickets + k * pacakges[curIndex][1]);
			if (money == -1) {
				continue;
			} else {
				money += pacakges[curIndex][0] * k; // money = 当前套餐花费 + 后续套餐花费
			}
			minMoney = Math.min(minMoney, money);
		}
		
		return minMoney;
		
	}
	
	/**
	 * 由暴力递归改写的动态规划
	 * 
	 * @author Yanjie
	 *
	 * @param pacakges
	 * @param n
	 * @return
	 */
	public static int process2(int[][] pacakges, int n) {
		
		// dp
		int[][] dp = new int[pacakges.length + 1][n + 2];
		
		// base case
		for (int j = 0; j <= n + 1; j++) {
			dp[pacakges.length][j] = -1;
		}
		
		// 普通位置
		for (int i = pacakges.length - 1; i >= 0; i--) {
			for (int j = n; j >= 0; j--) {
				int minMoney = (n + 1) * pacakges[0][0];
				int curMaxNum = (int) Math.ceil( (double) ((n + 1) - j) / pacakges[i][1] );
				for (int k = 0; k <= curMaxNum; k++) {
					int money = 0;
					if (j + k * pacakges[i][1] <= n + 1) { // 数组越界处理，票数是否够数
						money = dp[i + 1][j + k * pacakges[i][1]];
					}
					if (money == -1) {
						continue;
					} else {
						money += pacakges[i][0] * k;
					}
					minMoney = Math.min(minMoney, money);
				}
				dp[i][j] = minMoney;
			}
		}
		return dp[0][0];
		
	}
	
	
}