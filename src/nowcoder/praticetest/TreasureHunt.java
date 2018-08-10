package nowcoder.praticetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * 寻宝
 * 
 * 亮亮解出了卷轴隐藏的秘密，来到了一片沼泽地。这里有很多空地，而面试直通卡可能埋在任意一块空地中，
 * 好在亮亮发现了一堆木材，他可以将木材铺在两个空地之间的沼泽地上。因为亮亮不知道面试直通卡具体在哪一块空地中，
 * 所以必须要保证任意一块空地对于亮亮来说是可以抵达的。 “怎么还有鳄鱼！没办法，看来有些空地不能直接到达了。” 
 * 亮亮虽然没有洁癖，但是沼泽地实在太臭了，所以亮亮不会循环利用木材。而且木材不能拼接在一起使用，
 * 所以亮亮必须要知道在耗费木材最少的情况下，最长的那根木材至少需要多长。
 * 
 * 第一行包含两个整数N(1≤N≤10000),M(1≤M≤1000000)。N表示公有N块空地。
 * 接下来M行，每行包含三个整数P(1≤P≤N),Q(1≤Q≤N),K代表P,Q两个间没有鳄鱼，需要耗费K的木材。
 * 
 * 一个整数，即耗费木材最少的情况下，最长的那根木材长度。
 * 
 * 思路：
 * 	N块空地，空地P和空地Q间没有鳄鱼时可以铺K长木板过去。把空地看成图中的点，题目数据给出这些点的 边 以及 权值。
 * 	为了求总的木板耗费最少，问题转化成求图的最小生成树，输出最小生成树里权值最大的边。
 * 
 * @author Yanjie
 *
 */
public class TreasureHunt {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\TreasureHunt.txt"));
		int n = in.nextInt();
		int m = in.nextInt();
		
		int[][] dataArray = new int[m][3];
		for (int x = 0; x < m; x++) {
			dataArray[x][1] = in.nextInt();
			dataArray[x][2] = in.nextInt();
			dataArray[x][0] = in.nextInt();
		}
		Graph graph = new Graph(dataArray);
		System.out.println(kruskalMST(graph).weight);
		in.close();
	}
	
	public static Edge kruskalMST(Graph graph) {
		UnionFind unionFind = new UnionFind();
		unionFind.makeSets(graph.nodes.values());
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((Edge o1, Edge o2) -> {return o1.weight - o2.weight;});
		for (Edge edge : graph.edges) {
			priorityQueue.add(edge);
		}
		
		Edge lastEage = null;
		while (!priorityQueue.isEmpty()) {
			Edge edge = priorityQueue.poll();
			if (!unionFind.isSameSet(edge.from, edge.to)) {
				lastEage = edge;
				unionFind.union(edge.from, edge.to);
			}
		}
		return lastEage;
	}
}



/**
 * 图
 * 
 * @author Yanjie
 *
 */
class Graph {
	
	/**
	 * 点集,k-点编号，value值
	 */
	public HashMap<Integer,Node> nodes;
	
	/**
	 * 边集
	 */
	public HashSet<Edge> edges;

	
	/**
	 * 由弧表示法数组生成图
	 * 
	 * [[边权， from， to]，
	 * 		.
	 * 		.
	 * ]
	 * 
	 * @author Yanjie
	 *
	 * @param matrix
	 * @return
	 */
	public Graph(int[][] matrix) {
		
		nodes = new HashMap<>();
		edges = new HashSet<>();
		
		for (int i = 0; i < matrix.length; i++) {
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			if (!nodes.containsKey(from)) {
				nodes.put(from, new Node(from));
			}
			if (!nodes.containsKey(to)) {
				nodes.put(to, new Node(to));
			}
			Node fromNode = nodes.get(from);
			Node toNode = nodes.get(to);
			Edge newEdge = new Edge(weight, fromNode, toNode);
			fromNode.nexts.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(newEdge);
			edges.add(newEdge);
		}
	}
}

/**
 * 图 -节点
 * @author Yanjie
 *
 */
class Node {
	
	/**
	 *  节点值
	 */
	public int value;
	
	/**
	 *  入度：指向该节点的节点数
	 */
	public int in;
	
	/**
	 *  出度
	 */
	public int out;
	
	/** 
	 * 邻居节点，该节点所能到达的下一个节点
	 */
	public ArrayList<Node> nexts;
	
	/**
	 *  边，由该节点出发的边
	 */
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}

/**
 * 图-边
 * 
 * @author Yanjie
 *
 */
class Edge {
	
	/**
	 *  权重
	 */
	public int weight;
	
	/**
	 *  from节点
	 */
	public Node from;
	
	/**
	 *  to节点
	 */
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
}

/**
 * 并查集
 *  
 * @author Yanjie
 *
 */
class UnionFind {
	private HashMap<Node, Node> fatherMap;
	private HashMap<Node, Integer> rankMap;

	public UnionFind() {
		fatherMap = new HashMap<Node, Node>();
		rankMap = new HashMap<Node, Integer>();
	}

	private Node findFather(Node n) {
		Node father = fatherMap.get(n);
		if (father != n) {
			father = findFather(father);
		}
		fatherMap.put(n, father);
		return father;
	}

	public void makeSets(Collection<Node> nodes) {
		fatherMap.clear();
		rankMap.clear();
		for (Node node : nodes) {
			fatherMap.put(node, node);
			rankMap.put(node, 1);
		}
	}

	public boolean isSameSet(Node a, Node b) {
		return findFather(a) == findFather(b);
	}

	public void union(Node a, Node b) {
		if (a == null || b == null) {
			return;
		}
		Node aFather = findFather(a);
		Node bFather = findFather(b);
		if (aFather != bFather) {
			int aFrank = rankMap.get(aFather);
			int bFrank = rankMap.get(bFather);
			if (aFrank <= bFrank) {
				fatherMap.put(aFather, bFather);
				rankMap.put(bFather, aFrank + bFrank);
			} else {
				fatherMap.put(bFather, aFather);
				rankMap.put(aFather, aFrank + bFrank);
			}
		}
	}
}

