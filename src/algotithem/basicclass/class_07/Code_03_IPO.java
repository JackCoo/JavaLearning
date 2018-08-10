package algotithem.basicclass.class_07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 谋求项目最大收益
 * 
 * 题目：
 * 	costs[]:每个项目的花费，profits[]:每个项目的利润，k:最多能做的项目数，m:启动资金
 * 求最后获得的最大钱数。
 * 
 * 策略：
 * 	当前所有可做项目中（m >= Profit）中选择利润最大的。
 * 实现：
 * 	小根堆 + 大根堆，小根堆中存放待定项目（优先级cost），大根堆中存放可做项目（优先级profit），每次依据m >= cost 弹出项目至大根堆，大根堆弹出利润最大项目，
 * 更新m，循环。
 * 
 * @author Yanjie
 *
 */
public class Code_03_IPO {
	
	/**
	 * 项目信息
	 * 
	 * @author Yanjie
	 *
	 */
	public static class Node {
		public int profit;
		public int cost;

		public Node(int p, int c) {
			this.profit = p;
			this.cost = c;
		}
	}

	@Deprecated
	public static class MinCostComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.cost - o2.cost;
		}

	}
	@Deprecated
	public static class MaxProfitComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.profit - o1.profit;
		}

	}

	/**
	 * 求解
	 * 
	 * @author Yanjie
	 *
	 * @param k 最多可做的项目数
	 * @param w 启动资金
	 * @param profits 利润数组
	 * @param costs 花费数组
	 * @return 完成时的最大钱数
	 */
	public static int findMaximizedCapital(int k, int w, int[] profits, int[] costs) {
		
		// 封装数据
		Node[] projects = new Node[profits.length];
		for (int i = 0; i < profits.length; i++) {
			projects[i] = new Node(profits[i], costs[i]);
		}
		
		// 待定项目，小根堆，依据项目cost
		PriorityQueue<Node> minCostQ = new PriorityQueue<>( (Node p1, Node p2) -> {return p1.cost - p2.cost;} );
		
		// 当前可做项目，大根堆，依据项目profit
		PriorityQueue<Node> maxProfitQ = new PriorityQueue<>( (Node p1, Node p2) -> {return p2.profit - p1.profit;} );
		
		for (int i = 0; i < projects.length; i++) {
			minCostQ.add(projects[i]);
		}
		
		// 从小根堆中弹出可做项目至大根堆，每次完成大根堆中利润最大项目。更新启动资金，循环。
		for (int i = 0; i < k; i++) {
			
			// 尝试弹出可做项目
			while (!minCostQ.isEmpty() && minCostQ.peek().cost <= w) {
				maxProfitQ.add(minCostQ.poll());
			}
			
			// 中止条件-当前无可做项目 或者 达到最大次数k
			if (maxProfitQ.isEmpty()) {
				return w;
			}
			
			// 完成当前可做项目中利润最大的项目
			w += maxProfitQ.poll().profit;
		}
		return w;
	}
	
	public static void main(String[] args) {
		int k = 5;
		int w = 100;
		int[] profits = {30, 40, 50, 10, 50, 20};
		int[] costs = {110, 120, 70, 80, 190, 150};
		System.out.println(findMaximizedCapital(k, w, profits, costs));
		
	}

}
