package com.florianingerl.algodat.avltree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<E extends Comparable<E>> implements Iterable<E> {

	private static class AVLNode<E extends Comparable<E>> {
		AVLNode<E> left;
		AVLNode<E> right;
		AVLNode<E> parent;
		E value;

		AVLNode(AVLNode<E> parent, E e) {
			this.parent = parent;
			this.value = e;
		}
	}

	private AVLNode<E> root;
	private int iterationMode = PREORDER;

	public static final int PREORDER = 0;
	public static final int POSTORDER = 1;
	public static final int INORDER = 2;

	public boolean add(E e) {
		if (root == null) {
			root = new AVLNode<E>(null, e);
			return true;
		}
		return add(root, e);
	}

	private boolean add(AVLNode<E> node, E e) {
		if (node.value.compareTo(e) == 0)
			return false;
		if (node.value.compareTo(e) > 0) {
			if (node.left == null) {
				node.left = new AVLNode<E>(node, e);
				return true;
			}
			return add(node.left, e);
		}
		if (node.right == null) {
			node.right = new AVLNode<E>(node, e);
			return true;
		}
		return add(node.right, e);
	}

	public boolean contains(E e) {
		return contains(root, e);
	}

	private boolean contains(AVLNode<E> node, E e) {
		if (node == null)
			return false;
		if (node.value.compareTo(e) == 0)
			return true;
		if (node.value.compareTo(e) > 0)
			return contains(node.left, e);
		return contains(node.right, e);

	}

	public void setIterationMode(int iterationMode) {
		this.iterationMode = iterationMode;
	}

	public Iterator<E> iterator() {
		List<E> l = new LinkedList<E>();
		if (iterationMode == PREORDER)
			collectPreorder(root, l);
		else if (iterationMode == POSTORDER)
			collectPostorder(root, l);
		else if (iterationMode == INORDER)
			collectInorder(root, l);
		return l.iterator();
	}

	private void collectPreorder(AVLNode<E> node, List<E> l) {
		if (node == null)
			return;
		l.add(node.value);
		collectPreorder(node.left, l);
		collectPreorder(node.right, l);
	}

	private void collectPostorder(AVLNode<E> node, List<E> l) {
		if (node == null)
			return;
		collectPostorder(node.left, l);
		collectPostorder(node.right, l);
		l.add(node.value);
	}

	private void collectInorder(AVLNode<E> node, List<E> l) {
		if (node == null)
			return;
		collectInorder(node.left, l);
		l.add(node.value);
		collectInorder(node.right, l);
	}

	public boolean remove(E e) {
		return remove(root, e);
	}

	private boolean remove(AVLNode<E> node, E e) {
		if (node == null)
			return false;
		if (node.value.compareTo(e) == 0) {
			removeThis(node);
			return true;
		} else if (node.value.compareTo(e) > 0) {
			return remove(node.left, e);
		} else {
			return remove(node.right, e);
		}
	}

	private void removeThis(AVLNode<E> node) {
		if (node.left == null && node.right == null) {
			if (node.parent.left == node)
				node.parent.left = null;
			else
				node.parent.right = null;
			return;
		}

		if (node.left != null) {
			AVLNode<E> l = node.left.left;
			AVLNode<E> r = node.left.right;
			node.left.left = node;
			node.left.right = node.right;
			if (node.right != null)
				node.right.parent = node.left;
			node.left.parent = node.parent;
			if (node.parent != null) {
				if (node.parent.left == node)
					node.parent.left = node.left;
				else
					node.parent.right = node.left;
			} else {
				root = node.left;
			}
			node.parent = node.left;
			node.left = l;
			if (l != null)
				l.parent = node;
			node.right = r;
			if (r != null)
				r.parent = node;
			removeThis(node);
		} else {
			AVLNode<E> l = node.right.left;
			AVLNode<E> r = node.right.right;
			node.right.right = node;
			node.right.left = node.left;
			if (node.left != null)
				node.left.parent = node.right;
			node.right.parent = node.parent;
			if (node.parent != null) {
				if (node.parent.left == node)
					node.parent.left = node.right;
				else
					node.parent.right = node.right;
			}
			else {
				root = node.right;
			}
			node.parent = node.right;
			node.left = l;
			if (l != null)
				l.parent = node;
			node.right = r;
			if (r != null)
				r.parent = node;
			removeThis(node);
		}
	}
}
