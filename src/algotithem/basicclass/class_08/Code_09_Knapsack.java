package algotithem.basicclass.class_08;

/**
 * 01背包问题:每种物品只有一件，可以选择放或者不放
 * 
 * 01背包经典思路：动态规划3
 * 	dp[i][j]表示前i件物品，重量不超过j时的最大价值。普通位置：当前物品是否加入。 
 *  转移方程：dp[i][j] = max{dp[i - 1][j], dp[i - 1][j - c[i]] + w[i]}，若当前商品放入，则其子问题上一个状态为dp[i - 1][j - c[i]]
 * 
 * 其它
 * 	暴力递归1使用了三个可变参数，而暴力递归2删除了curValue使用了2个可变参数。
 * 	暴力递归1遍历完所有物品后形成01组合，然后根据空间是否超出返回总价值或者是0，所以需要传递curValue至递归尾部以判断。
 * 	而递归2 base case 中在空间超出时直接返回，无需curValue。每次递归返回上次价值（+当前物品价值）作为当前价值返回。
 * 	未由暴力递归2改写暴力递归3
 * 

 * 
 * @author Yanjie
 *
 */
public class Code_09_Knapsack {

	/**
	 * 暴力递归1
	 * 
	 * 每个物品均有 放入 和 忽略，以此规则递归整个物品数组组合，得到value和size，判断即可。
	 * 
	 * 不优化暴力回归，便于改写。
	 * 
	 * 有三个可变参数，可去掉curValue。
	 * 
	 * @author Yanjie
	 *
	 * @param weights
	 * @param values
	 * @param bagSapce
	 * @param index
	 * @param usedSapce
	 * @param curValue
	 * @return
	 */
	public static int process3(int[] weights, int[] values, int bagSapce, int index, int usedSapce, int curValue) {
		
		// base case：遍历完物品数组，完成一项组合。
		if (index == weights.length) { // 每种物品的选取与否组成了01序列
			return usedSapce > bagSapce ? -1 : curValue;
		}
		
		// 当前物品是否放入，然后跳至下一个物品。
		int value1 = process3(weights, values, bagSapce, index + 1, usedSapce, curValue);
		int value2 = process3(weights, values, bagSapce, index + 1, usedSapce + weights[index], curValue + values[index]);
		
		return Math.max(value1, value2);
		
	}
	
	
	/**
	 * 动态规划1
	 * 
	 * @author Yanjie
	 *
	 * @param weights
	 * @param values
	 * @param bagSapce
	 * @return
	 */
	public static int process4(int[] weights, int[] values, int bagSapce) {
		
		/**
		 * 定义dp数组
		 * 	根据可变参数确定维度
		 * 	可变参数的取值范围确定数组的大小
		 * 
		 */
		int maxValue = 0;
		for (int i : values) {
			maxValue += i;
		}
		int[][][] dp = new int[weights.length + 1][bagSapce + 1][maxValue + 1];
		
		// 计算base case 的解
		for (int j = 0; j <= bagSapce; j++) {
			for (int k = 0; k <= maxValue; k ++) {
				if (j > bagSapce) {
					dp[weights.length][j][k] = -1;
				} else {
					dp[weights.length][j][k] = k;
				}
			}
		}
		
		// 确定、计算普通位置的计算顺序及其解
		for (int i = weights.length - 1; i >= 0; i--) {
			for (int j = 0; j <= bagSapce; j++) {
				for (int k = 0; k <= maxValue; k++) {
					if (j + weights[i] > bagSapce || k + values[i] > maxValue) { // 越界为-1
						dp[i][j][k] = -1;
					} else {
						dp[i][j][k] = Math.max(dp[i + 1][j][k], dp[i + 1][j + weights[i]][k + values[i]]);
					}
				}
			}
		}
		
		return dp[0][0][0];
	}
	
	/**
	 * 暴力递归2
	 * 
	 * 每个物品均有 放入 和 忽略，以此规则递归整个物品数组组合，在使用空间大于背包空间或者遍历完所有物品时结束。
	 * 
	 * 由此改写的动态规划只是用二维dp数组。
	 * 
	 * @author Yanjie
	 *
	 * @param weights
	 * @param values
	 * @param bagSize
	 * @return
	 */
	public static int maxValue1(int[] weights, int[] values, int bagSize) {
		return process1(weights, values, 0, 0, bagSize);
	}
	public static int process1(int[] weights, int[] values, int curIndex, int usedSapce, int bagSize) {
		
		// base case * 2
		if (usedSapce > bagSize) {
			return 0;
		}
		if (curIndex == weights.length) {
			return 0;
		}
		
		int value1 = process1(weights, values, curIndex + 1, usedSapce, bagSize);
		int value2 = usedSapce + weights[curIndex] <= bagSize ? // 当前物品是否能放入
				process1(weights, values, curIndex + 1, usedSapce + weights[curIndex], bagSize) + values[curIndex] : 0;
		
		return Math.max(value1, value2);
		
	}
	
	/**
	 * 动态规划3
	 * 
	 * 思路
	 * 	dp[i][j]表示前i件物品，重量不超过j时的最大价值。
	 * 
	 * 牛客 BAT 12章
	 * 
	 * @author Yanjie
	 *
	 * @param weights
	 * @param values
	 * @param bagSize
	 * @return
	 */
	public static int maxValue3(int[] weights, int[] values, int bagSize) {
		
		int[][] dp = new int[weights.length][bagSize + 1];
		
		// 第一列，j = 0时，value = 0.————可删除
		for (int i = 0; i < weights.length; i++) {
			dp[i][0] = 0;
		}
		
		// 第一行，只能放入第一个物品时的价值
		for (int j = 1; j <= bagSize; j++) {
			dp[0][j] = weights[0] <= j ? values[0] : 0;
		}
		
		// 普通位置：当前物品是否加入。
		for (int i = 1; i < weights.length; i++) {
			for (int j = 1; j <= bagSize; j++) {
				int value1 = dp[i - 1][j];
				int value2 = j - weights[i] >= 0 ? dp[i - 1][j - weights[i]] + values[i] : 0; // 首先判断是否能够加入
				dp[i][j] = Math.max(value1, value2);
			}
		}
		
		return dp[weights.length - 1][bagSize];
		
	}

	/**
	 * 动态规划版本4，左神版本。
	 * 
	 * @author Yanjie
	 *
	 * @param c
	 * @param p
	 * @param bag
	 * @return
	 */
	public static int maxValue2(int[] c, int[] p, int bag) {
		int[][] dp = new int[c.length + 1][bag + 1];
		
		for (int i = c.length - 1; i >= 0; i--) {
			for (int j = bag; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + c[i] <= bag) {
					dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
				}
			}
		}
		return dp[0][0];
	}

	
	public static void main(String[] args) {
		int[] c = { 3, 2, 4, 7 };
		int[] p = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue1(c, p, bag));
		
		System.out.println(maxValue2(c, p, bag));
		
		System.out.println(process3(c, p, bag, 0, 0, 0));
		System.out.println(process4(c, p, bag));
		
		System.out.println(maxValue3(c, p, bag));
		
	}
	
	
	
	
	

}
