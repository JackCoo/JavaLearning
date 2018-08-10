package algotithem.basicclass.class_06;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 * 
 * @author Yanjie
 *
 */
public class Graph {
	
	/**
	 * 点集
	 */
	public HashMap<Integer,Node> nodes;
	
	/**
	 * 边集
	 */
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
