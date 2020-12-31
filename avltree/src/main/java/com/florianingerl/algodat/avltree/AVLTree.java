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
	private int size = 0;
	
	private int iterationMode = INORDER;

	public static final int PREORDER = 0;
	public static final int POSTORDER = 1;
	public static final int INORDER = 2;

	private boolean rotated = false;
	
	public boolean add(E e) {
		if (root == null) {
			root = new AVLNode<E>(null, e);
			size++;
			return true;
		}
		rotated = false;
		if(add(root,e)) {
			size++;
			return true;
		}
		return false;
	}
	
	public void add(E... all) {
		for(E e : all) {
			add(e);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(E e : this) {
			if(first) {
				first = false;
			}
			else {
				sb.append(",");
			}
			sb.append(e);
		}
		return sb.toString();
	}
	
	//This is the maximum number of edges from the root to a leaf
	public int depth() {
		if(root == null)
			return 0;
		return depth(root) - 1;
	}

	private int depth(AVLNode<E> node) {
		if(node == null) return 0;
		return 1 + Math.max(depth(node.left), depth(node.right));
	}
	
	private int getBalance(AVLNode<E> node) {
		return depth(node.right) - depth(node.left);
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
				if(!rotated && getBalance(node) == -2) {
					if(getBalance(node.left) == -1) {
						rotateRight(node);
					}
					else {
						rotateLeft(node.left);
						rotateRight(node);
					}
					rotated = true;
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
			if(!rotated && getBalance(node) == 2) {
				if(getBalance(node.right) == 1) {
					rotateLeft(node);
				}
				else {
					rotateRight(node.right);
					rotateLeft(node);
				}
				rotated = true;
			}
			return true;
		}
		return false;
	}
	
	private void rotateLeft(AVLNode<E> node) {
		if(node.parent == null)
			root = node.right;
		else if(node.parent.left == node)
			node.parent.left = node.right;
		else
			node.parent.right = node.right;
		node.right.parent = node.parent;
		node.right.left = node;
		node.parent = node.right;
		AVLNode<E> n = node.right.left;
		node.right.left = node;
		node.right = n;
		if(n!=null) n.parent = node;
		computeDepth(node);
		computeDepth(node.parent);
	}
	
	private void rotateRight(AVLNode<E> node) {
		if(node.parent == null)
			root = node.left;
		else if(node.parent.left == node)
			node.parent.left = node.left;
		else
			node.parent.right = node.left;
		node.left.parent = node.parent;
		node.left.right = node;
		node.parent = node.left;
		AVLNode<E> n = node.left.right;
		node.left.right = node;
		node.left = n;
		if(n!=null) n.parent = node;
		computeDepth(node);
		computeDepth(node.parent);
	}
	
	private void computeDepth(AVLNode<E> node) {
		node.depth =  1 + Math.max(depth(node.left), depth(node.right));
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
		if( remove(root, e) ) {
			--size;
			return true;
		}
		return false;
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
