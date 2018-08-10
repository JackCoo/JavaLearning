package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
// FIXME
/**
 * !AC
 * 
 * 最大值之和
 * 
 * 美丽的牛家庄受到了外星人的侵略, 勇敢的妞妞要上战场抵御侵略。
 * 在妞妞上战场前, 村长牛牛给了妞妞N件装备, 妞妞需要选择其中的K件,装备在身上提升自己的战斗力。每件装备有5种属性增幅值,对于第i件装备它的属性增幅值为(ri1, ri2, ri3, ri4, ri5), 分别代表该装备对不同的属性值增幅。
 * 当妞妞装备多件装备的时候,由于装备之前会互相影响, 对于每种属性值的增幅并不是所有装备该属性值之和, 而是该种属性值下所有装备中最大的属性值。而妞妞最终增加的战斗力为这5种属性值增幅之和。
 * 妞妞一定要保卫牛家庄, 所以她希望她能提升尽可能多的战斗力, 请你帮帮她计算她最多能增加多少战斗力。
 * 
 * 输入包括N+1行,
 * 第一行包括两个正整数N和K(1 <= N <= 10000, 1 <= K <= N), 分别表示一共有的装备数量和妞妞需要选择的装备数量。
 * 接下来的N行,每行5个整数ri1, ri2, ri3, ri4, ri5 (0 <= ri1, ri2, ri3, ri4, ri5 <= 10000)表示第i件装备的5种属性值增幅。
 * 
 * 输出一个整数,表示妞妞最多能增加的战斗力。
 * 
 * 思路：
 * 	1.排序，选择和值最大的，相等时选择最大属性值最大的。-70% case, 反例，和值很小但某一属性值很大的装备。
 * 	2.k > 5, 选择每个维度最大值的装备组合；小于5时，使用1策略。-90% case
 * 	3.DFS +　2 ?
 * 
 * https://www.nowcoder.com/question/next?pid=9439037&qid=140938&tid=16507747
 * @author Yanjie
 *
 */
public class MaxProperty {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
		in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\MaxProperty.txt"));
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		/**
		 * 不建立建立实体类，费时。
		 */
//		Equipment[] equipmentArray = new Equipment[n];
//		
//		for (int x = 0; x < n; x++) {
//			equipmentArray[x] = new Equipment(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
//		}
//		System.out.println(process1(equipmentArray, k));
		
		int[][] equipmentArray = new int[n][5]; 
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < 5; y++) {
				equipmentArray[x][y] = in.nextInt();
			}
		}
		System.out.println(process2(equipmentArray, k));
	}
	
	/**
	 * k > 5: 选择每个维度最大值
	 * k <= 5：排序，和值最大、最大值最大
	 * 
	 * 90 % case
	 * 
	 * @author Yanjie
	 *
	 * @param equipment
	 * @param k
	 * @return
	 */
	public static int process2(int[][] equipment, int k) {
		
		if (k < 5) {
			
			/**
			 * Arrays可以对多维数组排序
			 */
			Arrays.sort(equipment, (int[] o1, int[] o2) -> {
				int sum1 = Arrays.stream(o1).sum();
				int sum2 = Arrays.stream(o2).sum();
				if (sum1 > sum2) {
					return -1;
				} else if (sum1 < sum2){
					return 1;
				} else {
					Arrays.sort(o1);
					Arrays.sort(o2);
					for (int i = 4; i >= 0; i++) {
						if (o1[i] > o2[i]) {
							return -1;
						} else if (o1[i] < o2[i]) {
							return 1;
						}
					}
					return 0;
				}});
			
			int propSum = 0;
			for (int i = 0; i <= 4; i++) {
				int maxProp = 0;
				for (int j = 0; j < k; j++) {
					maxProp = equipment[j][i] >= maxProp ? equipment[j][i] : maxProp;
				}
				propSum += maxProp;
			}
			return propSum;
			
		} else {
			// k >= 5时，选择每个维度最大的
			int[] maxProp = new int[5];
			for (int i = 0; i < equipment.length; i++) {
				for (int j = 0; j < 5; j++) {
					maxProp[j] = equipment[i][j] > maxProp[j] ? equipment[i][j] : maxProp[j];
				}
			}
			return Arrays.stream(maxProp).sum();
		}

		
		
		
	}
	
	
	/**
	 * 70% case
	 * 
	 * 排序，和值最大，相同依次比较最大值、次大值。
	 * 
	 * @author Yanjie
	 *
	 * @param equipmentArray
	 * @param k
	 * @return
	 */
	@Deprecated
	private static int process1(Equipment[] equipmentArray, int k) {
		
		Arrays.sort(equipmentArray, (Equipment e1, Equipment e2) -> {
			if (e1.sum() > e2.sum()) {
				return -1;
			} else if (e1.sum() < e2.sum()){
				return 1;
			} else {
				for (int i = 0; i <= 4; i++) {
					if (e1.max(i) > e2.max(i)) {
						return -1;
					} else if (e1.max(i) < e2.max(i)){
						return 1;
					}
				}
			}
			
			return 0;});
		
		int[] maxPro = new int[5];
		int propSum = 0;
		for (int i = 0; i <= 4; i++) {
			int maxProp = 0;
			for (int j = 0; j < k; j++) {
				maxProp = equipmentArray[j].propArray[i] >= maxProp ? equipmentArray[j].propArray[i] : maxProp;
			}
			propSum += maxProp;
		}
		return propSum;
	}
	
}

class Equipment {
	public int[] sortPropArray;
	public int[] propArray;
	public Equipment(int prop1, int prop2, int prop3, int prop4, int prop5) {
		super();
		this.propArray = new int[] {prop1, prop2, prop3, prop4, prop5};
		this.sortPropArray = new int[] {prop1, prop2, prop3, prop4, prop5};
		Arrays.sort(sortPropArray);
	}
	
	public int sum() {
		int sum = 0;
		for (int i : propArray) {
			sum += i;
		}
		return sum;
	}

	public int max (int index) {
		return sortPropArray[4 - index];
	}

	@Override
	public String toString() {
		return "Equipment [propArray=" + Arrays.toString(propArray) + "]";
	}	
	
}
