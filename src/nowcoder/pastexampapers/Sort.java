package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *小易有一个长度为N的正整数数列A = {A[1], A[2], A[3]..., A[N]}。
 *牛博士给小易出了一个难题:
 *对数列A进行重新排列,使数列A满足所有的A[i] * A[i + 1](1 ≤ i ≤ N - 1)都是4的倍数。
 *小易现在需要判断一个数列是否可以重排之后满足牛博士的要求。 
 *
 *输入的第一行为数列的个数t(1 ≤ t ≤ 10),
 *接下来每两行描述一个数列A,第一行为数列长度n(1 ≤ n ≤ 10^5)
 *第二行为n个正整数A[i](1 ≤ A[i] ≤ 10^9)
 *
 *对于每个数列输出一行表示是否可以满足牛博士要求,如果可以输出Yes,否则输出No。
 *
 * 思路：
 *  总结归纳：遍历序列，统计能被4整除的数和只能被2整除的数。
 *  n4 >= n / 2或者(double)n2 >= (n - n2) / 2
 *  
 *  2的幂次做取余的除数时，用&代替。
 *  
 * @author Yanjie
 *
 */
public class Sort {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\Sort"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int testNum = in.nextInt();
		for (int i = 0; i < testNum; i++) {
			int n = in.nextInt();
			int[] seq = new int[n];
			for (int j = 0; j < n; j++) {
				seq[j] = in.nextInt();
			}
			process(seq);
		}
	}
	private static void process(int[] seq) {
		
		int fourTimeCount = 0;
		int twoTimeCount = 0;
		for (int i : seq) {
			if ((i & 3) == 0) {
				fourTimeCount++;
			} else if ((i & 1) == 0) {
				twoTimeCount++;
			}
		}
		
		if (fourTimeCount >= seq.length / 2) {
			System.out.println("Yes");
		} else if (fourTimeCount >= (seq.length - twoTimeCount) / (double)2) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
