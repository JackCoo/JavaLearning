package algotithem.basicclass.class_08;

/**
 * 汉诺塔
 * 打印移动轨迹，2^n - 1步
 * 递归
 * @author Yanjie
 *
 */
public class Code_02_Hanoi {

	/**
	 * 递归
	 * 问题n 缩小规模 至 n-1
	 * 
	 * 1 ~ n-1 由from移至help
	 * n由from移至to
	 * 1 ~ n-1 由help移至to
	 * base case： n == 1
	 * 
	 * 左中右抽象成from to help，三者角色不断变换
	 * 
	 * @author Yanjie
	 *
	 * @param n
	 * @param from
	 * @param to
	 * @param help
	 */
	public static void process(int n, String from, String to, String help) {
		
		// base case
		if (n == 1) {
			System.out.println(n + ":" + from + "->" + to);
		} else {
			process(n - 1, from, help, to);
			System.out.println(n + ":" + from + "->" + to);
			process(n - 1, help, to, from);
		}
		
	}
	public static void main(String[] args) {
//		int n = 3;
//		hanoi(n);
		process(3, "left", "right", "mid");
	}
	
	
	public static void hanoi(int n) {
		if (n > 0) {
			func(n, n, "left", "mid", "right");
		}
	}
	
	public static void moveMidToRight(int N) {
		if (N == 1) {
			System.out.println("move 1 from mid to right");
		}
		moveMidToLeft(N - 1);
		System.out.println("move " + N + "from mid to right");
		moveLeftToRight(N - 1);
	}

	public static void func(int rest, int down, String from, String help, String to) {
		if (rest == 1) {
			System.out.println("move " + down + " from " + from + " to " + to);
		} else {
			func(rest - 1, down - 1, from, to, help);
			func(1, down, from, help, to);
			func(rest - 1, down - 1, help, from, to);
		}
	}

	public static void moveLeftToRight(int N) {
		if (N == 1) {
			System.out.println("move 1 from left to right");
		}
		moveLeftToMid(N - 1);
		System.out.println("move " + N + "from left to right");
		moveMidToRight(N - 1);
	}

	public static void moveRightToLeft(int N) {

	}

	public static void moveLeftToMid(int N) {
		if (N == 1) {
			System.out.println("move 1 from left to mid");
		}
		moveLeftToRight(N - 1);
		System.out.println("move " + N + "from left to mid");
		moveRightToMid(N - 1);
	}

	public static void moveMidToLeft(int N) {

	}

	public static void moveRightToMid(int N) {

	}


}
