package algotithem.basicclass.class_06;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树-P算法
 * 无向图
 * 此算法可以称为“加点法”，每次迭代选择代价最小的边对应的点，加入到最小生成树中。算法从某一个顶点s开始，逐渐长大覆盖整个连通网的所有顶点。
 * 1.图的所有顶点集合为V；初始令集合u={s},v=V−u;
 * 2.在两个集合u,v能够组成的边中，选择一条代价最小的边(u0,v0)，加入到最小生成树中，并把v0并入到集合u中。
 * 3.重复上述步骤，直到最小生成树有n-1条边或者n个顶点为止。
 * @author Yanjie
 *
 */
public class Code_05_Prim {

	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static Set<Edge> primMST(Graph graph) {
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		
		// u集合
		HashSet<Node> set = new HashSet<>();
		Set<Edge> result = new HashSet<>();
		for (Node node : graph.nodes.values()) { // 考虑到有不连通的区域，对于一个连通图该for可以去掉。
			
			if (!set.contains(node)) { // 初始值s节点放入u集合
				set.add(node);
				for (Edge edge : node.edges) {
					priorityQueue.add(edge);
				}
				
				// 考察u集合的边
				while (!priorityQueue.isEmpty()) {
					Edge edge = priorityQueue.poll();
					Node toNode = edge.to;
					if (!set.contains(toNode)) { // toNode位于v集合
						set.add(toNode);
						
						// toNode放入u集合，解锁新的边
						result.add(edge);
						for (Edge nextEdge : toNode.edges) { 
							priorityQueue.add(nextEdge);
						}
					}
				}
			}
		}
		return result;
	}

}
