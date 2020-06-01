package controller;

import model.BST;
import model.Node;
import view.CircleNode;

import java.util.HashMap;

public class TreeController {
  public HashMap<Node, CircleNode> treeView;
  private BST tree;

  // For test
  private final double CENTER_X = 500;
  private final double CENTER_Y = 50;
  private final double V_GAP = 50;
  private final double H_GAP = 240;

  public TreeController(BST tree) {
    this.treeView = createTreeView(tree);
    this.tree = tree;
  }

  private HashMap<Node, CircleNode> createTreeView(BST tree) {
    HashMap<Node, CircleNode> treeView = new HashMap<>();
    if (tree.root != null) {
      createTreeView(treeView, tree.root, this.CENTER_X, this.CENTER_Y, this.H_GAP);
    }
    return treeView;
  }

  private void createTreeView(HashMap treeView, Node current, double centerX, double centerY, double hGap) {
    if (current.left != null) {
      createTreeView(treeView, current.left, centerX - hGap, centerY + V_GAP, hGap / 2);
    }

    if (current.right != null) {
      createTreeView(treeView, current.right, centerX + hGap, centerY + V_GAP, hGap / 2);
    }

    CircleNode cir = new CircleNode(current.element.toString(), centerX, centerY);
    treeView.put(current, cir);
  }

  public void updateTreeView() {
    this.treeView = createTreeView(this.tree);
  }

  // Function return HashMap of changed Node and CircleNode
  public HashMap<Node, CircleNode> getListNodeChanged() {
    HashMap<Node, CircleNode> list = new HashMap<>();
    HashMap<Node, CircleNode> newTreeView = createTreeView(this.tree);

    for (Node key : newTreeView.keySet()) {
      if (newTreeView.get(key).compareTo(this.treeView.get(key)) != 0) {
        list.put(key, newTreeView.get(key));
      }
    }
    return list;
  }
}
