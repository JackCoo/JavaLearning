package algotithem.basicclass.class_01;

import java.util.Arrays;

/**
 * 基于堆的优先队列
 * 
 * @author Yanjie
 *
 */
public class PriorityQueueOnHeap<E extends Comparable<? super E>> {
	
	/**
	 * 用以存放堆元素的数组
	 */
	private E[] elementArray;
	
	/**
	 * 堆（优先队列）的大小
	 */
	private int size = 0;
	
	/**
	 * 构造一个指定容量的优先队列
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueueOnHeap(int capacity) {
		elementArray = (E[]) new Comparable[capacity < 0 ? 10 : capacity];
	}
	
	/**
	 * 向优先队列中添加元素
	 * 
	 * @author Yanjie
	 *
	 * @param element
	 */
	public void insert(E element) {
		elementArray[size] = element;
		swim(size++);
	}
	
	/**
	 * 获取并删除优先队列中的最大值
	 * 
	 * @author Yanjie
	 *
	 * @return
	 */
	public E delMax() {
		E max = elementArray[0];
		exch(0, --size);
		
		/**
		 * 防止对象游离
		 */
		elementArray[size] = null;
		
		sink(0);
		return max;
	}
	
	/**
	 * 将index处的元素上浮
	 * 
	 * @author Yanjie
	 *
	 * @param index 需要上浮节点的数组索引
	 */
	private void swim(int index) {
		
		/**
		 * 上浮，不断将index元素与其当前父节点交换，直至其不大于当前父节点，或者到达堆顶。
		 * ps：使用/而不是>>,防止index为0时，右移除法结果为负，造成数组越界。
		 */
		while(index > 0 && elementArray[index].compareTo(elementArray[(index - 1) / 2]) > 0) {
			exch(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}
	
	/**
	 * 将index处的元素下沉
	 * 
	 * @author Yanjie
	 *
	 * @param index 需要下沉节点的数组索引
	 */
	private void sink(int index) {
		
		/**
		 * 待下沉节点的最大子节点索引，初始值为其左子节点
		 */
		int largestChildIndex = (2 * index) + 1;
		
		/**
		 * 下沉，与其当前两子节点中较大者交换，直至当前节点不小于其子节点，或者，到达堆底。
		 */
		while (largestChildIndex < size) {
			largestChildIndex = largestChildIndex + 1 < size &&
					elementArray[largestChildIndex + 1].compareTo(elementArray[largestChildIndex]) > 0 ? 
					largestChildIndex + 1 : largestChildIndex;
			if (elementArray[index].compareTo(elementArray[largestChildIndex]) < 0) {
				exch(index, largestChildIndex);
				index = largestChildIndex;
				largestChildIndex  = (2 * index) + 1;
			} else {
				break;
			}
		}
	}
	
	/**
	 * 交换数组i、j处的元素
	 * 
	 * @author Yanjie
	 *
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		E temp = elementArray[i];
		elementArray[i] = elementArray[j];
		elementArray[j] = temp;
	}
	
	/**
	 * 测试
	 * 
	 * @author Yanjie
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Test.compare();
		
	}
}

class Test {
	
	/**
	 * 随机int数组发生器
	 * 
	 * @author Yanjie
	 *
	 * @param maxSize 数组最大大小
	 * @param maxValue 数组最大元素值
	 * @return
	 */
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	
	/**
	 * 对数数组产生器
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 */
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
		for (int i = 0; i < arr.length /2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = temp;
		}
	}
	
	/**
	 * 数组对比器
	 * 
	 * @author Yanjie
	 *
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 打印数组
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 */
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 校验入口
	 * 
	 * @author Yanjie
	 *
	 * @param args
	 */
	public static void compare() {
		
		/**
		 * 实验次数
		 */
		int testTime = 500000;
		
		/**
		 * 数组最大大小
		 */
		int maxSize = 100;
		
		/**
		 * 数组元素最大值
		 */
		int maxValue = 100;
		
		boolean succeed = true;
		
		for (int i = 0; i < testTime; i++) {
			
			/**
			 * 生成相同的实验数组arr1和arr2
			 */
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = new int[arr1.length];
			System.arraycopy(arr1, 0, arr2, 0, arr1.length);
			
			/**
			 * 使用arr1生成优先队列，再将将队列输出至arr1
			 */
			PriorityQueueOnHeap<Integer> pq = new PriorityQueueOnHeap<>(maxSize);
			for (int j = 0; j < arr1.length; j++) {
				pq.insert(arr1[j]);
			}
			for (int j = 0; j < arr1.length; j++) {
				arr1[j] = pq.delMax();
			}
			
			/**
			 * 使用对数器生成正确的优先队列输出结果
			 */
			comparator(arr2);
			
			/**
			 * 对比
			 */
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				System.out.println("测试结果");
				printArray(arr1);
				System.out.println("正确结果");
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
