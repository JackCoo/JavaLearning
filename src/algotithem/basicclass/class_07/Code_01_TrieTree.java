package algotithem.basicclass.class_07;

/**
 * 前缀树（Trie树、单词查询树） 适用于26小写字母
 * 用于快速检索的多叉树结构，利用公共前缀节约存储空间。
 * 实现功能：
 * 		插入、删除
 * 		特定字符串出现的次数
 * 		特定前缀个数
 * 
 * @author Yanjie
 *
 */
public class Code_01_TrieTree {

	public static class TrieNode {

		// 以此为前缀的字符串数
		public int path;

		// 以此结束的字符串数（特定字符串被插入的次数）
		public int end;

		// 下一个节点
		public TrieNode[] nexts;

		public TrieNode() {
			path = 0;
			end = 0;
			
			// 每个节点有26条路径，index依次代表26个字母。
			nexts = new TrieNode[26];
		}
	}

	public static class Trie {
		
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		/**
		 * 插入字符串
		 * 
		 * @author Yanjie
		 *
		 * @param word
		 */
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			
			// 将字符沿路径插入前缀树，更新path和end变量
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new TrieNode();
				}
				node = node.nexts[index];
				node.path++;
			}
			node.end++;
		}

		/**
		 * 删除字符串
		 * 
		 * @author Yanjie
		 *
		 * @param word
		 */
		public void delete(String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				
				// 对word路径上的path--，删除path=0及其以后的节点（不是公共路径）。
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (--node.nexts[index].path == 0) {
						
						// 不会造成对象游离
						node.nexts[index] = null;
						return;
					}
					node = node.nexts[index];
				}
				
				node.end--;
			}
		}

		/**
		 * 搜索特定字符串出现（被插入）的次数，即返回其end值。
		 * 
		 * @author Yanjie
		 *
		 * @param word
		 * @return
		 */
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		/**
		 * 查询以pre为前缀的字符串数量，即返回节点的path值。
		 * 
		 * @author Yanjie
		 *
		 * @param pre
		 * @return
		 */
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}

}
