package algotithem.bat.class12;

/**
 * 台阶问题
 * 有n级台阶，一个人每次上一级或者两级，问有多少种走完n级台阶的方法。
 * 为了防止溢出，请将结果Mod 1000000007。给定一个正整数int n，请返回一个数，代表上楼的方式数。保证n小于等于100000。
 * 
 * 1.含有特殊情况的指定赋值。
 * 2.溢出问题：对中间结果 取模。(A+B)%C = (A%C + B%C)%C;使用long溢出，使用double不溢出但是精度损失导致误差。
 * 
 * @author Yanjie
 *
 */
public class Step {

	/**
	 * 暴力递归
	 * 思路：
	 * 	f(i) = f(i + 1) +f(i + 2)，从第i层上到n层的走法 等于 从 i+1、i+2层上到n层走法之和。
	 *  f(n) = f(n-1) + f(n-2) n>3，从第一层上到n层的走法等于....。斐波那契数列。
	 * @author Yanjie
	 *
	 * @param n
	 * @return
	 */
    public static int countWays1(int n) {
    	
    	// 处理特殊情况
    	if (n == 1) {
    		return 0;
    	} else if (n == 2) {
    		return 1;
    	}
    	
    	return process1(n, 1) % 1000000007;
    }
    public static int process1(int n, int cur) {
    	
    	if (cur > n) {
    		return 0;
    	} else if (cur == n) {
    		return 1;
    	}
    	
    	return process1(n, cur + 1) + process1(n, cur + 2);
    }
    
    /**
     * 动态规划
     * 
     * @author Yanjie
     *
     * @param n
     * @return
     */
    public static int countWays2(int n) {
    	int mod = 1000000007;
    	
    	// 特殊情况的题目给定取值
    	if (n == 1) {
    		return 0;
    	} else if (n == 2) {
    		return 1;
    	}
    	
    	// dp数组
    	int[] dp = new int[n + 1];
    	
    	// base case 解
    	dp[n] = 1;
    	dp[n - 1] = dp[n]; // 处理唯一越界的解
    	
    	// 普通位置
    	for (int i = n - 2; i >= 0; i--) {
    		dp[i] = dp[i + 1] + dp[i + 2];
    		dp[i] = dp[i] % mod; // (A+B)%C = (A%C + B%C)%C
    	}
    	
    	// 台阶从1开始，故返回dp[1]
    	System.out.println(dp[1]);
    	return dp[1];
    	
    }
    public static void main(String[] args) {
		int n = 96;
//		System.out.println(countWays1(n));
		System.out.println(countWays2(n));
	}
}
