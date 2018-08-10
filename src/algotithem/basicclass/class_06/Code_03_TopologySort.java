package algotithem.basicclass.class_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序
 *  有向无环图节点排序，若存在A->B路径，则A排在B之前。
 *  
 * 实现：输出当前入度为0的节点，并将其邻居节点入度减1，循环。
 * 	队列。
 * 
 * @author Yanjie
 *
 */
public class Code_03_TopologySort {

	public static List<Node> sortedTopology(Graph graph) {
		
		// 节点入度Map
		HashMap<Node, Integer> inMap = new HashMap<>();
		
		// 入度为0队列
		Queue<Node> zeroInQueue = new LinkedList<>();
		
		// 排序结果
		List<Node> result = new ArrayList<>();
		
		// 统计入度，将入度为0节点放入队列。
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		
		// 从队列中弹出节点，并将其邻居节点入度减1，将入度为0的邻居入队列。
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);
			
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		
		return result;
	}
}
