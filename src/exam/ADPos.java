package exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ADPos {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\exam\\ADPos"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Integer> input = new ArrayList<>();
		
		while(in.hasNextInt()) {
			input.add(in.nextInt());
		}
		
		int n = input.get(input.size() - 1);
		input.remove(input.size() - 1);
		
		process(input, n);
		
		in.close();
	}
	
	private static void process(List<Integer> housePos, int n) {
		Map<Integer, Integer> distance = new HashMap<>(housePos.size());
		int result = 0;		
		
		// 计算房屋间距
		for (int i = 1; i < housePos.size(); i++) {
			distance.put(i, housePos.get(i) - housePos.get(i - 1));
		}
		
		// 前n - 1个最大间距处分割
		List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(distance.entrySet());
		entryList.sort((Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) -> {
			if (o2.getValue() - o1.getValue() != 0) {
				return o2.getValue() - o1.getValue();
			} else {
				return o2.getKey() - o1.getKey();
			}
		});
		entryList = entryList.subList(0, n - 1);
		entryList.sort((Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)->
			{return o1.getKey() -o2.getKey();});
		
		int lastSplitPos = 0;
		for (int i = 0; i <= n - 1; i++) {
			int splitPos = 0;
			if (i == n - 1) {
				splitPos = housePos.size();
			} else {
				splitPos = entryList.get(i).getKey();
			}
			
			int sum = 0;
			for (int j = lastSplitPos; j <= splitPos - 1; j++) {		
				sum += housePos.get(j);
			}
			
			// 放置位置向下取整
			int ava = sum / (splitPos - lastSplitPos);
			
			// 重新计算距离
			for (int j = lastSplitPos; j <= splitPos - 1; j++) {		
				result += Math.abs(housePos.get(j) - ava);
			}
			lastSplitPos = splitPos;
		}
		
		System.out.println(result);
	}
	
	
}
