package controller;

import javafx.scene.shape.Line;
import model.BST;
import model.Node;
import view.CircleNode;

import java.util.HashMap;

public class TreeController {
  public HashMap<Node, CircleNode> treeView = new HashMap<>();
  
  // For test
  private final double CENTER_X = 500;
  private final double CENTER_Y = 50;
  private final double V_GAP = 50;
  private final double H_GAP = 240;

  public void createTreeView(BST tree) {
    this.treeView.clear();
    if (tree.root != null) {
      createTreeView(tree.root, this.CENTER_X, this.CENTER_Y, this.H_GAP);
    }
  }
  
  public void createTreeView(Node current, double centerX, double centerY, double hGap) {
    if (current.left != null) {
      createTreeView(current.left, centerX - hGap, centerY + V_GAP, hGap / 2);
    }

    if (current.right != null) {
      createTreeView(current.right, centerX + hGap, centerY + V_GAP, hGap / 2);
    }

    CircleNode cir = new CircleNode(current.element.toString(), centerX, centerY);

    this.treeView.put(current, cir);
  }
}
