package algotithem.bat.class12;

/**
 * 最长公共子序列（LCS）
 * 
 * @author Yanjie
 *
 */
public class LongestCommonSubsequence {
	
	/**
	 * 动态规划
	 * 
	 * 思路：
	 * 	dp[i][j]表示A[0,i]子字符串与B[0,j]子字符串的最长公共序列
	 * 	
	 *  base case：
	 * 		dp[0][j]:第一行，B中第一次出现A第一字符处及其之后为1，其余为0.
	 * 		dp[j][0]:第一列，
	 * 	普通位置：三处位置取最值
	 * 		dp[i - 1][j]
	 * 		dp[i][j - 1]
	 * 		dp[i - 1][j - 1] + 1，当A[i] == B[j]时。
	 * @author Yanjie
	 *
	 * @param A
	 * @param n
	 * @param B
	 * @param m
	 * @return
	 */
	public static int findLCS(String A, int n, String B, int m) {
		
		int[][] dp = new int[n][m];
		
		int firstA0InB = B.indexOf(A.charAt(0));
		int firstB0InA = A.indexOf(B.charAt(0));
		for (int j = 0; j < m; j++) {
			dp[0][j] = firstA0InB >= 0 && j >= firstA0InB ? 1 : 0;// 考虑到firstA0InB=-1
		}
		for (int i = 0; i < n; i++) {
			dp[i][0] = firstB0InA >= 0 && i >= firstB0InA ? 1 : 0;
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				int a1 = dp[i - 1][j];
				int a2 = dp[i][j - 1];
				int a3 = A.charAt(i) == B.charAt(j) ? dp[i - 1][j - 1] + 1 : 0;
				dp[i][j] = Math.max(Math.max(a1, a2), a3);
			}
		}
		
		return dp[n - 1][m - 1];
	}
	

	public static void main(String[] args) {
		String str1 = "zynnqufc";
		String str2 = "ddbauhkw";
		
		System.out.println(findLCS(str1, str1.length(), str2, str2.length()));
		
	}
}
