package algotithem.basicclass.class_07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 会议室安排
 * 题目：一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目的宣讲。 给你每一个项目开始的时间和结束的时间，安排宣讲的日程， 要求会
*   议室进行 的宣讲的场次最多。 返回这个最多的宣讲场次。
 * 
 * 贪心策略：选择当前结束时间最早的项目，跳过冲突项目。
 * 
 * 实现结构：有序数组
 * 
 * @author Yanjie
 *
 */
public class Code_06_BestArrange {

	/**
	 * 项目实体类
	 * 
	 * @author Yanjie
	 *
	 */
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	/**
	 * 项目比较器，依据结束时间排序。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	/**
	 * 求解
	 * 
	 * @author Yanjie
	 *
	 * @param programs 项目
	 * @param start 开始时间
	 * @return
	 */
	public static int bestArrange(Program[] programs, int start) {
		
		// 依据项目结束时间排序
		Arrays.sort(programs, new ProgramComparator());
		
		int totalProgram = 0;
		int lastProgramEnd = start;
		for (int i = 0; i < programs.length; i++) {
			if (lastProgramEnd <= programs[i].start) {
				totalProgram++;
				start = programs[i].end;
			}
			// 跳过冲突项目
		}
		return totalProgram;
	}

	public static void main(String[] args) {
		
		
	}

}
