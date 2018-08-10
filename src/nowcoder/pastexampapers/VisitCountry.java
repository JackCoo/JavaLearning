package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;




/**
 * 魔法王国一共有n个城市,编号为0~n-1号,n个城市之间的道路连接起来恰好构成一棵树。
 * 小易现在在0号城市,每次行动小易会从当前所在的城市走到与其相邻的一个城市,小易最多能行动L次。
 * 如果小易到达过某个城市就视为小易游历过这个城市了,小易现在要制定好的旅游计划使他能游历最多的城市,
 * 请你帮他计算一下他最多能游历过多少个城市(注意0号城市已经游历了,游历过的城市不重复计算)。 
 * 
 * 输入包括两行,第一行包括两个正整数n(2 ≤ n ≤ 50)和L(1 ≤ L ≤ 100),表示城市个数和小易能行动的次数。
 * 第二行包括n-1个整数parent[i](0 ≤ parent[i] ≤ i), 
 * 对于每个合法的i(0 ≤ i ≤ n - 2),在(i+1)号城市和parent[i]间有一条道路连接。
 * 
 * 输出一个整数,表示小易最多能游历的城市数量。
 * 
 * 思路：
 * 	1.不能将步数浪费在回头路上，故使用DFS。单纯的DFS 50%case，加入贪心策略，在邻居遍历的选择上，优先选择路径
 * 	较深的邻居先遍历。60%case。
 *  2.动态规划
 * 
 * @author Yanjie
 *
 */
public class VisitCountry {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\VisitCountry"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int n = in.nextInt();
		int l = in.nextInt();
		
		// 点集
		Map<Integer,CityNode> citys = new HashMap<>(n);
		CityNode zeroCity = new CityNode(0);
		citys.put(0, zeroCity);
		
		for (int i = 1; i < n; i++) {
			
			// 获取或创建第i城市节点
			CityNode iCity = citys.get(i);
			if (iCity == null) {
				iCity = new CityNode(i);
				citys.put(i, iCity);
			}
			
			// 获取或创建i城市的父结点
			int parent = in.nextInt();
			CityNode parentCity = citys.get(parent);
			if (parentCity == null) {
				parentCity = new CityNode(parent);
				citys.put(parent, parentCity);
			}
			
			// 更新参数
			parentCity.nexts.add(iCity);
			iCity.parentNode = parentCity;
			parentCity.maxLength = parentCity.maxLength == 0 ? 1 : parentCity.maxLength;
			while (parentCity.parentNode != null) { // 循环向上更新父结点的最长子路径
				parentCity = parentCity.parentNode;
				int maxLength = 0;
				for (CityNode city : parentCity.nexts) {
					maxLength = Math.max(city.maxLength, maxLength);
				}
				parentCity.maxLength = maxLength + 1;
			}
		}
		
		System.out.println(dfs(zeroCity, l));
	}
	
	/**
	 * DFS，弹出一个节点是可用步数减一。
	 * 
	 * @author Yanjie
	 *
	 * @param zeroNode 起始节点
	 * @param l 最长步数
	 * @return
	 */
	public static int dfs(CityNode zeroNode, int l) {
		Stack<CityNode> stack = new Stack<>();
		HashSet<CityNode> set = new HashSet<>();
		
		stack.add(zeroNode);
		set.add(zeroNode);
		l++;// 0好节点不算步数
		
		while (!stack.isEmpty()) {
			CityNode cur = stack.pop();
			l--;
			if (l == 0) {
				break;
			}
			
			// DFS在邻居遍历的选择上，优先选择路径较深的。
			cur.nexts.sort((CityNode c1, CityNode c2)->{
				int lengthDiff = c2.maxLength - c1.maxLength;
				return lengthDiff;
			});
			for (CityNode next : cur.nexts) {
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					break;
				}
			}
		}
		return set.size();
	}
}

/**
 * 图 -节点
 * @author Yanjie
 *
 */
class CityNode {
	
	// 节点值
	public int value;
	
	// 以该节点为起点的最长路径
	public int maxLength;
	
	public CityNode parentNode = null;
	
	// 邻居节点，该节点所能到达的下一个节点
	public ArrayList<CityNode> nexts;

	public CityNode(int value) {
		this.value = value;
		nexts = new ArrayList<>();
	}

	
	
	
	
	
}
