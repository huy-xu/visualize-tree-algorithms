package view;

import controller.TreeController;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.RBTree;
import model.BST;
import model.Node;

import java.util.HashMap;
import java.util.Scanner;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    BST<Integer> bst = new BST<>();
    bst.insert(10);
    bst.insert(16);
    bst.insert(7);
    bst.insert(12);
    bst.insert(19);
    bst.insert(2);
    bst.insert(4);
    bst.insert(25);

    RBTree<Integer> rbTree = new RBTree<>();
    rbTree.insert(10);
    rbTree.insert(16);
    rbTree.insert(7);
    rbTree.insert(19);
    rbTree.insert(2);
    rbTree.insert(4);

    TreeController treeController = new TreeController(rbTree);
    Group root = new Group();
    treeController.displayTree(root);

    primaryStage.setTitle("Visualize Tree Algorithms");
    primaryStage.setScene(new Scene(root, 1000, 600));

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}