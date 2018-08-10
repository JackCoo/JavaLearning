package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 排列
 * 
 * 注意：一般排列不存在重复元素
 * 
 * 妞妞得到一个(1~n)的排列p1, p2, p3,...,pn, 听村里的老人牛牛说如果让这个排列变为:
 * 对于所有的1 <= i <= n, 都满足pi ≠ i, 就可以获得Google Girl Hackathon的入场券。
 * 妞妞仅允许的操作是: 交换排列中两个相邻的元素, 并且妞妞允许做这个操作任意次。
 * 但是Google Girl Hackathon就快要开始了, 妞妞希望做最少的操作就使排列满足要求, 妞妞希望你能帮助她。
 * 
 * 输入包括两行, 第一行包括一个正整数n(2 <= n <= 10^5), 表示排列的长度和范围。 
 * 第二行包括n个正整数p1, p2, p3,...,pn, 即妞妞得到的排列, 保证是一个1~n的排列。
 * 
 * 输出一个整数, 表示妞妞需要的操作次数。
 * 
 * 思路：
 * 	一般排列不含重复元素。
 * 	若当前元素未错排，则和其相邻元素交换即可，优先与下一个元素交换，因为可能下一个元素也未错排，这样次数最少。若到达尾部，则和前一个元素交换。
 * 
 * @author Yanjie
 *
 */
public class Arrange {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Arrange.txt"));
		
		int n = in.nextInt();
		int[] array = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			array[i] = in.nextInt();
		}
		
		System.out.println(process(array));
	}
	
	private static int process(int[] array) {
		int changeCount = 0;
		for (int i = 1; i < array.length; i++) {
			if (i == array[i]) {
				changeCount++;
				if (i <= array.length - 2) { // 倒数第二个元素
					changeArrayElement(i, i + 1, array);
				} else {
					changeArrayElement(i, i - 1, array);
				}
			}
		}
		return changeCount;
	}
	
	private static void changeArrayElement(int x, int y, int[] array) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
}
