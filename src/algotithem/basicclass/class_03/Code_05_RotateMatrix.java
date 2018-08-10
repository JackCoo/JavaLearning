package algotithem.basicclass.class_03;

/**
 * 顺时针旋转矩阵
 * 抠边界
 * 
 * @author Yanjie
 *
 */
public class Code_05_RotateMatrix {

	public static void rotate(int[][] matrix) {
		
		// 左上角坐标
		int tR = 0;
		int tC = 0;
		
		//右下角坐标
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		
		// 按圈旋转
		while (tR < dR) {
			rotateEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	/**
	 * 顺时针旋转矩形框，由左上角及右下角坐标确定一矩形框。
	 * ps：因角点可属于行或者列，首先划分行列使得角点只属于行或者列。归纳总结时，使用带参表示坐标，而不是具体数字。
	 * 
	 * @author Yanjie
	 *
	 * @param m
	 * @param tR 
	 * @param tC
	 * @param dR
	 * @param dC
	 */
	public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
		int times = dC - tC; 
		int tmp = 0;
		for (int i   = 0; i != times; i++) {
			
			tmp = m[tR][tC + i];
			m[tR][tC + i] = m[dR - i][tC];
			m[dR - i][tC] = m[dR][dC - i];
			m[dR][dC - i] = m[tR + i][dC];
			m[tR + i][dC] = tmp;
		}  
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printMatrix(matrix);
		rotate(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}

}
