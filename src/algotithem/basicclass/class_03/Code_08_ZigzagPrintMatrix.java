package algotithem.basicclass.class_03;
/**
 * 之字形打印矩阵
 * 		宏观：设计动点AB，初始位置位于左上角。A右移，到头下移；B每次下移，到底右移。AB同时移动，可形成一条对角线。
 * 			按序打印对角线即可。避免了微观上的死扣边界、转折点，先将问题在宏观上拆分成较小问题。
 * @author Yanjie
 *
 */
public class Code_08_ZigzagPrintMatrix {

	public static void printMatrixZigzag(int[][] matrix) {
		
		/**
		 * 初始AB两点的位置为（0,0）
		 */
		int aRow = 0;
		int aColumn = 0;
		int bRow = 0;
		int bColumn = 0;
		
		int endRow = matrix.length - 1;
		int endColumn = matrix[0].length - 1;
		
		/**
		 *	对角线打印顺序，从上往下，从下往上。每次打印完后翻转。
		 */
		boolean fromUp = false;
		
		while (aRow != endRow + 1) {
			diagonalPrint(matrix, aRow, aColumn, bRow, bColumn, fromUp);
			
			/**
			 * 移动AB形成下一条对角线
			 */
			aRow = aColumn == endColumn ? aRow + 1 : aRow;
			aColumn = aColumn == endColumn ? aColumn : aColumn + 1;
			bColumn = bRow == endRow ? bColumn + 1 : bColumn;
			bRow = bRow == endRow ? bRow : bRow + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	/**
	 * 对角线打印
	 * 
	 * @author Yanjie
	 *
	 * @param martix
	 * @param aRow
	 * @param aColumn
	 * @param bRow
	 * @param bColumn
	 * @param fromUp
	 */
	public static void diagonalPrint(int[][] martix, int aRow, int aColumn, int bRow, int bColumn,
			boolean fromUp) {
		if (fromUp) {
			while (aRow != bRow + 1) {
				System.out.print(martix[aRow++][aColumn--] + " ");
			}
		} else {
			while (bRow != aRow - 1) {
				System.out.print(martix[bRow--][bColumn++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigzag(matrix);

	}

}
