package nowcoder.praticetest;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 动态规划 + 预计算
 * 
 * 牛牛把星星图看成一个平面，左上角为原点(坐标为(1, 1))。现在有n颗星星，他给每颗星星都标上坐标(xi，yi)，表示这颗星星在第x行，第y列。
 * 现在，牛牛想问你m个问题，给你两个点的坐标(a1, b1)(a2，b2)，表示一个矩形的左上角的点坐标和右下角的点坐标，请问在这个矩形内有多少颗星星（边界上的点也算是矩形内）。 
 * 
 * 输入：
 * 第一行输入一个数字n(1≤n≤100000)，表示星星的颗数。
 * 接下来的n行，每行输入两个数xi和yi(1≤xi，yi≤1000），表示星星的位置。
 * 然后输入一个数字m(1≤m≤100000), 表示牛牛询问问题的个数。
 * 接下来m行，每行输入四个数字a1，b1，a2，b2(1≤a1＜a2≤1000), (1≤b1＜b2≤1000）
 * 题目保证两颗星星不会存在于同一个位置
 * 
 * 输出：
 * 输出一共包含m行，每行表示与之对应的每个问题的答案。
 * 
 * 例子：
 * F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\countingStar_1.txt
 * 答案：
 * 2
 * 4
 * 2
 * 2
 * 
 * 
 * 思路：只遍历一次数组
 * 使用二维数组表示星图，预先计算每个坐标左上角的星和（动态规划），利用类似矩形面积加减的方式求得每个问题的答案。
 * 
 * @author Yanjie
 *
 */
public class CountingStar {

	
	public static void main(String[] args) throws Exception {
		
		// 读取解析星图
        Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\countingStar_1.txt"));
		int[][] starMap = new int[1001][1001];
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
        	int x = in.nextInt();
        	int y = in.nextInt();
        	starMap[x][y] = 1;
        }
        
        /**
         * 预计算，每个坐标（包括）左上角的星星数。
         * 	利用动态规划：
         * 		base case：因坐标从1.1开始，所以第一行第一列均为0，无需计算。
         */
		int[][] countingTemp = new int[1001][1001];
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= 1000; j++) {
				countingTemp[i][j] = countingTemp[i][j - 1] +  countingTemp[i - 1][j] - countingTemp[i - 1][j - 1] + starMap[i][j];
			}
		}
		
		// 读取问题并解答
		int m = in.nextInt();
		for (int time = 0; time < m; time++) {
			int a1 = in.nextInt();
			int b1 = in.nextInt();
			int a2 = in.nextInt();
			int b2 = in.nextInt();
			
			int starNum = countingTemp[a2][b2] - countingTemp[a2][b1 - 1] - countingTemp[a1 - 1][b2] + countingTemp[a1 - 1][b1 - 1];
			System.out.println(starNum);
		}
		in.close();
		
	}
	
}
