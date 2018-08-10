package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 矩阵重叠-网易18Java实习
 * 
 * 平面内有n个矩形, 第i个矩形的左下角坐标为(x1[i], y1[i]), 右上角坐标为(x2[i], y2[i])。
 * 如果两个或者多个矩形有公共区域则认为它们是相互重叠的(不考虑边界和角落)。
 * 请你计算出平面内重叠矩形数量最多的地方,有多少个矩形相互重叠。
 * 
 * 输入包括五行。
 * 第一行包括一个整数n(2 <= n <= 50), 表示矩形的个数。
 * 第二行包括n个整数x1[i](-10^9 <= x1[i] <= 10^9),表示左下角的横坐标。
 * 第三行包括n个整数y1[i](-10^9 <= y1[i] <= 10^9),表示左下角的纵坐标。
 * 第四行包括n个整数x2[i](-10^9 <= x2[i] <= 10^9),表示右上角的横坐标。
 * 第五行包括n个整数y2[i](-10^9 <= y2[i] <= 10^9),表示右上角的纵坐标。
 * 
 * 输出一个正整数, 表示最多的地方有多少个矩形相互重叠,如果矩形都不互相重叠,输出1。
 * 
 * 思路：
 * 	计算最大重叠区域的重叠数而不是某个矩形的最大重叠数（process1求解的是后者，且情况考虑不全，40%case）
 * 	数据量n较小，可考虑暴力方法。
 * 	遍历存在矩形区域每个点被多少个矩形所包含，超时 50% case。
 * 	降低遍历点的个数，重叠后的矩形左下角坐标一定是{x1[0]~x1[50], y1[0]~y1[50]}这2500个点中产生，
 *    只要分别判断这些点在多少矩形中即可.-考虑了中字型重叠   90% case
 * 
 * https://www.nowcoder.com/test/question/done?tid=16680239&qid=168809#summary
 * https://www.nowcoder.com/questionTerminal/d17cf8815a0745f5bbe856eee123cd24
 * 
 * @author Yanjie
 *
 */
public class OverlapMat {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\OverlapMat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		int[] leftXi = new int[n];
		int[] leftYi = new int[n];
		int[] rightXi = new int[n];
		int[] rightYi = new int[n];
		
		int[] area = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
		
		for (int i = 0; i < n; i++) {
			leftXi[i] = in.nextInt();
			area[0] = leftXi[i] < area[0] ? leftXi[i] : area[0];
		}
		for (int i = 0; i < n; i++) {
			leftYi[i] = in.nextInt();
			area[1] = leftYi[i] < area[0] ? leftYi[i] : area[0];
		}
		for (int i = 0; i < n; i++) {
			rightXi[i] = in.nextInt();
			area[2] = rightXi[i] > area[2] ? rightXi[i] : area[2];
		}
		for (int i = 0; i < n; i++) {
			rightYi[i] = in.nextInt();
			area[3] = rightYi[i] > area[3] ? rightYi[i] : area[3];
		}
		
		System.out.println(process1(leftXi, leftYi, rightXi, rightYi));
		System.out.println(process2(leftXi, leftYi, rightXi, rightYi, area));
	}

	
	public static int process2(int[] leftXi, int[] leftYi, int[] rightXi, int[] rightYi, int[] area) {
		
		int max = 0;
		for (int i = 0; i < leftXi.length; i++) {
			for (int j = 0; j < leftYi.length; j++) {
				int curPosCount = 1;
				for (int k = 0; k < leftXi.length; k++) {
					if (i == k || j == k) { // 同一个矩形边
						continue;
					} else if (leftXi[i] >= leftXi[k] && leftXi[i] < rightXi[k] &&
							leftYi[j] >= leftYi[k] && leftYi[j] < rightYi[k]) {
						curPosCount++;
					}
					
				}
				max = curPosCount > max ? curPosCount : max;
			}

		}
		
		return max;
		
	}
	
	
	public static int process1(int[] leftXi, int[] leftYi, int[] rightXi, int[] rightYi) {
		
		// 计数，记录每个矩形的重叠个数
		int[] overlapCount = new int[leftXi.length];
		
		for (int i = 1; i < leftXi.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				
				// i右下角点在j中
				if (rightXi[i] > leftXi[j] && leftYi[i] < rightYi[j] &&
						rightXi[i] <= rightXi[j] && leftYi[i] >= leftYi[j]){
					overlapCount[i]++;
					overlapCount[j]++;
				} else if (leftXi[i] < rightXi[j] && leftYi[i] < rightYi[j] && 
						leftXi[i] >= leftXi[j] && leftYi[i] >= leftYi[j]){ // 左下角
					overlapCount[i]++;
					overlapCount[j]++;
				} else if (rightXi[i] > leftXi[j] && rightYi[i] > leftYi[j] &&
						rightXi[i] <= rightXi[j] && rightYi[i] <= rightYi[j]) { // 右上角
					overlapCount[i]++;
					overlapCount[j]++;
				} else if (leftXi[i] < rightXi[j] && rightYi[i] > leftYi[j] &&
						leftXi[i] >= leftXi[j] && rightYi[i] <= rightYi[j]) { // 左上角
					overlapCount[i]++;
					overlapCount[j]++;
				}
			}
		}
		
		return Arrays.stream(overlapCount).max().getAsInt() + 1;
	}
	
}
