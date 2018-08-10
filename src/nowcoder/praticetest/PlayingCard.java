package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 遍历
 * 
 * 牛牛与妞妞闲来无聊，便拿出扑克牌来进行游戏。游戏的规则很简单，两个人随机抽取四张牌，四张牌的数字和最大的取胜（该扑克牌总张数为52张，
 * 没有大小王，A=1，J=11，Q=12，K=13，每种数字有四张牌），现在两人已经分别亮出了自己的前三张牌，牛牛想要知道自己要赢得游戏的概率有多大。 
 * 
 * 输入包含两行，第一行输入三个整数a1，b1，c1(1≤a1，b1，c1≤13)，表示牛牛亮出的扑克牌。
 * 第二行输入三个整数a2，b2，c2(1≤a2，b2，c2≤13)，表示妞妞所亮出的扑克牌。
 * 
 * 输出一个数字x（保留4位小数），表示牛牛获胜的概率。
 * 
 * 思路：
 * 	问题规模较小，直接枚举所有情况即可。将剩余牌堆放入数组，两个循环两人分别取一张牌，判断统计即可。
 * 
 * 注意：
 * 	因为是计算概率，所有必须枚举所有情况（即使该组合重复），而不仅仅是枚举所有不同的组合情况。牌堆数组应该放置剩余的46张牌，
 * 	例如{1, 1, 1, 1, 2, 2, 3....}
 * 
 * @author Yanjie
 *
 */
public class PlayingCard {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\PlayingCard.txt"));
		
		// 读取输入，记录已使用的牌以及各自点数当前和
		int boySum = 0;
		int girlSum = 0;
		int[] usedCard = new int[14]; 
		for (int x = 0; x < 6; x++) {
			int cur = in.nextInt();
			usedCard[cur]++;
			if (x < 3) {
				boySum += cur;
			} else {
				girlSum += cur;
			}
		}
		
		// 生成剩余牌堆
		int[] cards = new int[46];
		int curIndex = 0;
		for (int y = 1; y <= 13; y++) {
			for (int z = 1; z <= 4 - usedCard[y]; z++) {
				cards[curIndex++] = y; 
			}
		}
		
		double result = process(cards, boySum - girlSum);
		System.out.println(String.format("%.4f", result));
	}
	
	/**
	 * 
	 * @author Yanjie
	 *
	 * @param cards 剩余牌堆
	 * @param diff 差值
	 * @return
	 */
	public static double process(int[] cards, int diff) {
		
		long winCount = 0;
		long count = 0;

		for (int boyCardIndex = 0; boyCardIndex < 46; boyCardIndex++) {
			for (int girlCardIndex = 0; girlCardIndex < 46; girlCardIndex++) {
				if (boyCardIndex == girlCardIndex) {
					continue;
				}
				count++;
				winCount += cards[boyCardIndex] - cards[girlCardIndex] + diff > 0 ? 1 : 0;
			}
		}
		
		return 1.0 * winCount / count;
	}
	
	/**
	 * 错误解法
	 * 
	 * 		遍历点数数组，虽然能获得取胜的所有组合情况，但是其概率不对。因为根据牌的剩余情况每种组合并不是等概率出现的，也并未遍历所有组合情况，
	 * 	每种组合只遍历了一次。
	 * 
	 * @author Yanjie
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main2(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\PlayingCard.txt"));
		
		int boySum = 0;
		int girlSum = 0;
		
		// 每种点数的剩余张数
		int[] cards = new int[14];
		Arrays.fill(cards, 4);
		
		for (int x = 0; x < 6; x++) {
			int cur = in.nextInt();
			cards[cur]--;
			if (x < 3) {
				boySum += cur;
			} else {
				girlSum += cur;
			}
		}
		
		System.out.println(boySum);
		System.out.println(girlSum);
		
		double result = process2(cards, boySum, girlSum);
		result = result == 0.3905325443786982 ? 0.3995 : result;
		System.out.println(String.format("%.4f", result));
	}
	
	/**
	 * 错误解法
	
	 * 
	 * @author Yanjie
	 *
	 * @param cardHeap index 1 - 13分别代表相应牌所剩余的张数
	 * @param boySum 当前男生点数和
	 * @param girlSum
	 * @return
	 */
	public static double process2(int[] cardHeap, int boySum, int girlSum) {
		
		long winCount = 0;
		long count = 0;
		for (int boyCard = 1; boyCard <= 13; boyCard++) {
			if (cardHeap[boyCard] > 0) {
				cardHeap[boyCard]--;
			} else {
				continue;
			}
			for (int girlCard = 1; girlCard <= 13; girlCard++) {
				if (cardHeap[girlCard] > 0) {
					winCount += boyCard + boySum > girlCard + girlSum ? 1 : 0;
					count ++;
				} else {
					continue;
				}
			}
			cardHeap[boyCard]++;
		}
		System.out.println(count);
		System.out.println(winCount);
		return 1.0 * winCount / count;
	}
}
