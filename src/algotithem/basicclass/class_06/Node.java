package algotithem.basicclass.class_06;

import java.util.ArrayList;

/**
 * 图 -节点
 * @author Yanjie
 *
 */
public class Node {
	
	// 节点值
	public int value;
	
	// 入度：指向该节点的节点数
	public int in;
	
	// 出度
	public int out;
	
	// 邻居节点，该节点所能到达的下一个节点
	public ArrayList<Node> nexts;
	
	// 边，由该节点出发的边
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
