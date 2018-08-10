package algotithem.basicclass.class_08;

/**
 * 生母牛
 * 	母牛每年生一只牛，三年后小牛每年生一只，假设不会死，求N年后母牛数量。
 * 
 * 利用举例寻找递归公式，f(n) = f(n-1) + f(n-3)
 * 
 * 时间复杂度O(n)
 * 
 * 递归
 * 
 * @author Yanjie
 *
 */
public class Code_05_Cow {

	/**
	 * 求解
	 * 
	 * @author Yanjie
	 *
	 * @param n
	 * @return
	 */
	public static int cowNumber1(int n) {
		
		// 前四年
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		
		// f(n) = f(n-1) + f(n-3)
		return cowNumber1(n - 1) + cowNumber1(n - 3);
	}

	public static int cowNumber2(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int res = 3;
		int pre = 2;
		int prepre = 1;
		int tmp1 = 0;
		int tmp2 = 0;
		for (int i = 4; i <= n; i++) {
			tmp1 = res;
			tmp2 = pre;
			res = res + prepre;
			pre = tmp1;
			prepre = tmp2;
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 20;
		System.out.println(cowNumber1(n));
		System.out.println(cowNumber2(n));
	}

}
