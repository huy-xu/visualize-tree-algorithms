package model;

import static model.Constants.WHITE;

public class Node<T extends Comparable<T>> {
  public T element;
  public Node<T> left;
  public Node<T> right;
  public Node<T> parent;
  public int height;
  public int color;

  public Node(T element) {
    this.element = element;
    left = null;
    right = null;
    parent = null;
    height = 1;
    color = WHITE;
  }
}
