package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 最近天气太热了，牛牛每天都要吃雪糕。雪糕有一盒一份、一盒两份、一盒三份这三种包装，
 * 牛牛一天可以吃多盒雪糕，但是只能吃六份，吃多了就会肚子疼，吃少了就会中暑。
 * 而且贪吃的牛牛一旦打开一盒雪糕，就一定会把它吃完。请问牛牛能健康地度过这段高温期么？
 * 
 * 每个输入包含多个测试用例。
 * 输入的第一行包括一个正整数，表示数据组数T(1<=T<=100)。
 * 接下来N行，每行包含四个正整数，表示高温期持续的天数N(1<=N<=10000)，
 * 一盒一份包装的雪糕数量A(1<=A<=100000)，一盒两份包装的雪糕数量B(1<=B<=100000)，
 * 一盒三份包装的雪糕数量C(1<=A<=100000)。
 * 
 * 对于每个用例，在单独的一行中输出结果。如果牛牛可以健康地度过高温期则输出"Yes"，否则输出"No"。
 * 
 * 思路：注意是每天吃6根
 * 	贪心，共有7中搭配方案，吃的顺序为：
 * 		3*2，2*3，1*1+2*1+3*1，1*2+2*2，1*3+3*1，1*4+2*1，1*6
 * 	因为1是万能搭配，尽量较少1的消耗。
 * 	
 * 	
 * @author Yanjie
 *
 */
public class Eat {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Eat.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int testNum = in.nextInt();
		
		for (int test = 0; test < testNum; test++) {
			int days = in.nextInt();
			int one = in.nextInt();
			int two = in.nextInt();
			int three = in.nextInt();
			
			System.out.println(process(days, one, two, three));
			
		}
		
	}
	
	public static String process(int days, int one, int two, int three) {
		
		if ((one + two * 2 + three * 3) < days * 6) {
			return "No";
		}
		
		for (int i = 0; i < days; i++) {
			if (three >= 2) {
				three -= 2;
			} else if (two >= 3){
				two -= 3;
			} else if (one >= 1 && two >= 1 && three >= 1) {
				one--;
				two--;
				three--;
			} else if (one >= 2 && two >= 2) {
				one -= 2;
				two -= 2;
			} else if (one >= 3 && three >= 1) {
				one -= 3;
				three--;
			} else if (one >= 4 && two >= 1) {
				one -= 4;
				two--;
			} else if (one >= 6) {
				one -= 6;
			} else {
				return "No";
			}
		}
		return "Yes";
	}
}
