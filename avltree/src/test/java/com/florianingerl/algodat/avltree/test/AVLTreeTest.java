package com.florianingerl.algodat.avltree.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		for (int e : tree) {
			sb.append(e + ",");
		}
		assertEquals("2,1,0,5,3,7,", sb.toString());

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
		for (int e : tree) {
			sb.append(e + ",");
		}
		assertEquals("0,1,3,7,5,2,", sb.toString());

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
		for (int e : tree) {
			sb.append(e + ",");
		}
		assertEquals("0,1,2,3,5,7,", sb.toString());

	}

	@Test
	public void testContains() {
		AVLTree<Integer> tree = new AVLTree<Integer>();

		assertTrue(tree.add(2));
		assertTrue(tree.add(1));
		assertTrue(tree.add(0));
		assertTrue(tree.add(5));
		assertTrue(tree.add(3));
		assertTrue(tree.add(7));
		assertFalse(tree.add(7));
		assertFalse(tree.add(2));
		assertFalse(tree.add(5));
		assertFalse(tree.add(1));
		assertFalse(tree.add(3));

		assertTrue(tree.contains(3));
		assertFalse(tree.contains(11));
		assertTrue(tree.contains(2));

	}

	@Test
	public void removeTest() {
		AVLTree<Integer> tree = new AVLTree<Integer>();

		tree.add(2);
		tree.add(1);
		tree.add(0);
		tree.add(5);
		tree.add(3);
		tree.add(7);
		
		assertTrue(tree.remove(0));
		assertFalse(tree.contains(0));
		assertTrue(tree.add(0));
		
		assertTrue(tree.remove(3));
		assertFalse(tree.contains(3));
		assertTrue(tree.add(3));
		
		assertTrue(tree.remove(7));
		assertFalse(tree.contains(7));
		assertTrue(tree.add(7));
		
		assertTrue(tree.remove(1));
		assertFalse(tree.contains(1));
		assertTrue(tree.add(1));
		
		assertTrue(tree.remove(5));
		assertFalse(tree.contains(5));
		assertTrue(tree.add(5));
		
		assertTrue(tree.remove(2));
		assertFalse(tree.contains(2));
		assertTrue(tree.add(2));
	}

}
