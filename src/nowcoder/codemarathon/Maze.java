package nowcoder.codemarathon;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * NowCoder最喜欢游乐场的迷宫游戏，他和小伙伴们比赛谁先走出迷宫。 
 * 现在把迷宫的地图给你，你能帮他算出最快走出迷宫需要多少步吗？
 * 
 * 输入包含多组数据。
 * 每组数据包含一个10*10，由“#”和“.”组成的迷宫。其中“#”代表墙；“.”代表通路。
 * 入口在第一行第二列；出口在最后一行第九列。
 * 从任意一个“.”点都能一步走到上下左右四个方向的“.”点。
 * 
 * 对应每组数据，输出从入口到出口最短需要几步。
 * 
 * 答案：16 40
 * https://www.nowcoder.com/practice/6276dbbda7094978b0e9ebb183ba37b9?tpId=3&&tqId=10880&rp=1&ru=/activity/oj&qru=/ta/hackathon/question-ranking
 * 
 * 思路： 
 *  迷宫问题、无权图最短路径的求解可以抽象为连通图的遍历
 *  BFS：找出的第一条路径就是最短路径。 宽搜，层数较小（离顶点较近）的节点先被访问，保证了第一条达到路径为最短路径，类似于水波纹扩散。
 *  DFS：找到的第一条可行路径不一定是最短路径，如果需要找到最短路径，那么需要找出所有可行路径后，再逐一比较，求出最短路径。
 *  
 * @author Yanjie
 *
 */
public class Maze {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\codemarathon\\Maze.txt"));
		
		while (in.hasNext()) {
			int n = 10;
			char[][] map = new char[n][n];
			for (int x = 0; x < n; x++) {
				map[x] = in.nextLine().toCharArray();
			}
			System.out.println(process(map, 0, 1, 9, 8));
		}
		in.close();
	}
	
	/**
	 * BFS
	 * 
	 * 无需生成Graph对象，费事。迷宫地图Map即可表示图，使用Node类型为了简化编码。
	 * 
	 * @author Yanjie
	 *
	 * @param map
	 * @param entryX
	 * @param entryY
	 * @param exitX
	 * @param exitY
	 * @return
	 */
	public static int process(char[][] map, int entryX, int entryY, int exitX, int exitY) {
		
		
		// 邻居，定义上下左右四个方向
		int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		
		// node队列
		Queue<Node> nodePQ = new LinkedList<>();
		
		// 标记数组。记录节点是否被访问过以及被访问的次序。
		int[][] accessed = new int[map.length][map[0].length];
		
		nodePQ.add(new Node(entryX, entryY));
		accessed[entryX][entryY] = 1;
		
		while (!nodePQ.isEmpty()) {
			Node curNode = nodePQ.poll();
			if (curNode.x == exitX && curNode.y == exitY) {
				return accessed[curNode.x][curNode.y] - 1; // 返回路径长度，入口节点为1.
			}
			
			// 将当前节点符合条件的邻居节点放入队列
			for (int i = 0; i < 4; i++) {
				Node nextNode = new Node(curNode.x + direction[i][0], curNode.y + direction[i][1]);
				if (nextNode.x >=0 && nextNode.x < map.length && nextNode.y >= 0 && nextNode.y < map[0].length 
						&& map[nextNode.x][nextNode.y] != '#' && accessed[nextNode.x][nextNode.y] == 0) { // 不越界、不是墙、未被访问过
					
					nodePQ.add(nextNode);
					accessed[nextNode.x][nextNode.y] = accessed[curNode.x][curNode.y] + 1;// 标记
				}
			}
		}
		
		return -1;
	}
}

/**
 * 节点，本题也可以用int[]表示节点。
 * 
 * @author Yanjie
 *
 */
class Node {
	public int x;
	public int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
