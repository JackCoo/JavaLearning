package algotithem.basicclass.class_08;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩阵最小路径和
 * 题目：
 * 	给定二维数组，元素均是正数，要求从左上角做到右下角，每一步只能向右或者向下，沿途经过的数字累加，求最小路径和。	
 * 
 * 由 暴力递归 改动态规划
 * 
 * 
 * @author Yanjie
 *
 */
public class Code_07_MinPath {
	
	/**
	 * 暴力递归版本4
	 * 
	 * 子问题划分：
	 * 	当前点   到右小角的最小路径 = 当前点 + min(右侧到右下角的最小路径，下方到右小角的最小路径）
	 * 
	 * 递归体内含有min逻辑
	 * 
	 * @author Yanjie
	 *
	 * @param curX
	 * @param curY
	 * @param mat
	 * @return
	 */
	public static int process4(int curX, int curY, int[][] mat) {
		
		// base case
		if (curX == mat.length - 1 && curY == mat[0].length - 1) {
			return mat[curX][curY];
		}
		
		if (curY == mat[0].length - 1) {
			return mat[curX][curY] + process4(curX + 1, curY, mat);
		} else if (curX == mat.length - 1) {
			return mat[curX][curY] + process4(curX, curY + 1, mat);
		} else {
			// 从右侧位置到右下角的最小路径
			int right = process4(curX, curY + 1, mat);
			
			// 从下方位置到右小角的最小路径
			int down = process4(curX + 1, curY, mat);
			
			// 从当前位置到右小角的最小路径
			return mat[curX][curY] + Math.min(right, down);
		}
	}
	
	/**
	 * 动态规划版本2，由底向上。
	 * 
	 * 暴力递归重复计算了子问题，动态规划记录了子问题的解。
	 * 由 暴力递归 改写 动态规划
	 * 
	 * @author Yanjie
	 *
	 * @param mat
	 * @param curX
	 * @param curY
	 * @return
	 */
	public static int process5(int[][] mat, int curX, int curY) {
		
		int row = mat.length;
		int col = mat[0].length;
		
		// 定义子问题缓存表，由可变参数确定了dp的维度为2
		int[][] dp = new int[row][col];
		
		// 由base case 求解不依赖他人的子问题的解(最右列、最下行)
		dp[row - 1][col - 1] = mat[row - 1][col - 1];
		for (int i = row - 2; i >= 0; i--) { // 列
			dp[i][col - 1] = dp[i + 1][col - 1] + mat[i][col - 1];
		}
		for (int i = col - 2; i >= 0; i--) {
			dp[row - 1][i] = dp[row - 1][i + 1] + mat[row - 1][i];
		}
		
		// 确定普遍位置计算顺序（行：从下打上，列：从右到左）
		for (int i = row - 2; i >= 0; i--) {
			for (int j = col - 2; j >= 0; j--) {
				dp[i][j] = mat[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
			}
		}
		
		return dp[0][0];
	}
	
	/**
	 * 动态规划版本
	 * 
	 * 计算从右小角到左上角的最短路径和
	 * 
	 * @author Yanjie
	 *
	 * @param m
	 * @return
	 */
	public static int minPath2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		
		int row = m.length;
		int col = m[0].length;
		
		// 保存子问题的解
		int[][] dp = new int[row][col];

		// 确定base case以及不依赖他人的子问题解
		dp[0][0] = m[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0]; 
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		
		//
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}

	
	private static List<Integer> resultList = new ArrayList<>();
	/**
	 * 暴力递归版本3
	 * 
	 * 记录所有路径的和，然后min。暴力枚举，没有划分子问题思想。
	 * 
	 * 缺点：递归体内为处理min逻辑，使用了全局变量
	 * 
	 * @author Yanjie
	 *
	 * @param curX 当前坐标
	 * @param curY
	 * @param mat
	 * @param prefix 已走路径和
	 */
	public static void process3(int curX, int curY, int[][] mat, int prefix) {
		
		int cur = prefix + mat[curX][curY];
		
		// 每次均向右、向下走。
		if (curX < mat.length - 1) {
			process3(curX + 1, curY, mat, cur);
		} 
		if (curY < mat[0].length - 1) {
			process3(curX, curY + 1, mat, cur);
		} 
		if (curX == mat.length - 1 && curY == mat[0].length - 1) {
			
			// 将所有走法结果放入list
			resultList.add(cur);
		}

	}
	
	
	/**
	 * 暴力递归版本1
	 * 
	 * 与版本4相同
	 * 
	 * @author Yanjie
	 *
	 * @param matrix
	 * @return
	 */
	public static int minPath1(int[][] matrix) {
		return process1(matrix, matrix.length - 1, matrix[0].length - 1);
	}
	public static int process1(int[][] matrix, int i, int j) {
		int res = matrix[i][j];
		if (i == 0 && j == 0) {
			return res;
		}
		if (i == 0 && j != 0) {
			return res + process1(matrix, i, j - 1);
		}
		if (i != 0 && j == 0) {
			return res + process1(matrix, i - 1, j);
		}
		return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
	}
	
	
	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 10);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
		System.out.println(minPath1(m));
		System.out.println(minPath2(m));
		

		m = generateRandomMatrix(6, 7);
		System.out.println(minPath1(m));
		System.out.println(minPath2(m));
		
		
		process3(0, 0, m, 0);
		System.out.println(resultList);
		System.out.println(process4(0, 0, m));
		
		System.out.println(process5(m, 0, 0));
		
	}
}
