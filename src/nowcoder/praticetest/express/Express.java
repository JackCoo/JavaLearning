package nowcoder.praticetest.express;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


/**
 * 图的最短路径
 * 
 * 牛牛的快递到了，他迫不及待地想去取快递，但是天气太热了，以至于牛牛不想在烈日下多走一步。
 * 他找来了你，请你帮他规划一下，他最少要走多少距离才能取回快递。
 * 
 * 每个输入包含一个测试用例。
 * 输入的第一行包括四个正整数，表示位置个数N(2<=N<=10000)，道路条数M(1<=M<=100000)，
 * 起点位置编号S(1<=S<=N)和快递位置编号T(1<=T<=N)。位置编号从1到N，道路是单向的。
 * 数据保证S≠T，保证至少存在一个方案可以从起点位置出发到达快递位置再返回起点位置。
 * 接下来M行，每行包含三个正整数，表示当前道路的起始位置的编号U(1<=U<=N)，
 * 当前道路通往的位置的编号V(1<=V<=N)和当前道路的距离D(1<=D<=1000)。
 * 
 * 对于每个用例，在单独的一行中输出从起点出发抵达快递位置再返回起点的最短距离。
 * 
 * 思路：
 * 	加权图的最短路径，使用dijkstra分别计算起点和终点。
 * 
 * https://www.nowcoder.com/test/question/071695ed1d0b4e65b07eb969d212b92a?pid=10714908&tid=16542644
 * @author Yanjie
 *
 */
public class Express {
	
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\praticetest\\express\\Express.txt"));
		
		
		int siteNum = in.nextInt();
		int roadNum = in.nextInt();
		int startIndex = in.nextInt();
		int targetIndex = in.nextInt();
		
		Graph graph = new Graph(siteNum);
		for (int x = 0; x < roadNum; x++) {
			graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
		}
		
		
		Node startNode = graph.nodes[startIndex];
		Node targetNode = graph.nodes[targetIndex];
		
		int result1 = dijkstra1(startNode, targetNode, graph.nodes);
		int result2 = dijkstra1(targetNode, startNode, graph.nodes);
		
		System.out.println(result1 + result2);
		in.close();
	}
	

	/**
	 * dijkstra1修改版
	 * 
	 * 1.存储类型使用数组，速度提升近100%。
	 * 2.未计算源点到所有节点的最短路径，在获得目标节点的最短路径后即刻返回。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @param end
	 * @param nodes
	 * @return
	 */
	public static int dijkstra1(Node head, Node end, Node[] nodes) {
		
		// 保存源点到各个节点的最短距离
		int[] distanceArray = new int[nodes.length];
		Arrays.fill(distanceArray, Integer.MAX_VALUE);
		distanceArray[head.index] = 0;
		
		// 已确定最短距离的节点集合，确定为1否则为0
		int[] selectedArray = new int[nodes.length];
		
		// 循环，distanceArray未确定节点中距离最小节点视为确定最小距离，考察其边，更新其相连节点的最小距离。
		int minNodeIndex = getMinDistanceAndUnselectedNode(distanceArray, selectedArray);
		while (minNodeIndex > 0) {
			
			/**
			 * 根据题意添加，减小耗时。
			 * 确定目标节点的最小路径后即返回
			 */
			if (minNodeIndex == end.index) {
				return distanceArray[end.index];
			} 
			
			selectedArray[minNodeIndex] = 1;
			int distance = distanceArray[minNodeIndex];
			for (Edge edge : nodes[minNodeIndex].edges) {
				Node toNode = edge.to;
				distanceArray[toNode.index] = Math.min(distanceArray[toNode.index], distance + edge.weight);
			}
			minNodeIndex = getMinDistanceAndUnselectedNode(distanceArray, selectedArray);
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * 从distanceArray中未确定最短距离（不在selectedArray集合中）的节点中挑选出距离最小的节点，返回其编号
	 * 
	 * 
	 * @author Yanjie
	 *
	 * @param distanceArray
	 * @param selectedArray
	 * @return -1表示所有节点的最短距离已确定，或者剩余未处理的节点不可达（距离为Integer.MAX_VALUE）
	 */
	public static int getMinDistanceAndUnselectedNode(int[] distanceArray, int[] selectedArray) {
		int minDistance = Integer.MAX_VALUE;
		int minDistanceNodeIndex = -1;
		for (int i = 0; i < distanceArray.length; i++) {
			if (selectedArray[i] == 0) { // 未确定=0
				if (distanceArray[i] < minDistance) {
					minDistance = distanceArray[i];
					minDistanceNodeIndex = i;
				}
			}
		}
		return minDistanceNodeIndex;
	}
}



