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
import model.BST;

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
    bst.insert(9);
    bst.insert(8);

//    BSTPane<Integer> bstPane = new BSTPane<>(bst, 500, 250);
//    bstPane.displayTree();

    TreeController treeController = new TreeController();
    treeController.createTreeView(bst);

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
