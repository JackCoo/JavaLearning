package algotithem.basicclass.class_06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * Dijkstra使用了广度优先搜索解决 赋权 有向图 的单源 最短路径问题
 * 
 * 最短路径定义：
 * 	从图中的某个顶点出发到达另外一个顶点的所经过的边的权重和最小的一条路径，称为最短路径
 * 
 * 适用于：
 * 	有向正权图的最短路径（不含负数边权）
 * 
 * @author Yanjie
 *
 */
public class Code_06_Dijkstra {

	/**
	 * 从顶点head到图各个节点的最短距离
	 * 思路：
	 * 		集合S保留所有已确定最短距离的节点，集合R保留所有节点及其当前最小距离。在R中选择拥有最小距离的节点 u，可将其放入集合S（因为图的边权
	 * 	均为正数且当前s-u最小，所以通过其它未确定最短距离节点中转到达u一定大于当前到达u的路径）。考察u的出度，如果存在一条从 u 到 v 的边，
	 * 	那么从 s 到 v 的路径可以经由边（u, v）到达，这条路径的长度是 d[u] + w(u, v)。如果这个值比目前已知的 d[v] 的值要小，
	 * 	我们可以用新值来替代当前 d[v] 中的值。循环，直至确定所有节点的最短路径。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return Map，无法到达的节点不在Map中。
	 */
	public static HashMap<Node, Integer> dijkstra1(Node head) {
		
		// result，保存源点到各个节点的最短距离（只包含可达节点）
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(head, 0);
		
		// 已确定最短距离的节点集合
		HashSet<Node> selectedNodes = new HashSet<>();

		Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		
		// 循环，distanceMap未确定节点中距离最小节点视为确定最小距离，考察其边，更新其相连节点的最小距离。
		while (minNode != null) {
			selectedNodes.add(minNode);
			int distance = distanceMap.get(minNode);
			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;
				if (!distanceMap.containsKey(toNode)) {
					distanceMap.put(toNode, distance + edge.weight);
				}
				distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
			}
			minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		}
		return distanceMap;
	}

	/**
	 * 从diatanceMap中未确定最短距离（不在selectedNodes集合中）的节点你中挑选出距离最小的节点。
	 * 
	 * @author Yanjie
	 *
	 * @param distanceMap
	 * @param selectedNodes
	 * @return
	 */
	public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, 
			HashSet<Node> selectedNodes) {
		Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
			Node node = entry.getKey();
			int distance = entry.getValue();
			if (!selectedNodes.contains(node) && distance < minDistance) {
				minNode = node;
				minDistance = distance;
			}
		}
		return minNode;
	}

	
	
	
	
	public static class NodeRecord {
		public Node node;
		public int distance;

		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}

	public static class NodeHeap {
		private Node[] nodes;
		private HashMap<Node, Integer> heapIndexMap;
		private HashMap<Node, Integer> distanceMap;
		private int size;

		public NodeHeap(int size) {
			nodes = new Node[size];
			heapIndexMap = new HashMap<>();
			distanceMap = new HashMap<>();
			this.size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void addOrUpdateOrIgnore(Node node, int distance) {
			if (inHeap(node)) {
				distanceMap.put(node, Math.min(distanceMap.get(node), distance));
				insertHeapify(node, heapIndexMap.get(node));
			}
			if (!isEntered(node)) {
				nodes[size] = node;
				heapIndexMap.put(node, size);
				distanceMap.put(node, distance);
				insertHeapify(node, size++);
			}
		}

		public NodeRecord pop() {
			NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
			swap(0, size - 1);
			heapIndexMap.put(nodes[size - 1], -1);
			distanceMap.remove(nodes[size - 1]);
			nodes[size - 1] = null;
			heapify(0, --size);
			return nodeRecord;
		}

		private void insertHeapify(Node node, int index) {
			while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int index, int size) {
			int left = index * 2 + 1;
			while (left < size) {
				int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
						? left + 1 : left;
				smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
				if (smallest == index) {
					break;
				}
				swap(smallest, index);
				index = smallest;
				left = index * 2 + 1;
			}
		}

		private boolean isEntered(Node node) {
			return heapIndexMap.containsKey(node);
		}

		private boolean inHeap(Node node) {
			return isEntered(node) && heapIndexMap.get(node) != -1;
		}

		private void swap(int index1, int index2) {
			heapIndexMap.put(nodes[index1], index2);
			heapIndexMap.put(nodes[index2], index1);
			Node tmp = nodes[index1];
			nodes[index1] = nodes[index2];
			nodes[index2] = tmp;
		}
	}

	public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
		NodeHeap nodeHeap = new NodeHeap(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0);
		HashMap<Node, Integer> result = new HashMap<>();
		while (!nodeHeap.isEmpty()) {
			NodeRecord record = nodeHeap.pop();
			Node cur = record.node;
			int distance = record.distance;
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
			}
			result.put(cur, distance);
		}
		return result;
	}

}
