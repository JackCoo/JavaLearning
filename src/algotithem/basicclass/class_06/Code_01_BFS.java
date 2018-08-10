package algotithem.basicclass.class_06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import nowcoder.codemarathon.Maze;
import nowcoder.praticetest.VisitingPark;

/**
 * 广度、宽度优先遍历、搜索
 * 按层遍历
 * 
 * 队列 + set：源节点入队列，没弹出一个节点将其所有未入过队列（set）的邻居节点入队列，直至队列空。
 * 
 * 应用：
 * 	无向（？）无权图的最短距离
 * 		因为广度优先搜索最先访问到的是相邻的点，所以
 * 			1.距离近的点最先访问到，例如 {@link VisitingPark}，多源点多终点的最短距离。
 * 			2.到达某一节点的距离是所有路径中最短的，例如{@link Maze}，迷宫问题，宽搜第一次访问到终点的路径是最短路径。
 * 		实现上：
 * 			多用数组标记节点是否被访问过以及被访问的次序（最短距离），不使用set。
 * 			对于格子型的图，多用方向数组表示其可能存在的邻居，以简化代码。
 * 			只需要Node类型即可，不需要Graph类，可用点集也可以用数组表示Map。
 * @author Yanjie
 *
 */
public class Code_01_BFS {

	public static void bfs(Node node) {
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>();
		queue.add(node);
		set.add(node);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			// do something
			System.out.println(cur.value);
			
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					set.add(next);
					queue.add(next);
				}
			}
		}
	}

}
