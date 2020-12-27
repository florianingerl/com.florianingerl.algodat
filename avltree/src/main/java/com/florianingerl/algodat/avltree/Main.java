package com.florianingerl.algodat.avltree;

public class Main {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		
		tree.add(2);
		tree.add(1);
		tree.add(0);
		
		tree.setIterationMode(AVLTree.INORDER);
		for(int e : tree) {
			System.out.print(e + ",");
		}
		System.out.println();
		
		
	}

}
