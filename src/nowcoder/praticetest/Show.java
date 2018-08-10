package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 贪心——会议室安排问题
 * 
 * 已知牛牛拿到了n个上台表演的机会，第i次表演的上台时间为ti时刻，需要表演mi这么长的时间。
 * 牛牛为了提高自己的知名度，肯定要取得最多的上场次数。请问，牛牛最多能上场多少次呢？
 * 
 * 第一行输入一个数字n(1≤n≤100000)，表示牛牛获得的上台表演的机会
 * 接下来n行，每行输入两个数字ti(1≤ti≤108)和mi(1≤mi≤108), 表示第i次表演机会的上台时间和该次表演需要的总时间（表演途中不能中断表演退出）。
 * 
 * 牛牛最多能上场的次数。
 * 
 * 思路：
 * 	贪心策略：选择当前最早结束的，冲突跳过。
 * 	利用小根堆或者直接排序都行。
 * 
 * @author Yanjie
 *
 */
public class Show {

	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\Show.txt"));
		int n = in.nextInt();
		
		// 读取表演信息，根据结束时间放入小根堆
		PriorityQueue<ShowInfo> showPQ = new PriorityQueue<ShowInfo>((ShowInfo s1, ShowInfo s2) -> {return s1.getEndTime() - s2.getEndTime();});
		for (int x = 0; x < n; x++) {
			showPQ.add(new ShowInfo(in.nextInt(), in.nextInt()));
		}
		
		// 贪心策略-选择当前最早结束的，冲突跳过。
		int count = 0;
		int lastEndTime = 0;
		while (!showPQ.isEmpty()) {
			ShowInfo curShow = showPQ.poll();
			if (curShow.getStartTime() >= lastEndTime) {
				count++;
				lastEndTime = curShow.getEndTime();
			}
		}
		
		System.out.println(count);
	}
}

class ShowInfo{
	private int startTime;
	private int endTime;
	
	public ShowInfo(int startTime, int usedTime) {
		super();
		this.startTime = startTime;
		this.endTime = startTime + usedTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}
	
}