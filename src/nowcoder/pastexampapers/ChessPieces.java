package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 堆棋子-网易18校招
 * 
 * 小易将n个棋子摆放在一张无限大的棋盘上。第i个棋子放在第x[i]行y[i]列。同一个格子允许放置多个棋子。
 * 每一次操作小易可以把一个棋子拿起并将其移动到原格子的上、下、左、右的任意一个格子中。
 * 小易想知道要让棋盘上出现有一个格子中至少有i(1 ≤ i ≤ n)个棋子所需要的最少操作次数.
 * 
 * 输入包括三行,第一行一个整数n(1 ≤ n ≤ 50),表示棋子的个数
 * 第二行为n个棋子的横坐标x[i](1 ≤ x[i] ≤ 10^9)
 * 第三行为n个棋子的纵坐标y[i](1 ≤ y[i] ≤ 10^9)
 * 
 * 输出n个整数,第i个表示棋盘上有一个格子至少有i个棋子所需要的操作数,以空格分割。行末无空格
 * 如样例所示:
 * 对于1个棋子: 不需要操作
 * 对于2个棋子: 将前两个棋子放在(1, 1)中
 * 对于3个棋子: 将前三个棋子放在(2, 1)中
 * 对于4个棋子: 将所有棋子都放在(3, 1)中
 * 
 * 思路1：(50%)-最终棋子并不一定在已有棋子的位置上，需要扩大中心坐标的取值范围。
 * 	n=50，可以使用复杂度较高的算法。
 * 	遍历每个点，以其为中心，记录其它点到其的距离，并排序，将距离近的点放前面。O(n^2 + nlogn)
 * 	然后累加距离数组，表示前i颗棋子到该点的最小距离和。O(n^2)
 * 	最后比较输出。O(n^2)
 * 
 * 思路2：
 * 	扩大1中中心坐标，原：n个点坐标，现：n个点横纵坐标组合，n^2个点。
 * 	ps:遍历全图复杂度太高，尝试在较小的n上扩大范围。
 * 	
 * @author Yanjie
 *
 */
public class ChessPieces {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\ChessPieces"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		
		int[] xAxis = new int[n];
		int[] yAxis = new int[n];
		for (int num = 0; num < n; num++) {
			xAxis[num] = in.nextInt();
		}
		for (int num = 0; num < n; num++) {
			yAxis[num] = in.nextInt();
		}
		
		process2(xAxis, yAxis, n);
	}
	
	public static void process1(int[] xAxis, int[] yAxis, int n) {
		
		int[][] result = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					result[i][j] = Integer.MAX_VALUE;
				} else {
					result[i][j] = Math.abs(xAxis[i] - xAxis[j]) + Math.abs(yAxis[i] - yAxis[j]);
				}
		
			}
		}
		
		for (int i = 0; i < n; i++) {
			Arrays.sort(result[i]);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n - 1; j++) {
				result[i][j] += result[i][j -1];
			}
		}
		
		System.out.print("0 ");
		for (int i = 0; i < n - 1; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				min = min > result[j][i] ? result[j][i] : min;
			}
			System.out.print(min + " ");
		}
	}
	
	public static void process2(int[] xAxis, int[] yAxis, int n) {
		
		int[][][] result = new int[n][n][n];

		// 计算每个点到中心坐标（n个点的横纵坐标组合）的距离。
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				for (int k = 0; k < n; k++) {
					result[x][y][k] = Math.abs(xAxis[x] - xAxis[k]) + Math.abs(yAxis[y] - yAxis[k]);
				}
			}
		}
		
		// 升序排序
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				Arrays.sort(result[x][y]);	
			}
		}
		
		// 前k个点到中心点的距离和
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				for (int k = 1; k < n; k++) {
					result[x][y][k] += result[x][y][k - 1];
				}
			}
		}
		
		for (int k = 0; k < n; k++) {
			int min = Integer.MAX_VALUE;
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					min = min > result[x][y][k] ? result[x][y][k] : min;
				}
			}
			System.out.print(min + " ");
		}
	}
}
