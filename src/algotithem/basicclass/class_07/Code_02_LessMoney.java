package algotithem.basicclass.class_07;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 花费最少问题
 * 
 * 
 * 切分金条
 * 		金条每次可切分成两部分，花费铜板=金条长度。给定数组表示金条最终需要切分形态，求最小花费。
 * 	例：{10，30，20}，将长60的金条分成10 20 30 三段，有60->30 30 , 30->10 20 ，共60+30=120铜
 * 		60->10 50, 50-> 20 30，共110铜
 * 
 * 具体实现：哈夫曼 + （优先队列）小根堆
 * 
 * 哈夫曼：---加权外部路径长度（权重 * 深度 之和）最小
 * 	1.第一次遍历输入字符串，统计字符频率
 * 	2.构建编码树（创建字符节点优先队列，依次合并当前频率最小的节点/树，将合并结果放入队列）
 * 	3.由编码树创建编码表
 * 	4.序列化编码树
 * 	5.第二次遍历输入字符串，有编码表输出编码。
 * 
 * @author Yanjie
 *
 */
public class Code_02_LessMoney {

	/**
	 * 使用小根堆实现
	 * 
	 * 由底向上的处理，每次合并当前堆中最小的两个值，并将结果放入堆中。
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @return
	 */
	public static int lessMoneyUsingMinHeap(int[] arr) {
		
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pQ.add(arr[i]);
		}
		int sum = 0;
		int cur = 0;
		while (pQ.size() > 1) {
			cur = pQ.poll() + pQ.poll();
			sum += cur;
			pQ.add(cur);
		}
		return sum;
	}
	
	
	/**
	 * 该策略并不能得到全局最优解！
	 * 
	 * 贪心策略：每次切分金条时，切去当前所需的最大值，如此剩余下次切分的长度最小。
	 * 利用大根堆实现。
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @return
	 */
	public static int lessMoneyUsingMaxHeap(int[] arr) {
		
		// 使用Lambda自定义Comparator以利用PriorityQueue实现大根堆。
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((Integer o1, Integer o2) -> {return o2 - o1;}); 
		int moneySum = 0;
		for (int element : arr) {
			maxHeap.add(element);
			moneySum += element;
		}
		
		int remain = moneySum;
		while (maxHeap.size() > 2) {
			remain = remain - maxHeap.poll();
			moneySum += remain;
		}
		return moneySum;
	}
	

	/**
	 * 自定义比较器以借助PriorityQueue实现大根堆、小根堆。
	 * @author Yanjie
	 *
	 */
	public static class MinheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2; // < 0  o1 < o2  负数
		}

	}
	public static class MaxheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1; // <   o2 < o1
		}

	}

	public static void main(String[] args) {
		// solution
//		int[] arr = { 6, 7, 8, 9 };
		
		int[] arr = {10, 20, 30};
		System.out.println(lessMoneyUsingMaxHeap(arr));

		
		/**
		 * 大小根堆测试。
		 */
		int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

		// min heap
		PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ1.add(arrForHeap[i]);
		}
		while (!minQ1.isEmpty()) {
			System.out.print(minQ1.poll() + " ");
		}
		System.out.println();

		// min heap use Comparator
		PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ2.add(arrForHeap[i]);
		}
		while (!minQ2.isEmpty()) {
			System.out.print(minQ2.poll() + " ");
		}
		System.out.println();

		// max heap use Comparator
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
		for (int i = 0; i < arrForHeap.length; i++) {
			maxQ.add(arrForHeap[i]);
		}
		while (!maxQ.isEmpty()) {
			System.out.print(maxQ.poll() + " ");
		}

	}

}

