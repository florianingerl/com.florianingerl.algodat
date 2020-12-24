package com.florianingerl.algodat.avltree;

public class Main {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		
		tree.add(2);
		tree.add(1);
		tree.add(0);
		tree.add(5);
		tree.add(3);
		tree.add(7);
		
		for(int e : tree) {
			System.out.print(e + ",");
		}
		System.out.println();
		
		if(tree.remove(2))
			for(int e : tree) {
				System.out.print(e + ",");
			}
		
		System.out.println();
		
		
		
	}

}
