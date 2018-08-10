package algotithem.basicclass.class_08;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的子序列
 * 	打印字符串的所有子序列，包括空字符串。
 * 
 * 递归
 * @author Yanjie
 *
 */
public class Code_03_Print_All_Subsquences {

	/**
	 * 求解
	 * 递归：
	 * 	由1~n位置，每个位置均有包含和不包含两种选项。
	 * 	base case i = n，打印。
	 * 
	 * @author Yanjie
	 *
	 * @param prefix 已处理得到的子序列前缀
	 * @param originalStr 原始字符串
	 * @param index 当前下标
	 */
	public static void printAllSub(String prefix, char[] originalStr, int index) {
		
		// base case: i == length
		if (index == originalStr.length) {
			System.out.println(prefix);
		} else {
			
			// i++
			printAllSub(prefix + originalStr[index], originalStr, index + 1); 
			printAllSub(prefix + "_ ", originalStr, index + 1); 
		}
	}
	
	public static void main(String[] args) {
		String test = "abc";
		printAllSubsquence(test);
		printAllSub("", test.toCharArray(), 0);
	}
	
	
	public static void printAllSubsquence(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0);
	}

	public static void process(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
			return;
		}
		process(chs, i + 1);
		char tmp = chs[i];
		chs[i] = 0;
		process(chs, i + 1);
		chs[i] = tmp;
	}
	
	public static void function(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0, new ArrayList<Character>());
	}
	
	public static void process(char[] chs, int i, List<Character> res) {
		if(i == chs.length) {
			printList(res);
		}
		List<Character> resKeep = copyList(res);
		resKeep.add(chs[i]);
		process(chs, i+1, resKeep);
		List<Character> resNoInclude = copyList(res);
		process(chs, i+1, resNoInclude);
	}
	
	public static void printList(List<Character> res) {
		// ...;
	}
	
	public static List<Character> copyList(List<Character> list){
		return null;
	}

}
