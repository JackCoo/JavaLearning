package algotithem.basicclass.class_01;
/**
 * 荷兰国旗
 *     给定一个数组，以及数字n，将小于n的数放在数组的左边，等于放中间，大于放右边。时间复杂度o(N)，空间复杂度o(1).
 *     排列中状态：
 *     		小于区域 等于区域 待定区域 大于区域
 * @author Yanjie
 *
 */
public class Code_08_NetherlandsFlag {

	/**
	 * 将数组arr left至right 范围内数字，按照荷兰国旗规则排列。
	 * 
	 * @author Yanjie
	 *
	 * @param arr 待排列数组
	 * @param left 数组需要排列的左边界
	 * @param right 数组需要排列的右边界
	 * @param compareNumber 比较数字
	 * 
	 * @return 与比较数字n相等的区域
	 */
	public static int[] partition(int[] arr, int left, int right, int compareNumber) {
		/**
		 * 小于区域边界
		 */
		int smallerAreaIndex = left - 1;
		
		/**
		 * 大于区域边界
		 */
		int biggerAreaIndex = right + 1;
		
		int currentIndex = left;
		
		/**
		 * 遍历数组，将小于数放置在小于区域，大于数放置到大于区域。当前指针与大于区域相遇时完成排列。
		 */
		while (currentIndex < biggerAreaIndex) {
			if (arr[currentIndex] < compareNumber) {
				
				/**
				 * 扩展小于区域，当前值与小于区域边界值交换，因为交换的值已判断过（在当前值左侧，等于或小于（即自身）比较数），无需再次判断，当前指针自增。
				 */
				swap(arr, ++smallerAreaIndex, currentIndex++);
			} else if (arr[currentIndex] > compareNumber) {
				
				/**
				 * 扩展大于区域，当前值与大于区域边界值交换，因为交换的值位于待定区域，未判断过大小（在当前值右侧），所以需要判断以下，当前指针不自增。
				 */
				swap(arr, --biggerAreaIndex, currentIndex);
			} else {
				
				/**
				 * 相等，等于区域位于中间，无需交换。
				 */
				currentIndex++;
			}
		}
		return new int[] { smallerAreaIndex + 1, biggerAreaIndex - 1 };
	}

	/**
	 * 交换数组指定位置的两个数
	 * 
	 * @author Yanjie
	 *
	 * @param arr 带交换的数字
	 * @param i 带交换的数字下标
	 * @param j 带交换的数字下标
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
