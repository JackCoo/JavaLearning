package nowcoder.offer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 无序数组最小的N个数
 * 
 * 思路：
 * 		1.使用快排排定前N个数
 * 		2.利用小根堆弹出前N个数（自身实现、PriorityQueue(默认小根堆，大根堆定义比较器)）
 * 
 * @author Yanjie
 *
 */
public class FirstNSmall {
	
	/**
	 * 利用小根堆获取乱序数组前k小数
	 * 
	 * @author Yanjie
	 *
	 * @param arr 待排序的数组
	 * @return
	 */
	public static ArrayList<Integer> getMinK(int[] arr, int k) {
		int size = arr.length;
		if (arr == null || arr.length < 2 || k > size || k < 0) {
			return new ArrayList<>(0);
		} 
		
		/**
		 * 建立小根堆，从左至右遍历数组，将元素加入堆，保证i左侧有序。
		 */
		for (int i = 0; i < arr.length; i++) {
			swim(arr, i);
		}
		
		/**
		 * 弹出堆顶元素（当前最小元素）至结果数组，下沉堆末尾交换而来的元素，使堆有序。
		 */
		ArrayList<Integer> result = new ArrayList<>(k);
		for (int i = 0; i < k; i++) {
			result.add(arr[0]);
			swap(arr, 0, --size);
			sink(arr, 0, size);
		}
		return result;
	}

	/**
	 * 上浮元素，使小根堆有序。
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param index
	 */
	public static void swim(int[] arr, int index) {
		
		/**
		 * 不断将index元素与其当前父节点交换，直至其不小于当前父节点，或者到达堆顶。
		 * ps：使用/而不是>>,防止index为0时，右移除法结果为负，造成数组越界。
		 */
		while (arr[index] < arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	/**
	 * 下沉元素，使小根堆有序。
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param index
	 * @param size
	 */
	public static void sink(int[] arr, int index, int size) {
		/**
		 * 待下沉节点的左子节点
		 */
		int leftChildIndex = index * 2 + 1;
		
		/**
		 * 循环，与其当前两子节点中较小者交换，直至当前节点不大于其子节点，或者，到达堆底。
		 */
		while (leftChildIndex < size) {
			int minChildIndex = leftChildIndex + 1 < size && arr[leftChildIndex + 1] < arr[leftChildIndex] ? leftChildIndex + 1 : leftChildIndex;
			if (arr[minChildIndex] < arr[index]) {
				swap(arr, minChildIndex, index);
				index = minChildIndex;
				leftChildIndex = index * 2 + 1;
			} else {
				break;
			}
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		System.out.println(getMinK(new int[] {4,5,1,6,2,7,3,8}, 8));
		System.out.println(QueueUsingJdk.getMinK(new int[] {4,5,1,6,2,7,3,8}, 8));
	}
}


/**
 * 使用PriorityQueue实现小根堆
 * 
 * @author Yanjie
 *
 */
class QueueUsingJdk {
	
	/**
	 * 获取无序数组前k小数
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param k
	 * @return
	 */
	public static ArrayList<Integer> getMinK(int[] arr,int k) {
		if (arr == null || arr.length == 0 || k > arr.length || k < 0) {
			return new ArrayList<>(0);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>(arr.length);
		ArrayList<Integer> result = new ArrayList<>(k);
		for (int i : arr) {
			pq.add(i);
		}
		for (int i = 0; i < k; i++) {
			result.add(pq.remove());
		}
		return result;
	}
}
	