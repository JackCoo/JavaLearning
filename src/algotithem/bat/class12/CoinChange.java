package algotithem.bat.class12;

/**
 * 换零钱
 * 有一个数组changes，changes中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 对于一个给定值x，请设计一个高效算法，计算组成这个值的方案数。
 * 给定一个int数组changes，代表所以零钱，同时给定它的大小n，另外给定一个正整数x，请返回组成x的方案数，保证n小于等于100且x小于等于10000。
 * 
 * @author Yanjie
 *
 */
public class CoinChange {
	
	/**
	 * 暴力递归
	 * 
	 * 思路1
	 * 	遍历零钱面额数组，每种面额均可以分配 0 - (目标-当前)/当前面额 张。遍历完数组，判断cur == aim。
	 * 思路2
	 * 	遍历零钱面儿数组，每种面额均可分配0 - (目标-当前)/当前面额 张。剩下目标由后续面额分。最后 aim == 0。
	 * 
	 * @author Yanjie
	 *
	 * @param changes 零钱面额数组
	 * @param n 数组大小
	 * @param x 目标面额
	 * @return 方案数
	 */
    public static int countWays1(int[] changes, int n, int x) {
    	
    	return process1(changes, n, x, 0, 0);
    }
    public static int process1(int[] changes, int n, int aim, int index, int cur) {
    	
    	// base case
    	if (index == n) {
    		return cur == aim ? 1 : 0;
    	}
    	
    	// 累加方案数
    	int tempCount = 0;
    	int curMaxTime = aim > cur ? (aim - cur) / changes[index] : 0; // 当前面额最大张树
    	for (int i = 0; i <= curMaxTime; i++) {
    		tempCount += process1(changes, n, aim, index + 1, cur + i * changes[index]);
    	}
    	
    	return tempCount;
    }
    
    /**
     * 动态规划
     * 
     * @author Yanjie
     *
     * @param changes
     * @param n
     * @param x
     * @return
     */
    public static int countWay2(int[] changes, int n, int x) {
    	
    	/**
    	 *  创建dp数组
    	 *  	优化cur取值范围：0-aim，忽略大于aim。
    	 */
    	int[][] dp = new int[n + 1][x + 1];
    	
    	// 根据base case 求解特殊位置
    	for (int j = 0; j <= x; j++) {
    		dp[n][j] = j == x ? 1 : 0;
    	}
    	
    	// 计算普通位置的解
    	for (int i = n - 1; i >= 0; i--) {
    		for (int j = 0; j <= x; j++) {
            	int tempCount = 0;
            	int curMaxTime = x > j ? (x - j) / changes[i] : 0;
            	for (int k = 0; k <= curMaxTime; k++) {
            		tempCount += j + k * changes[i] > x ? 0 : dp[i + 1][j + k * changes[i]];
            	}
            dp[i][j] = tempCount;
    		}
    	}
    	
    	return dp[0][0];
    }
    
    public static void main(String[] args) {
    	int[] changes = {5, 10, 25, 1};
    	int n = changes.length;
    	int x = 15;
		System.out.println(countWays1(changes, n, x));
		System.out.println(countWay2(changes, n, x));
	}
    
}
