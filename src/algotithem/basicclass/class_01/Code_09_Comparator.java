package algotithem.basicclass.class_01;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 为自定义数据类型 设计比较器、实现Comparable接口，以实现<br>
 * 		1.使用系统API　Arrays.sort方法 <em>升序 排序<br>
 * 		2.使用PriorityQueue实现堆结构<br>
 * 		3.使用TreeMap实现红黑树
 * 
 * @author Yanjie
 *
 */
public class Code_09_Comparator {

	/**
	 * 自定义数据类型，Student，可以通过实现Comparable接口，在内部实现比较规则。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class Student implements Comparable<Student>{
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}

		/**
		 * 规则：
		 * 		返回 负数：第一个 < 第二个
		 * 		返回    0 ：==
		 * 		返回 正数：第一个 > 第二个
		 * 
		 * 该实现为ID升序
		 */
		@Override
		public int compareTo(Student o) {
			return this.id - o.id;
		}
	}

	/**
	 * 设计Comparator实现类，即比较器，在对象外部实现比较规则，当这个对象不支持自比较或者自比较函数不能满足你的要求时使用。
	 * 
	 * ps：
	 * 用 Comparator 是策略模式（strategy design pattern），
	 * 就是不改变对象自身，而用一个策略对象（strategy object）来改变它的行为。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class IdAscendingComparator implements Comparator<Student> {

		/**
		 * compare方法规则：
		 * 		返回 负数：第一个 < 第二个
		 * 		返回    0 ：==
		 * 		返回 正数：第一个 > 第二个
		 * 
		 * 该实现为ID降序
		 */
		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}

	}
	

	public static void main(String[] args) {
		Student student1 = new Student("A", 1, 23);
		Student student2 = new Student("B", 2, 21);
		Student student3 = new Student("C", 3, 22);

		System.out.println("原始数组");
		Student[] students = new Student[] { student3, student2, student1 };
		printStudents(students);

		System.out.println("使用Comparable接口提供的自比较");
		Arrays.sort(students);
		printStudents(students);

		System.out.println("使用外部比较器");
		Arrays.sort(students, new IdDescendingComparator());
		printStudents(students);

		System.out.println("使用Lambda定义比较器");
		Arrays.sort(students, (s1, s2) -> {return s1.id - s2.id;});
		printStudents(students);
		
		System.out.println("使用匿名类");
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.id - o2.id;
			}
		});
		printStudents(students);

	}
	
	
	/**
	 * 更多的比较器实现，依据ID倒序
	 * 
	 * @author Yanjie
	 *
	 */
	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}

	}

	public static class AgeAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}

	}

	public static class AgeDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;
		}

	}

	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
		System.out.println("===========================");
	}

}
