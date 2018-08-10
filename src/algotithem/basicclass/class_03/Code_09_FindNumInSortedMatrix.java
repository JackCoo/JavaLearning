package algotithem.basicclass.class_03;
/**
 * 在行列有序的矩阵数组中查找指定数字N
 * 		行列有序：行从小到大，列从小到大。
 * 
 * 思路：从矩阵右上角开始，当前数比N大，指针向左移动（列有序，下方数均大，可忽略否定）；当前数比N小，指针向下移动。如此，
 * 每次可否定剩余的行、列，到达边界时即可完成对整个矩阵的判断。
 * @author Yanjie
 *
 */
public class Code_09_FindNumInSortedMatrix {

	public static boolean isContains(int[][] matrix, int K) {
		
		/**
		 * 初始位置：矩阵右上角
		 */
		int row = 0;
		int col = matrix[0].length - 1;
		
		while (row < matrix.length && col > -1) {
			if (matrix[row][col] == K) {
				return true;
			} else if (matrix[row][col] > K) {
				col--;
			} else {
				row++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
				{ 10, 12, 13, 15, 16, 17, 18 },// 1
				{ 23, 24, 25, 26, 27, 28, 29 },// 2
				{ 44, 45, 46, 47, 48, 49, 50 },// 3
				{ 65, 66, 67, 68, 69, 70, 71 },// 4
				{ 96, 97, 98, 99, 100, 111, 122 },// 5
				{ 166, 176, 186, 187, 190, 195, 200 },// 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int K = 233;
		System.out.println(isContains(matrix, K));
	}

}
