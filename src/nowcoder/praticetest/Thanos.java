package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * 灭霸
 * 
 * 牛牛在地上捡到了一个手套，他带上手套发现眼前出现了很多个小人，当他打一下响指，
 * 这些小人的数量就会发生以下变化：如果小人原本的数量是偶数那么数量就会变成一半，
 * 如果小人原本的数量是奇数那么数量就会加一。现在牛牛想考考你，他要打多少次响指，才能让小人的数量变成1
 * 
 * 每个输入包含一个测试用例。
 * 输入的第一行包括一个正整数，表示一开始小人的数量N(1<=N<=10^100)
 * 
 * 对于每个用例，在单独的一行中输出牛牛需要打多少次响指才能让小人的数量变成1。
 * 
 * 思路：
 * 	依据题意即可。
 *  超大整型数字的运算使用BigInteger
 *  
 * https://www.nowcoder.com/question/next?pid=10714908&qid=164573&tid=16590866
 * 
 * @author Yanjie
 *
 */
public class Thanos {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Thanos.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String people = in.nextLine();
		BigInteger bigInt = new BigInteger(people);
		
		int count = 0;
		while (bigInt.compareTo(BigInteger.ONE) != 0) {
			count++;
			bigInt = bigInt.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0
					? bigInt.divide(BigInteger.valueOf(2)) : bigInt.add(BigInteger.ONE);
		}
		System.out.println(count);
	}
}
