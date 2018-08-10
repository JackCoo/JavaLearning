package algotithem.basicclass.class_08;

/**
 * 给定正数数组arr，选择数组任意数字是否可以累加到aim。
 * 
 * 暴力递归 -> 动态规划
 * 
 * @author Yanjie
 *
 */
public class Code_08_Money_Problem {

	/**
	 * 暴力递归，注意base case 的写法。
	 * 
	 * 递归每个数组元素，每个元素有计算在内和忽略两种情况。
	 * 
	 * 类比打印字符串子序列。
	 * 
	 * 未考虑aim=0，未选择arr任何数的情况。
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param aim
	 * @param index
	 *            当前处理的元素index
	 * @param prefix
	 *            之前的累加和
	 * @return
	 */
	public static boolean process(int[] arr, int aim, int index, int prefix) {

		// base case， 与子问题仍有重复逻辑，合并。
//		if (index == arr.length - 1) {
//			return prefix + arr[index] == aim || prefix == aim ? true : false;
//		}

		// base case
		if (index == arr.length) {
			return prefix == aim ? true : false;
		}

		// 计算子问题，计算/忽略当前元素，向前推进。
		boolean containResult = process(arr, aim, index + 1, prefix + arr[index]);
		boolean withoutResult = process(arr, aim, index + 1, prefix);

		// 得到子问题后的决策
		return containResult || withoutResult;
	}

	/**
	 * 脱离题意，仅根据递归函数来改写动态规划
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param aim
	 * @return
	 */
	public static boolean process(int[] arr, int aim) {

		/**
		 *  定义子问题解缓存容器
		 *  	可变参数：index，prefix	=> 二维数组
		 *  	取值范围：
		 *  		index：由base case => 0-arr.length，及length + 1
		 *  		prefix：prefix的取值取决于数组arr的和值范围，即0-maxPrefix，共maxPrefix + 1。可优化至0-aim。
		 */
		int row = arr.length + 1;
		int col = 1;
		for (int i : arr) {
			col += i;
		}
		boolean[][] dp = new boolean[row][col];

		// 由base case 推导base case 的解以及其他不依赖他人的子问题解
		for (int j = 0; j <= col - 1; j++) {
			if (j == aim) {
				dp[row - 1][j] = true;
			} else {
				dp[row - 1][j] = false;
			}
		}
		
		// 分析普通位置，确定计算顺序
		for (int i = row - 2; i >= 0; i--) {
			for (int j = col - 1; j >= 0; j--) {
				boolean down = dp[i + 1][j]; // 正下方
				boolean downRight = (j + arr[i]) > col - 1 ? false : dp[i + 1][j + arr[i]]; // 下方横向距离arr[i]，若越界则为false
				dp[i][j] =  down || downRight;
			}
			
		}
		
		return dp[0][0];
	}

	/**
	 * 暴力递归写法2
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param aim
	 * @return
	 */
	public static boolean money1(int[] arr, int aim) {
		return process1(arr, 0, 0, aim);
	}

	public static boolean process1(int[] arr, int i, int sum, int aim) {

		// 忽略了后续的无用遍历
		if (sum == aim) {
			return true;
		}
		// sum != aim
		if (i == arr.length) {
			return false;
		}
		return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
	}

	/**
	 * 动态规划写法2
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param aim
	 * @return
	 */
	public static boolean money2(int[] arr, int aim) {
		
		// 优化了解空间
		boolean[][] dp = new boolean[arr.length + 1][aim + 1];
		
		// base case + 仅依赖base case 即可求解的子问题
		for (int i = 0; i < dp.length; i++) {
			dp[i][aim] = true;
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = aim - 1; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + arr[i] <= aim) {
					dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] arr = { 1, 4, 8 };
		int aim = 5;
		System.out.println(money1(arr, aim));
		System.out.println(money2(arr, aim));

		System.out.println(process(arr, aim, 0, 0));
		
		System.out.println(process(arr, aim));
	}

}
