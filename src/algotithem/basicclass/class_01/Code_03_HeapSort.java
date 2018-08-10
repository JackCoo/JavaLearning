package algotithem.basicclass.class_01;

import java.util.Arrays;

/**
 * 堆排序
 *    利用堆（完全二叉树）在堆顶保存当前最大元素的特性排序。
 * 
 * @author Yanjie
 *
 */
public class Code_03_HeapSort {

	/**
	 * 堆排序
	 * 
	 * @author Yanjie
	 *
	 * @param arr 待排序的数组
	 */
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		/**
		 * 建立大根堆，从左至右遍历数组，将元素加入堆，保证i左侧有序。
		 */
		for (int i = 0; i < arr.length; i++) {
			swim(arr, i);
		}
		
		/**
		 * 不断弹出堆顶元素（当前最大元素）至数组末尾，弹出元素不在属于堆结构，下沉堆末尾交换而来的元素，使堆有序。
		 */
		int size = arr.length;
		while (size > 1) {
			swap(arr, 0, --size);
			sink(arr, 0, size);
		}
	}

	/**
	 * 上浮元素，使堆有序。
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param index
	 */
	public static void swim(int[] arr, int index) {
		
		/**
		 * 不断将index元素与其当前父节点交换，直至其不大于当前父节点，或者到达堆顶。
		 * ps：使用/而不是>>,防止index为0时，右移除法结果为负，造成数组越界。
		 */
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	/**
	 * 下沉元素，使堆有序。
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
		 * 与其当前两子节点中较大者交换，直至当前节点不小于其子节点，或者，到达堆底。
		 */
		while (leftChildIndex < size) {
			int largestChildIndex = leftChildIndex + 1 < size && arr[leftChildIndex + 1] > arr[leftChildIndex] ? leftChildIndex + 1 : leftChildIndex;
			if (arr[largestChildIndex] > arr[index]) {
				swap(arr, largestChildIndex, index);
				index = largestChildIndex;
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

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
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

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {

		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			heapSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}

}
