package com.florianingerl.algodat.avltree.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.florianingerl.algodat.avltree.AVLTree;

public class AVLTreeTest {

	@Test
	public void testpreorderTraversal() {
	AVLTree<Integer> tree = new AVLTree<Integer>();
		
		tree.add(2);
		tree.add(1);
		tree.add(0);
		tree.add(5);
		tree.add(3);
		tree.add(7);
		
		StringBuilder sb = new StringBuilder();
		for(int e : tree) {
			sb.append(e + ",");
		}
		assertEquals("2,1,0,5,3,7,", sb.toString() );
		
	}
	
	@Test
	public void testpostorderTraversal() {
	AVLTree<Integer> tree = new AVLTree<Integer>();
		
		tree.add(2);
		tree.add(1);
		tree.add(0);
		tree.add(5);
		tree.add(3);
		tree.add(7);
		
		StringBuilder sb = new StringBuilder();
		
		tree.setIterationMode(AVLTree.POSTORDER);
		for(int e : tree) {
			sb.append(e + ",");
		}
		assertEquals("0,1,3,7,5,2,", sb.toString() );
		
	}
	
	@Test
	public void testinorderTraversal() {
	AVLTree<Integer> tree = new AVLTree<Integer>();
		
		tree.add(2);
		tree.add(1);
		tree.add(0);
		tree.add(5);
		tree.add(3);
		tree.add(7);
		
		StringBuilder sb = new StringBuilder();
		
		tree.setIterationMode(AVLTree.INORDER);
		for(int e : tree) {
			sb.append(e + ",");
		}
		assertEquals("0,1,2,3,5,7,", sb.toString() );
		
	}
	
	
	
	
	
}
