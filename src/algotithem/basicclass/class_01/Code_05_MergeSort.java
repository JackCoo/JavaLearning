package algotithem.basicclass.class_01;

import java.util.Arrays;

/**
 * 
 * 归并排序
 * 自顶向下
 * 改进:
 *  1.使用全局辅助数组，避免每次merge时创建数组耗时
 *	2.在小数量（15以下）时，归并改插入、选择排序，可节约时间。
 * 效果（与Array.sort相比）
 *  原始归并：2.0
 *  结合选择：1.35
 *  结合插入：似乎略差？，理论上应该比选择好？是因为数组不是部分有序吗？
 * @author Yanjie
 *
 */
public class Code_05_MergeSort {
	
	private static int[] help;//辅助数组，放置排序结果

	/**
	 * 校验数组，然后归并排序
	 * 
	 * @param arr
	 */
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		help = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		
		if (r - l < 15) {
			insertionSort(arr, l, r);
		} else {
			int mid = l + ((r - l) >> 1);// 求解L、M中点位置 , 更快且防止溢出 
			mergeSort(arr, l, mid);// 左排序
			mergeSort(arr, mid + 1, r);// 右排序
			merge(arr, l, mid, r);// 合并，排序操作在合并的时候执行。
		}
		
	}

	/**
	 * 按照大小顺序合并
	 * 
	 * @param arr 待排序数组
	 * @param l 左侧起始位置
	 * @param m 中间位置
	 * @param r 右侧结束位置
	 */
	public static void merge(int[] arr, int l, int m, int r) {
		int i = 0;
		int p1 = l;// 左指针
		int p2 = m + 1;// 右指针
		
		//在一个数组到达边界时结束，另一数组剩余部分直接复制
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];// 根据大小将左右数组依次放置在辅助数组中，移动已放置数组指针。
		}
		
		//有且只有一个while执行
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		
		// 将排好序的help写入原数组的相应位置
		for (i = 0; i < r - l + 1; i++) {
			arr[l + i] = help[i];
		}
	}

	/**
	 * 选择排序
	 * 
	 * @param arr
	 */
	public static void selectionSort(int[] arr, int l, int r) {
//		if (r - l < 1) {
//			return;
//		}
		for (int i = l; i <= r; i++) {
			int minIndex = i;
			for (int j = i + 1; j <= r; j++) {
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;
			}
			swap(arr, i, minIndex);
		}
	}

	/**
	 * 交换元素，使之有序
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
	
	/**
	 * 插入排序
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param leftIndex
	 * @param rightIndex
	 */
	public static void insertionSort(int[] arr, int leftIndex, int rightIndex) {
//		if (rightIndex - leftIndex < 1) {
//			return;
//		}
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			
			/**
			 * 尽量将当前元素往前插入，直至有序
			 */
			for (int j = i - 1; j >= leftIndex && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
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
		long betterMethodTimeConsume = 0;
		long normalMethodTimeConsume = 0;
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			
			betterMethodTimeConsume -= System.nanoTime();
			mergeSort(arr1);
			betterMethodTimeConsume += System.nanoTime();
			
			normalMethodTimeConsume -= System.nanoTime();
			comparator(arr2);
			normalMethodTimeConsume += System.nanoTime();
			
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		System.out.println("普通方法耗时：" + normalMethodTimeConsume);
		System.out.println("优化方法耗时：" + betterMethodTimeConsume);
		System.out.println("优/普：" + (1.0 * betterMethodTimeConsume / normalMethodTimeConsume));
		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
