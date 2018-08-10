package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 独立的牛牛
 * 
 * 小牛牛为了向他的父母表现他已经长大独立了,他决定搬出去自己居住一段时间。
 * 一个人生活增加了许多花费: 牛牛每天必须吃一个水果并且需要每天支付x元的房屋租金。
 * 当前牛牛手中已经有f个水果和d元钱,牛牛也能去商店购买一些水果,商店每个水果售卖p元。
 * 牛牛为了表现他独立生活的能力,希望能独立生活的时间越长越好,牛牛希望你来帮他计算一下他最多能独立生活多少天。
 * 
 * 输入包括一行,四个整数x, f, d, p(1 <= x,f,d,p <= 2 * 10^9),以空格分割
 * 
 * 输出一个整数, 表示牛牛最多能独立生活多少天
 * 
 * 思路：
 * 	考虑money是否可以度过有水果的前几天
 * 	1.能，days = (money - liveCost * fruitNum) / (fruitPrice + liveCost) + fruitNum;	
 * 	2.不能，days = money / liveCost;
 * 
 * 
 * https://www.nowcoder.com/question/next?pid=10714908&qid=164546&tid=16586488
 * @author Yanjie
 *
 */
public class Survive {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in  = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Survice.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int liveCost = in.nextInt();
		int fruitNum = in.nextInt();
		int money = in.nextInt();
		int fruitPrice = in.nextInt();
		
		int days = 0;
		if (money >= liveCost * fruitNum) {
			days = (money - liveCost * fruitNum) / (fruitPrice + liveCost) + fruitNum;	
		} else {
			days = money / liveCost;
		}
		System.out.println(days);
		in.close();
	}
}
