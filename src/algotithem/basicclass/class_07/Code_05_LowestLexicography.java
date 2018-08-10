package algotithem.basicclass.class_07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小字典序拼接字符串
 * 给定字符串数组，求其最小字典序的拼接（排序）字符串
 * 
 * 贪心算法
 * 
 * @author Yanjie
 *
 */
public class Code_05_LowestLexicography {

	/**
	 * 比较器
	 * 
	 * 贪心策略：a + b <= b + a，则a放前面。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	/**
	 * 最小字典序拼接字符串
	 * 
	 * @author Yanjie
	 *
	 * @param strs
	 * @return
	 */
	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		
		// 依据贪心策略排序数组
		Arrays.sort(strs, new MyComparator());
		String res = "";
		
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));

	}

}
