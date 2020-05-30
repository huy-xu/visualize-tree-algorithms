package view;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.BST;
import model.Node;

public class BSTPane<T extends Comparable<T>> extends Pane {
  private BST<T> tree;
  private final double V_GAP = 50;
  private double centerX;
  private double centerY = V_GAP;
  private double hGap;


  public BSTPane(BST<T> tree, double centerX, double hGap) {
    this.tree = tree;
    this.centerX = centerX;
    this.hGap = hGap;
    this.displayTree();
  }

  public void displayTree() {
    this.getChildren().clear();
    if (tree.root != null) {
      displayTree(tree.root, this.centerX, this.centerY, this.hGap);
    }
  }

  public void displayTree(Node<T> current, double centerX, double centerY, double hGap) {
    if (current.left != null) {
      this.getChildren().add(new Line(centerX - hGap, centerY + V_GAP, centerX, centerY));
      displayTree(current.left, centerX - hGap, centerY + V_GAP, hGap / 2);
    }

    if (current.right != null) {
      this.getChildren().add(new Line(centerX + hGap, centerY + V_GAP, centerX, centerY));
      displayTree(current.right, centerX + hGap, centerY + V_GAP, hGap / 2);
    }

    CircleNode cir = new CircleNode(current.element.toString(), centerX, centerY);
    getChildren().add(cir);
  }
}
