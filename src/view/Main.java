package view;

import controller.TreeController;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AVLTree;
import model.BST;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{

//    BST<Integer> bst = new BST<>();
//    bst.insert(10);
//    bst.insert(16);
//    bst.insert(7);
//    bst.insert(12);
//    bst.insert(19);
//    bst.insert(2);
//    bst.insert(4);
//    bst.insert(25);

    AVLTree<Integer> avlTree = new AVLTree<>();
    avlTree.insert(10);
    avlTree.insert(16);
    avlTree.insert(7);
    avlTree.insert(19);
    avlTree.insert(2);
    avlTree.insert(4);

    TreeController treeController = new TreeController(avlTree);

    // Each time add a new Node use getListNodeChanged
    avlTree.insert(25);
    treeController.getListNodeChanged().forEach((k, v) -> {
      System.out.println(k.element.toString());
    });

    // After animate use this to update new position for treeView
    treeController.updateTreeView();

    Group root = new Group();

    treeController.treeView.forEach((node, cir) -> {
      root.getChildren().add((Node) cir);
    });

    primaryStage.setTitle("Visualize Tree Algorithms");
    primaryStage.setScene(new Scene(root, 1000, 600));



    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
