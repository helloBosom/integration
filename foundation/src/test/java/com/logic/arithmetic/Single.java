package com.logic.arithmetic;

public class Single {
	private static Single onlyone = new Single();

	public static Single getSingle() {
		return onlyone;
	}

	public static void main(String[] args) {
		// 引用类型,并没有开辟新的内存空间
		Single s1 = Single.getSingle();
		Single s2 = Single.getSingle();
		if (s1 == s2) {
			System.out.println("s1 is equals to s2!");
		}
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1.equals(s2));
	}
}
