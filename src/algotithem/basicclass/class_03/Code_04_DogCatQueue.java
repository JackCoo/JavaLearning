package algotithem.basicclass.class_03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列：实现一个可以容纳两种不同元素的队列，可以按照类型顺序弹出，也可以忽略类型顺序弹出
 * 
 * 双队列 + 封装入列元素（加时间戳）：按照类型入相应队列，入列前封装入列元素；时间戳用以比较不同队列入列先后顺序。
 * 类似装饰器模式？
 * 
 * @author Yanjie
 *
 */
public class Code_04_DogCatQueue {

	/**
	 * 猫狗父类
	 * 
	 * @author Yanjie
	 *
	 */
	public static class Pet {
		private String type;

		public Pet(String type) {
			this.type = type;
		}

		public String getPetType() {
			return this.type;
		}
	}

	/**
	 * 狗
	 * @author Yanjie
	 *
	 */
	public static class Dog extends Pet {
		public Dog() {
			super("dog");
		}
	}

	/**
	 * 猫
	 * 
	 * @author Yanjie
	 *
	 */
	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}
	}

	/**
	 * 队列元素，组合PET，为PET类型增加时间戳。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class PetEnterQueue {
		private Pet pet;
		private long count;

		public PetEnterQueue(Pet pet, long count) {
			this.pet = pet;
			this.count = count;
		}

		public Pet getPet() {
			return this.pet;
		}

		public long getCount() {
			return this.count;
		}

		public String getEnterPetType() {
			return this.pet.getPetType();
		}
	}

	/**
	 * 猫狗双队列
	 * 
	 * @author Yanjie
	 *
	 */
	public static class DogCatQueue {
		private Queue<PetEnterQueue> dogQ;
		private Queue<PetEnterQueue> catQ;
		private long count;

		public DogCatQueue() {
			this.dogQ = new LinkedList<PetEnterQueue>();
			this.catQ = new LinkedList<PetEnterQueue>();
			this.count = 0;
		}

		/**
		 * 入列
		 * 
		 * @author Yanjie
		 *
		 * @param pet
		 */
		public void add(Pet pet) {
			/**
			 * 根据元素类型入相应列，并加上时间戳。
			 */
			if (pet.getPetType().equals("dog")) {
				this.dogQ.add(new PetEnterQueue(pet, this.count++));
			} else if (pet.getPetType().equals("cat")) {
				this.catQ.add(new PetEnterQueue(pet, this.count++));
			} else {
				throw new RuntimeException("err, not dog or cat");
			}
		}

		/**
		 * 忽略类型弹出
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Pet pollAll() {
			if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
				/**
				 * 依据两队列首元素入列时间弹出
				 */
				if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
					return this.dogQ.poll().getPet();
				} else {
					return this.catQ.poll().getPet();
				}
			/**
			 * 有空队列时，弹出非空。
			 */
			} else if (!this.dogQ.isEmpty()) {
				return this.dogQ.poll().getPet();
			} else if (!this.catQ.isEmpty()) {
				return this.catQ.poll().getPet();
			} else {
				throw new RuntimeException("err, queue is empty!");
			}
		}

		/**
		 * 弹出狗元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Dog pollDog() {
			if (!this.isDogQueueEmpty()) {
				return (Dog) this.dogQ.poll().getPet();
			} else {
				throw new RuntimeException("Dog queue is empty!");
			}
		}

		/**
		 * 弹出猫元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Cat pollCat() {
			if (!this.isCatQueueEmpty()) {
				return (Cat) this.catQ.poll().getPet();
			} else
				throw new RuntimeException("Cat queue is empty!");
		}

		public boolean isEmpty() {
			return this.dogQ.isEmpty() && this.catQ.isEmpty();
		}

		public boolean isDogQueueEmpty() {
			return this.dogQ.isEmpty();
		}

		public boolean isCatQueueEmpty() {
			return this.catQ.isEmpty();
		}

	}

	public static void main(String[] args) {
		DogCatQueue test = new DogCatQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);
		while (!test.isDogQueueEmpty()) {
			System.out.println(test.pollDog().getPetType());
		}
		while (!test.isEmpty()) {
			System.out.println(test.pollAll().getPetType());
		}
	}

}
