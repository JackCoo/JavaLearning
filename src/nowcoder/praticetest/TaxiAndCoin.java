package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 打车
 * 
 * 妞妞参加完Google Girl Hackathon之后,打车回到了牛家庄。
 * 妞妞需要支付给出租车司机车费s元。妞妞身上一共有n个硬币，第i个硬币价值为p[i]元。
 * 妞妞想选择尽量多的硬币，使其总价值足以支付s元车费(即大于等于s)。
 * 但是如果从妞妞支付的这些硬币中移除一个或者多个硬币，剩下的硬币总价值还是足以支付车费的话，出租车司机是不会接受的。例如: 妞妞使用价值为2，5，7的硬币去支付s=11的车费,出租车司机是不会接受的，因为价值为2这个硬币是可以移除的。
 * 妞妞希望能选取最大数量的硬币，使其总价值足以支付车费并且出租车司机能接受。
 * 妞妞希望你能帮她计算最多可以支付多少个硬币。
 * 
 * 输入包括两行, 第一行包括两个正整数n和s(1 <= n <= 10, 1 <= s <= 1000), 表示妞妞的硬币个数和需要支付的车费。
 * 第二行包括n个正整数p[i] (1 <= p[i] <= 100)，表示第i个硬币的价值。
 * 保证妞妞的n个硬币价值总和是大于等于s。
 * 
 * 输出一个整数, 表示妞妞最多可以支付的硬币个数。
 * 
 * 思路：贪心算法
 * 	1.从小到大累加至cost——保证硬币最多
 * 	2.从大到小剔除元素（剔除后sum仍大于cost）——保证剔除的元素最少
 * 
 * https://www.nowcoder.com/question/next?pid=9439037&qid=23650&tid=16502539
 * @author Yanjie
 *
 */
public class TaxiAndCoin {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\TexiAndCoin.txt"));
		
		int coinNum = in.nextInt();
		int cost = in.nextInt();
		int[] coinArray = new int[coinNum];
		for (int x = 0; x < coinNum; x++) {
			coinArray[x] = in.nextInt();
		}
		
		System.out.println(process(coinArray, cost));
	}
	
	private static int process(int[] coinArray, int cost) {
		
		// 排序
		Arrays.sort(coinArray);
		
		// 当前硬币和
		int sum = 0;
		
		// 从小到大累加至cost时的下标i——使用的硬币最多
		int i;
		for (i = 0; i < coinArray.length; i++) {
			if (sum < cost) {
				sum += coinArray[i];
			} else {
				break;
			}
		}
		i--;
		
		// 从大到小剔除多余元素（剔除后sum仍大于cost）——剔除的硬币最少
		for (int j = i - 1; j >= 0; j--) {
			if (sum - coinArray[j] >= cost) {
				sum -= coinArray[j];
				i--;
			}
		}
		
		return i + 1;
	}
	
}
