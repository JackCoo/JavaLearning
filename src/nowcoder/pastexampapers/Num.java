package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 神奇数-京东18安卓
 * 
 * 东东在一本古籍上看到有一种神奇数,如果能够将一个数的数字分成两组,其中一组数字的和等于另一组数字的和,
 * 我们就将这个数称为神奇数。例如242就是一个神奇数,我们能够将这个数的数字分成两组,
 * 分别是{2,2}以及{4},而且这两组数的和都是4.东东现在需要统计给定区间中有多少个神奇数,
 * 即给定区间[l, r],统计这个区间中有多少个神奇数,请你来帮助他。 
 * 
 * 输入包括一行,一行中两个整数l和r(1 ≤ l, r ≤ 10^9, 0 ≤ r - l ≤ 10^6),以空格分割
 * 输出一个整数,即区间内的神奇数个数
 * 
 * 思路1：贪心 40%
 * 	处理int成数字数组，首先判断数组和是否为偶数（两组和不可能为小数），而后利用贪心去凑目标和：
 * 		判断数组中是否有aim，没有使用小于aim的最大数代替，减小aim，循环。
 * 
 * 思路2：100%
 * 	预处理数组后，利用递归搜索所有情况。数组最大也就9个元素。
 * 
 * @author Yanjie
 *
 */
public class Num {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\Num"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int l = in.nextInt();
		int r = in.nextInt();
		int count = 0;
		for (int i = l; i <= r; i++) {
			
			// int转数字数组
			char[] charArray = String.valueOf(i).toCharArray();
			int[] numArray = new int[charArray.length];
			for (int j = 0; j < charArray.length; j++) {
				numArray[j] = Character.getNumericValue(charArray[j]);
			}
			
			// 初步校验，两组和不能为小数。
			if (Arrays.stream(numArray).sum() % 2 != 0) {
				continue;
			} 
			
			int aim = Arrays.stream(numArray).sum() / 2;
			
			// 进一步校验，贪心。40% case
			//count += process1(numArray, aim);
			
			// 进一步校验，递归搜索。
			count += process2(numArray, aim, 0, 0) > 0 ? 1 : 0;
		}
		
		System.out.println(count);
		in.close();
	}
	
	// 贪心——40% case
	private static int process1(int[] numArray, int aim) {
		while (true) {
			int maxLess = -1;
			int maxLessPos = -1;
			for (int j = 0; j < numArray.length; j++) {
				if (numArray[j] == aim) {
					return 1;
				} else {
					if (numArray[j] < aim && numArray[j] > maxLess) {
						maxLess = numArray[j];
						maxLessPos = j;
					}
					
				}
			}
			if (maxLessPos == -1) {
				break;
			}
			// 未找到aim值，使用小于aim的最大值代替，减小aim。
			aim = aim - maxLess;
			numArray[maxLessPos] = -1; // 标记，此数已被消耗掉。
		}
		return 0;
	}
	
	/**
	 *  递归，暴力搜索。100% case
	 *  
	 * @author Yanjie
	 *
	 * @param numArray
	 * @param aim
	 * @param cur
	 * @param sum
	 * @return 凑aim的方案数
	 */
	private static int process2(int[] numArray, int aim, int cur, int sum) {
		if (cur == numArray.length) {
			return sum == aim ? 1 : 0;
		}
		return process2(numArray, aim, cur + 1, sum) + process2(numArray, aim, cur + 1, sum + numArray[cur]);
	}
	
}
