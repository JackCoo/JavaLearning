package algotithem.basicclass.class_03;

/**
 * 顺时针旋转打印矩阵
 * 		宏观：将矩阵拆分成矩形框，顺时针打印矩形框即可。避免了直接从微观上死扣转折点。
 * 
 * @author Yanjie
 *
 */
public class Code_06_PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matrix) {
		
		/** 
		 * 左上角、右下角两点确定一个矩形框
		 */
		int upLeftRow = 0;
		int upLeftColumn = 0;
		int lowRightRow = matrix.length - 1;
		int lowRightColumn = matrix[0].length - 1;
		
		/**
		 * 按层打印矩形框，两点重合时结束。
		 */
		while (upLeftRow <= lowRightRow && upLeftColumn <= lowRightColumn) {
			
			/**
			 * 缩小矩形框 = 左上角r++，c++；右下角--。
			 */
			printEdge(matrix, upLeftRow++, upLeftColumn++, lowRightRow--, lowRightColumn--);
		}
	}

	/**
	 * 打印矩形框
	 * 
	 * @author Yanjie
	 *
	 * @param m
	 * @param upLeftRow
	 * @param upLeftColumn
	 * @param lowRightRow
	 * @param lowRightColumn
	 */
	public static void printEdge(int[][] m, int upLeftRow, int upLeftColumn, int lowRightRow, int lowRightColumn) {
		/**
		 * 矩形框只有一行或者一列
		 */
		if (upLeftRow == lowRightRow) {
			for (int i = upLeftColumn; i <= lowRightColumn; i++) {
				System.out.print(m[upLeftRow][i] + " ");
			}
		} else if (upLeftColumn == lowRightColumn) {
			for (int i = upLeftRow; i <= lowRightRow; i++) {
				System.out.print(m[i][upLeftColumn] + " ");
			}
		
		/**
		 * 行-列-行-列
		 */
		} else {
			int curC = upLeftColumn;
			int curR = upLeftRow;
			while (curC != lowRightColumn) {
				System.out.print(m[upLeftRow][curC] + " ");
				curC++;
			}
			while (curR != lowRightRow) {
				System.out.print(m[curR][lowRightColumn] + " ");
				curR++;
			}
			while (curC != upLeftColumn) {
				System.out.print(m[lowRightRow][curC] + " ");
				curC--;
			}
			while (curR != upLeftRow) {
				System.out.print(m[curR][upLeftColumn] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
