package nowcoder.praticetest.express;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


/**
 * 图
 * 
 * @author Yanjie
 *
 */
class Graph {
	
	/**
	 * 点集，根据Node编号（index）依次放置
	 */
	Node[] nodes;
	
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
	public Graph(int[][] matrix, int nodeNum) {
		nodes = new Node[nodeNum + 1];
		edges = new HashSet<>();
		for (int i = 0; i < matrix.length; i++) {
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			Node fromNode = nodes[from];
			Node toNode = nodes[to];
			Edge newEdge = new Edge(weight, fromNode, toNode);
			fromNode.nexts.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(newEdge);
			edges.add(newEdge);
		}
	}
	
	/**
	 * 默认构造器
	 * 
	 * @param NodeNum
	 */
	public Graph(int nodeNum) {
		this.nodes = new Node[nodeNum + 1];
		this.edges = new HashSet<>();
	}
	
	/**
	 * 根据节点index添加边
	 * 
	 * @author Yanjie
	 *
	 * @param from 
	 * @param to
	 * @param weight
	 */
	public void addEdge(int from, int to, int weight) {
		if (nodes[from] == null) {
			nodes[from] = new Node(from);
		}
		if (nodes[to] == null) {
			nodes[to] = new Node(to);
		}
		Node fromNode = nodes[from];
		Node toNode = nodes[to];
		Edge newEdge = new Edge(weight, fromNode, toNode);
		fromNode.nexts.add(toNode);
		fromNode.out++;
		toNode.in++;
		fromNode.edges.add(newEdge);
		edges.add(newEdge);
	}
}

/**
 * 图 -节点
 * 
 * @author Yanjie
 *
 */
class Node {
	
	/**
	 *  节点编号
	 */
	public int index;
	
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

	/**
	 * 
	 * @param index
	 */
	public Node(int index) {
		this.index = index;
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

	/**
	 * 
	 * @param weight
	 * @param from
	 * @param to
	 */
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
