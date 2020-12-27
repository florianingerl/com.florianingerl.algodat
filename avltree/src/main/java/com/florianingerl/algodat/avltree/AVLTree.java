package com.florianingerl.algodat.avltree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<E extends Comparable<E>> implements Iterable<E> {

	private static class AVLNode<E extends Comparable<E>> {
		AVLNode<E> left;
		AVLNode<E> right;
		AVLNode<E> parent;
		int depth;
		E value;

		AVLNode(AVLNode<E> parent, E e) {
			this.parent = parent;
			this.value = e;
			depth = 1;
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

	private int depth(AVLNode<E> node) {
		if(node == null) return 0;
		return node.depth;
	}
	
	private boolean add(AVLNode<E> node, E e) {
		if (node.value.compareTo(e) == 0)
			return false;
		if (node.value.compareTo(e) > 0) {
			if (node.left == null) {
				node.left = new AVLNode<E>(node, e);
				return true;
			}
			if (add(node.left, e)) {
				node.depth = 1 + Math.max( depth(node.left), depth(node.right) );
				int balance = depth(node.right) - depth(node.left);
				if(balance == -2) {
					if(node.parent == null)
						root = node.left;
					else if(node.parent.left == node)
						node.parent.left = node.left;
					else
						node.parent.right = node.left;
					node.left.parent = node.parent;
					node.left.right = node;
					node.parent = node.left;
					if(node.left.right!=null)
						node.left.right.parent = node;
					node.left = node.left.right;
					node.depth -= 2;
				}
				return true;
			} else {
				return false;
			}
		}
		if (node.right == null) {
			node.right = new AVLNode<E>(node, e);
			return true;
		}
		if(add(node.right, e) ) {
			node.depth = 1 + Math.max(depth(node.left), depth(node.right) );
			int balance = depth(node.right) - depth(node.left);
			if(balance == 2) {
				if(node.parent == null) {
					root = node.right;
				}
				else if(node.parent.left == node) {
					node.parent.left = node.right;
				}
				else {
					node.parent.right = node.right;
				}
				node.right.parent = node.parent;
				node.right.left = node;
				node.parent = node.right;
				if(node.right.left != null) {
					node.right.left.parent = node;
				}
				node.right = node.right.left;
				node.depth -= 2;
			}
			return true;
		}
		return false;
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
			if (node.parent != null) {
				if (node.parent.left == node)
					node.parent.left = null;
				else
					node.parent.right = null;
			} else {
				root = null;
			}
			return;
		}

		if (node.left != null) {
			E z = node.left.value;
			node.left.value = node.value;
			node.value = z;
			removeThis(node.left);
		} else {
			E z = node.right.value;
			node.right.value = node.value;
			node.value = z;
			removeThis(node.right);
		}
	}

}
