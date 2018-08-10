package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


/**
 * 小易正在玩一款新出的射击游戏,这个射击游戏在一个二维平面进行,小易在坐标原点(0,0),平面上有n只怪物,
 * 每个怪物有所在的坐标(x[i], y[i])。小易进行一次射击会把x轴和y轴上(包含坐标原点)的怪物一次性消灭。
 * 小易是这个游戏的VIP玩家,他拥有两项特权操作:
 * 1、让平面内的所有怪物同时向任意同一方向移动任意同一距离
 * 2、让平面内的所有怪物同时对于小易(0,0)旋转任意同一角度
 * 小易要进行一次射击。小易在进行射击前,可以使用这两项特权操作任意次。
 * 小易想知道在他射击的时候最多可以同时消灭多少只怪物,请你帮帮小易。
 * 
 * 输入包括三行。
 * 第一行中有一个正整数n(1 ≤ n ≤ 50),表示平面内的怪物数量。
 * 第二行包括n个整数x[i](-1,000,000 ≤ x[i] ≤ 1,000,000),表示每只怪物所在坐标的横坐标,以空格分割。
 * 第二行包括n个整数y[i](-1,000,000 ≤ y[i] ≤ 1,000,000),表示每只怪物所在坐标的纵坐标,以空格分割
 * 
 * 输出一个整数表示小易最多能消灭多少只怪物
 * 
 * 思路：
 * 	1.只考虑顺逆45度以及上下左右移动（统计同一xy元素个数）。40%case，忽略了可以执行任意次该步骤。
 * 	2.变换的相对位置不变，寻找一个垂直线，使得落在其上的点最多。n较小，枚举n中3个点组成的垂直线。
 * 
 * 注意：
 * 	1.Math.cos等方法传入的时弧度，弧度=角度*PI/180
 * 	2.浮点型由于精度问题不能直接使用==比较，可以指定一个精度，相减并绝对值后<=精度。
 * 	3.坐标点旋转后坐标，百度公式。https://www.cnblogs.com/ywxgod/archive/2010/08/06/1793609.html
 * 	4.map.getOrDefault，可以指定k-v不存在时的返回值。
 * 
 * @author Yanjie
 *
 */
public class Shoot {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\Shoot"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = in.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = in.nextInt();
		}
		for (int i = 0; i < n; i++) {
			y[i] = in.nextInt();
		}
		process(x, y);
		
		in.close();
	}
	
	// 考虑上下左右移动，顺逆45
	private static void process(int[] x, int[] y) {
		
		// 顺逆时针45度后，XY轴上元素统计。
		int right45Count = 0;
		int left45Count = 0;
		
		// 处于同一x、y坐标的元素统计
		Map<Integer, Integer> yCount = new HashMap<>(x.length);
		Map<Integer, Integer> xCount = new HashMap<>(x.length);
		
		for (int i = 0; i < x.length; i++) {
			// 统计顺逆45，考虑了原点元素的不重复统计。
			if (Math.abs(x[i] * Math.cos(45 * Math.PI / 180) -y[i] * Math.sin(45 * Math.PI / 180)) <= 0.000001) {
				right45Count++;
			} else if (Math.abs(x[i] * Math.sin(45 * Math.PI / 180) + y[i] * Math.sin(45 * Math.PI / 180)) <= 0.000001) {
				right45Count++;
			}
			if (Math.abs(x[i] * Math.cos(-45 * Math.PI / 180) -y[i] * Math.sin(-45 * Math.PI / 180)) <= 0.000001) {
				left45Count++;
			} else if (Math.abs(x[i] * Math.sin(-45 * Math.PI / 180) + y[i] * Math.sin(-45 * Math.PI / 180)) <= 0.000001){
				left45Count++;
			}
			
			// 统计同一xy坐标
			xCount.put(x[i], xCount.getOrDefault(x[i], 0) + 1);
			yCount.put(y[i], yCount.getOrDefault(y[i], 0) + 1);
		}
		
	
		int max = Math.max(right45Count, left45Count);
		max = Math.max(xCount.values().stream().max((Integer o1, Integer o2)->{return o1 - o2;}).get(), 
				max); // 自然排序，取max。
		max = Math.max(yCount.values().stream().max((Integer o1, Integer o2)->{return o1 - o2;}).get(), 
				max);
		
		System.out.println(max);
	}
	
	private static int process2(int[] x, int[] y) {
		
		for (int i = 0; i <)
		
	}
}
