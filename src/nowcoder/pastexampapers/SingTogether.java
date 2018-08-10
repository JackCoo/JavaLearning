package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 小Q和牛博士合唱一首歌曲,这首歌曲由n个音调组成,每个音调由一个正整数表示。
 * 对于每个音调要么由小Q演唱要么由牛博士演唱,对于一系列音调演唱的难度等于所有相邻音调变化幅度之和, 
 * 例如一个音调序列是8, 8, 13, 12, 那么它的难度等于|8 - 8| + |13 - 8| + |12 - 13| = 6(其中||表示绝对值)。
 * 现在要对把这n个音调分配给小Q或牛博士,让他们演唱的难度之和最小,请你算算最小的难度和是多少。
 * 如样例所示: 小Q选择演唱{5, 6}难度为1, 牛博士选择演唱{1, 2, 1}难度为2,难度之和为3,
 * 这一个是最小难度和的方案
 * 
 * 输入包括两行,第一行一个正整数n(1 ≤ n ≤ 2000) 第二行n个整数v[i](1 ≤ v[i] ≤ 10^6), 表示每个音调。
 * 
 * 输出一个整数,表示小Q和牛博士演唱最小的难度和是多少。
 * 
 * 思路：
 * 	递归或者动态规划，降低可变参的数量以及取值范围。
 * 
 * 注意：
 * 	动态规划，可变参数的取值范围无法与dp一一对应时(有负数)，采用映射的方式解决，即for循环以ij表示，
 *  根据dp数组确定取值范围。由于递归或者dp中，描述的时相对顺序，所有直接用ij替换可变参数即可。
 *  但是，当涉及到从不变参数取值时，需要将ij映射回去。
 * @author Yanjie
 *
 */
public class SingTogether {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\SingTogether"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		
		int[] v = new int[n];
		
		for (int i = 0; i < n; i++) {
			v[i] = in.nextInt();
		}
		
		System.out.println(process1(v, 0, 0, 0));
		System.out.println(process2(v, -1, -1));
		System.out.println(dpPeocess(v));
		in.close();
	}
	
	/**
	 * 暴力递归
	 * 三个可变参数
	 * @author Yanjie
	 *
	 * @param v
	 * @param cur
	 * @param proPre
	 * @param stuPre
	 * @return
	 */
	private static int process1(int[] v, int cur, int proPre, int stuPre) {
		
		//base case
		if (cur == v.length) {
			return 0;
		}
		
		int proSing = (proPre == 0 ? 0 : Math.abs(v[cur] - proPre)) + process1(v, cur + 1, v[cur], stuPre); // 第一个音符没有代价
		int stuSing = (stuPre == 0 ? 0 : Math.abs(v[cur] - stuPre)) + process1(v, cur + 1, proPre, v[cur]);
		
		return Math.min(proSing, stuSing);
	}
	
	/**
	 * 暴力递归
	 * 两个可变参数，同时将取值范围较大的音调改为取值范围较小的音调索引。
	 * @author Yanjie
	 *
	 * @param v
	 * @param proPreIndex
	 * @param stuPreIndex
	 * @return
	 */
	private static int process2(int[] v, int proPreIndex, int stuPreIndex) {
		
		// base case
		if (proPreIndex == v.length - 1 || stuPreIndex == v.length - 1) {
			return 0;
		}
		
		int curIndex = Math.max(proPreIndex, stuPreIndex) + 1;
		
		int proSing = (proPreIndex == -1 ? 0 : Math.abs(v[curIndex] - v[proPreIndex])) + process2(v, curIndex, stuPreIndex);
		int stuSing = (stuPreIndex == -1 ? 0 : Math.abs(v[curIndex] - v[stuPreIndex])) + process2(v, proPreIndex, curIndex);
		
		return Math.min(proSing, stuSing);
	}
	
	/**
	 * dp
	 * 注意参数映射
	 * 
	 * @author Yanjie
	 *
	 * @param v
	 * @return
	 */
	private static int dpPeocess(int[] v) {
		
		// dp
		int[][] dp = new int[v.length + 1][v.length + 1];
		
		// base case
		 
		// normal
		for (int i = v.length - 1; i >= 0; i--) {
			for (int j = v.length - 1; j >= 0; j--) {
				int curIndex = Math.max(i, j) + 1;
				int proSing = (i == 0 ? 0 : Math.abs(v[curIndex - 1] - v[i - 1])) + dp[curIndex][j];
				int stuSing = (j == 0 ? 0 : Math.abs(v[curIndex - 1] - v[j - 1])) + dp[i][curIndex];
				
				dp[i][j] = Math.min(proSing, stuSing);
			}
		}
		
		return dp[0][0];
	}
}



