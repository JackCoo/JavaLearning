package basecomponent;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 基于PriorityQueue的大根堆、小根堆
 * PriorityQueue：默认基于元素的自然顺序排列，即小根堆，若含有多个最小值，堆顶为任一。不同步。offer、poll、remove、add
 * 时间复杂度O(logn)。
 * 以PriorityQueue实现大根堆：自定义比较器comparator。
 * 
 * @author Yanjie
 *
 */
public class HeapOnPriorityQueue {
	
	// 默认PriorityQueue为小根堆
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	
	// 自定义Comparator以实现大根堆
	PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	});
	
	// 自定义Comparator以实现大根堆，使用Lambda表达式。
	PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((Integer o1, Integer o2) ->  {return o2 - o1;});
}
