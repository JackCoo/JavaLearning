package nowcoder.offer;

/**
 * 二维数组查找
 * 
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 * 
 * @author Yanjie
 *
 */
public class SeacchTwoDimensionArray {
	public boolean Find(int target, int [][] array) {
		for (int perpendicularIndex = 0; perpendicularIndex < array.length; perpendicularIndex++) {
			int[] childArray = array[perpendicularIndex];
			for (int horizontalIndex = 0; horizontalIndex < childArray.length; horizontalIndex++) {
				if (childArray[horizontalIndex] > target) {
					if (horizontalIndex == 0) {
						return false;
					}
					break;
				} else if (childArray[horizontalIndex] == target) {
					return true;
				}
			}
		}
		return false;
	} 
	
	public static void main(String[] args) {
		Test.compare();
	}
}

class Test {
	
	/**
	 * 随机二维int数组发生器
	 * 
	 * @author Yanjie
	 *
	 * @param maxSize 数组最大大小
	 * @param maxValue 数组最大元素值
	 * @return
	 */
	public static int[][] generateRandomArray(int maxSize, int maxValue) {
		int arrayXSize = (int) ((maxSize + 1) * Math.random());
		int arrayYSize = (int) ((maxSize + 1) * Math.random());
		int[][] arr = new int[arrayXSize][arrayYSize];
		for (int i = 0; i < arrayXSize; i++) {
			
			for (int j = 0; j < arrayYSize; j++) {
				arr[i][j] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
			}
				
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
	public static boolean comparator(int[][] arr, int target) {
		for (int[] is : arr) {
			for (int i : is) {
				if (i == target) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 打印数组
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 */
	public static void printArray(int[][] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
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
		int maxSize = 10;
		
		/**
		 * 数组元素最大值
		 */
		int maxValue = 100;
		
		boolean succeed = true;
		
		for (int i = 0; i < testTime; i++) {
			
			int[][] arr1 = generateRandomArray(maxSize, maxValue);
			int target = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
					
			if (comparator(arr1, target) != new SeacchTwoDimensionArray().Find(target, arr1)) {
				succeed = false;
				printArray(arr1);
				System.out.println(target);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
 
