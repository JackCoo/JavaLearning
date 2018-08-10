package algotithem.basicclass.class_01;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @author Yanjie
 *
 */
public class Code_04_QuickSort {

	/**
	 * 快速排序
	 *   利用三向切分，每次排定n个相等的数
	 * 
	 * @author Yanjie
	 *
	 * @param arr 待排序的数组
	 */
	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * 快速排序递归体
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param l
	 * @param r
	 */
	public static void quickSort(int[] arr, int l, int r) {
		if (l < r) {
			
			/**
			 * 产生随机切分元素
			 */
			swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
			int[] p = partition(arr, l, r);
			
			/**
			 * 递归
			 */
			quickSort(arr, l, p[0] - 1);
			quickSort(arr, p[1] + 1, r);
		}
	}

	/**
	 * 三向切分
	 * <p>以数组最后一个数为基准，将数组按照 小于该数、等于、大于该数的顺序原地排列
	 * 
	 * @author Yanjie
	 *
	 * @param arr 待切分的数组
	 * @param left 切分左边界（包含）边界值
	 * @param right 切分右边界（包含边界值）
	 * @return 大小为2的数组，{等于区域左边界，等于区域右边界}
	 */
	public static int[] partition(int[] arr, int left, int right) {
		
		/**
		 * 小于区域边界
		 */
		int smallAreaBoundary = left - 1;
		
		/**
		 * 大于区域边界
		 */
		int bigAreaBoundary = right;
		int currentIndex = left;
		
		/**
		 * 遍历数组，将小于数放置在小于区域，大于数放置到大于区域。当前指针与大于区域相遇时完成排列。
		 */
		while (currentIndex < bigAreaBoundary) {
			if (arr[currentIndex] < arr[right]) {
				
				/**
				 * 扩展小于区域，当前值与小于区域边界值交换，因为交换的值已判断过（在当前值左侧，等于或小于（即自身）比较数），无需再次判断，当前指针自增。
				 */
				swap(arr, ++smallAreaBoundary, currentIndex++);
			} else if (arr[currentIndex] > arr[right]) {
				
				/**
				 * 扩展大于区域，当前值与大于区域边界值交换，因为交换的值原位于待定区域，未判断过大小（在当前值右侧），所以需要判断以下，当前指针不自增。
				 */
				swap(arr, --bigAreaBoundary, currentIndex);
			} else {
				
				/**
				 * 相等，等于区域位于中间，无需交换。
				 */
				currentIndex++;
			}
		}
		
		/**
		 * 将数组末尾的切分元素放置在数组中间位置
		 * ps：boundary处的值为交换而来的大数
		 */
		swap(arr, bigAreaBoundary, right);
		return new int[] { smallAreaBoundary + 1, bigAreaBoundary };
	}

	/**
	 * 交换数组指定位置的两个数
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param i
	 * @param j
	 */
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
			quickSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		quickSort(arr);
		printArray(arr);

	}

}
