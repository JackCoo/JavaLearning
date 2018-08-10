package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 牛牛找工作-网易18Java实习
 * 
 * 为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。牛牛选工作的标准是在难度不超过自身能力值的情况下，
 * 牛牛选择报酬最高的工作。在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，
 * 牛牛依然使用自己的标准来帮助小伙伴们。牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
 * 
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
 * 接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
 * 接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
 * 保证不存在两项工作的报酬相同。
 * 
 * 对于每个小伙伴，在单独的一行输出一个正整数表示他能得到的最高报酬。一个工作可以被多个人选择。
 * 
 * 思路1：
 * 	按照工作难度对工作数组进行排序，对于每个伙伴二分搜索确定其能胜任的最大工作难度，由于工作难度和报酬没有明确的关系，
 * 	需要在小于该难度的工作中求报酬最大值。
 * 	超时，40%。
 * 	涉及Arrays.sort，Arrays.binarySearch，Arrays.stream.max
 * 	注意，存在不能胜任任何工作的情况。
 *  Arrays.stream(array, int startInclusive, int endExclusive)，start==end时，无元素被选中，在进一步调用get时抛
 *  java.util.NoSuchElementException: No value present
 * 
 * 思路2：
 * 	预处理数据，去除在最大值求取操作。
 * 	按照工作难度对工作数组排序后，遍历有序数组，更新报酬为当前难度下的最大报酬。而后，对每个伙伴只需二分搜索确定其胜任
 * 	的最大工作难度即可获取当下最大报酬。
 * 
 * @author Yanjie
 *
 */
public class FindJob {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\FindJob"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int jobNum = in.nextInt();
		int friendNum = in.nextInt();
		int[][] jdArray = new int[jobNum][2];
		for (int x = 0; x < jobNum; x++) {
			jdArray[x][0] = in.nextInt();
			jdArray[x][1] = in.nextInt();
		}
		int[] ability = new int[friendNum];
		for (int x = 0; x < friendNum; x++) {
			ability[x] = in.nextInt();
		}
		
		process1(jdArray, ability);
		process2(jdArray, ability);
		
	}
	
	public static void process2(int[][] jdArray, int[] ability) {
		// 按照工作难度升序排序
		Arrays.sort(jdArray, (int[] jd1, int[] jd2) -> {return jd1[0] - jd2[0];});
		
		// 更新每个工作的报酬为当前难度所能获取的最大报酬
		for (int i = 0; i < jdArray.length - 1; i++) {
			if (jdArray[i][1] > jdArray[i + 1][1]) {
				jdArray[i + 1][1] = jdArray[i][1];
			}
		}
		
		// 二分查找确定能胜任的最大工作难度及其最大报酬
		for (int i = 0; i < ability.length; i++) {
			int index = Arrays.binarySearch(jdArray, new int[] {ability[i], 0}, (int[] jd1, int[] jd2) ->{
				return jd1[0] - jd2[0];});
			index = index < 0 ? -(index + 1) - 1: index;
			System.out.println(index >= 0 ? jdArray[index][1] : 0);
		}
		
	}
	
	
	public static void process1(int[][] jdArray, int[] ability) {
		// 按照工作难度升序排序
		Arrays.sort(jdArray, (int[] jd1, int[] jd2) -> {return jd1[0] - jd2[0];});
		
		for (int i = 0; i < ability.length; i++) {
			
			// 在已排序的工作中查找当前能力所能胜任的最大工作难度下标（不包括）。
			int index = Arrays.binarySearch(jdArray, new int[] {ability[i], 0}, (int[] jd1, int[] jd2) -> {
				return jd1[0] - jd2[0];
			});
			index = index < 0 ? -(index + 1) : index + 1;
			
			if (index == 0) { // 不能胜任任何工作
				System.out.println(0);
			} else {// 在胜任的工作中求报酬最大值
				int maxPaid = Arrays.stream(jdArray, 0, index).max((int[] jd1, int[] jd2) ->{
					return jd1[1] - jd2[1];
				}).get()[1];
				System.out.println(maxPaid);
			}
		}
	}
	
	
}
