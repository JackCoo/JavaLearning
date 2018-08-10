package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * BFS
 * 
 * 又是晴朗的一天，牛牛的小伙伴们都跑来找牛牛去公园玩。但是牛牛想呆在家里看E3展，不想出去逛公园，可是牛牛又不想鸽掉他的小伙伴们，于是找来了公园的地图，
 * 发现公园是由一个边长为n的正方形构成的，公园一共有m个入口，但出口只有一个。公园内有一些湖和建筑，牛牛和他的小伙伴们肯定不能从他们中间穿过，所以只能绕行。
 * 牛牛想知道他需要走的最短距离并输出这个最短距离。 
 * 
 * 第一行输入一个数字n(1≤n≤1000)表示公园的边长
 * 接下来会给你一个n*n的公园地图，其中 . 表示公园里的道路，@表示公园的入口，*表示公园的出口，#表示公园内的湖和建筑。
 * 牛牛和他的小伙伴们每次只能 上下左右 移动一格位置。
 * 输入保证公园入口个数m(1≤m≤10000)且所有的入口都能和出口相连。
 * 
 * 输出牛牛需要行走的最短距离。
 * 
 * 思路：
 * 	BFS处理多顶点-多个顶点初始化时全部放入队列即可。
 *  
 * ps：多顶点BFS输出第一个输出仍然是最短路径，当由某一顶点出发的路径碰到其它节点已经形成的路径时，已被访问过的节点不会被
 * 	再次访问，即重复路径会被舍弃，保留较小者。
 * 
 * 为什么不在接收输入的同时创建点集：也可以。邻居不包含墙。双向。
 * 	
 * 
 * @author Yanjie
 *
 */
public class VisitingPark {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\VisitingPark.txt"));
		int n = in.nextInt();
		char[][] map = new char[n][n];
		List<Node2> entryList = new ArrayList<>();
		Node2 exit = null;
		in.nextLine();
		for (int x = 0; x < n; x++) {
			char[] lineCharArray = in.nextLine().toCharArray();
			for (int y = 0; y < n; y++) {
				map[x][y] = lineCharArray[y];
				if (lineCharArray[y] == '@') {
					entryList.add(new Node2(x, y));
				} else if (lineCharArray[y] == '*' ) {
					exit = new Node2(x, y);
				}
			}
		}
		
		/**
		 * 方法2，单个问题=BFS，对每个入口执行一次BFS。
		 * 超时，50%。
		 */
		int minPath = Integer.MAX_VALUE;
		for (Node2 entryNode : entryList) {
			minPath = Math.min(minPath, process2(map, entryNode, exit));
		}
		System.out.println(minPath);
		
		/**
		 * 方法3
		 * 	将多个入口节点看成一个连通所有入口节点的超级节点，问题变成了单入口单出口的普通BFS。
		 * 实现：
		 * 	初始化时，将所有入口节点放入队列即可。
		 */
		System.out.println(process3(map, entryList, exit));
		
		
		in.close();
	}
	
	/**
	 * BFS-2
	 * 
	 * 含有多个入口节点
	 * 
	 * @author Yanjie
	 *
	 * @param map
	 * @param entry
	 * @param exit
	 * @return
	 */
	public static int process3(char[][] map, List<Node2> entryList, Node2 exit) {
		
		// 邻居，上下左右四个方向
		int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		
		// 节点队列
		Queue<Node2> nodePQ = new LinkedList<>();
		
		// 标记数组，标记该节点是否访问过以及被访问的次序（距离顶点距离）
		int[][] accessed = new int[map.length][map[0].length];
		
		
		for (Node2 entry : entryList) {
			nodePQ.add(entry);
			accessed[entry.x][entry.y] = 1;
		}

		
		while (!nodePQ.isEmpty()) {
			Node2 curNode = nodePQ.poll();
			
			if (curNode.equals(exit)) {
				return accessed[curNode.x][curNode.y] - 1;
			}
			
			// 当前节点符合条件的邻居节点放入队列
			for (int i = 0; i < 4; i++) {
				Node2 nextNode = new Node2(curNode.x + direction[i][0], curNode.y + direction[i][1]);
				if (nextNode.x >= 0 && nextNode.x < map.length && nextNode.y >= 0 && nextNode.y < map[0].length
						&& map[nextNode.x][nextNode.y] != '#' && accessed[nextNode.x][nextNode.y] == 0) { // 不越界、不是墙、未被访问过
					
					nodePQ.add(nextNode);
					accessed[nextNode.x][nextNode.y] = accessed[curNode.x][curNode.y] + 1; // 标记
				}
			}
		}
		
		return -1;
	}
	
	
	/**
	 * BFS-1
	 * 
	 * 单入口、单出口
	 * 
	 * @author Yanjie
	 *
	 * @param map
	 * @param entry
	 * @param exit
	 * @return
	 */
	public static int process2(char[][] map, Node2 entry, Node2 exit) {
		
		// 邻居，上下左右四个方向
		int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		
		// 节点队列
		Queue<Node2> nodePQ = new LinkedList<>();
		
		// 标记数组，标记该节点是否访问过以及被访问的次序（距离顶点距离）
		int[][] accessed = new int[map.length][map[0].length];
		
		nodePQ.add(entry);
		accessed[entry.x][entry.y] = 1;
		
		while (!nodePQ.isEmpty()) {
			Node2 curNode = nodePQ.poll();
			
			if (curNode.equals(exit)) {
				return accessed[curNode.x][curNode.y] - 1;
			}
			
			// 当前节点符合条件的邻居节点放入队列
			for (int i = 0; i < 4; i++) {
				Node2 nextNode = new Node2(curNode.x + direction[i][0], curNode.y + direction[i][1]);
				if (nextNode.x >= 0 && nextNode.x < map.length && nextNode.y >= 0 && nextNode.y < map[0].length
						&& map[nextNode.x][nextNode.y] != '#' && accessed[nextNode.x][nextNode.y] == 0) { // 不越界、不是墙、未被访问过
					
					nodePQ.add(nextNode);
					accessed[nextNode.x][nextNode.y] = accessed[curNode.x][curNode.y] + 1; // 标记
				}
			}
		}
		
		return -1;
	}
	
	
	
	
	/**
	 * 递归如何解决回头路、环路问题？
	 * @author Yanjie
	 *
	 * @param map
	 * @param curX
	 * @param curY
	 * @return
	 */
	// 输出由该入口的最短路径  cur 为入口
	public static int process1(char[][] map, int curX, int curY) {
		
		// base case
		if (curX >= map.length || curY >= map.length || curX < 0 || curY < 0) {
			return -1;
		}
		if (map[curX][curY] == '*') {
			return 0;
		}
		if (map[curX][curY] == '#') {
			return -1;
		}
		
		// 入口？
		
		int minPath = Integer.MAX_VALUE;
		int path1 = process1(map, curX - 1, curY) + 1;
		if (path1 > 0) {
			minPath = minPath > path1 ? path1 : minPath;
		}
		int path2 = process1(map, curX + 1, curY) + 1;
		if (path2 > 0) {
			minPath = minPath > path2 ? path2 : minPath;
		}
		int path3 = process1(map, curX, curY - 1) + 1;
		if (path3 > 0) {
			minPath = minPath > path3 ? path3 : minPath;
		}
		int path4 = process1(map, curX, curY + 1) + 1;
		if (path4 > 0) {
			minPath = minPath > path4 ? path4 : minPath;
		}
		
		return minPath;
	}
}

/**
 * 节点
 * 
 * @author Yanjie
 *
 */
class Node2 {
	public int x;
	public int y;
	public Node2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node2 other = (Node2) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}