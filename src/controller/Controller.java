package controller;

import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.shape.Line;
import model.AVLTree;
import model.BST;
import model.RBTree;
import view.CircleNode;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
  @FXML
  private TextField InputValue;
  @FXML
  private TextArea Console;

  private TreeController treeControllerForRBT;
  private TreeController treeControllerForBST;
  private TreeController treeControllerForAVL;
  private TreeController treeControllerForBSTAfterHandle;
  private String typeTree;
  private int currValue;
  public Group root;
  public ArrayList<String> list = new ArrayList<String>();


  public boolean isNumeric(String strNum) {
    if (strNum == null)
      return false;
    try {
      currValue = Integer.parseInt(strNum);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  public String extractInputValue() {
    try {
      String value = InputValue.getText();
      InputValue.clear();
      if (isNumeric(value))
        return value;
    } catch (NumberFormatException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Error type value. The input field can only accept numbers.",
              ButtonType.OK);

      alert.showAndWait()
              .filter(response -> response == ButtonType.OK)
              .ifPresent(response -> alert.close());
    }
    return null;
  }

  @FXML
  public void handleInsertButton() {
    String value = extractInputValue();
    if (value != null) {
      // add value to list to print in console area
      if (this.list.size() == 1)
        Console.appendText(" \n");
      //insert
      if (this.typeTree != null) {
        switch (typeTree) {
          case "AVL":
            this.insertAVL();
            break;
          case "BST":
            this.insertBST();
            break;
          case "RBT":
            this.insertRBT();
            break;
        }

        if (!list.contains(value)) {
          Console.appendText("Inserted " + value + "\n");
          list.add(value);
          printList();
        }
      }
      //catch (NullPointerException e)
      else {
        Alert alert = new Alert(Alert.AlertType.ERROR, "You must select type of tree before.",
                ButtonType.OK);

        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
      }
    }
  }

  private void insertAVL() {
    this.treeControllerForAVL.tree.insert(currValue);
    this.treeControllerForBST.tree.insert(currValue);

    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    // Create treeView from treeNode
    treeControllerForAVL.updateTreeView();
    treeControllerForBST.updateTreeView();

    // Clear all lines on the screen
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    //  Display lines again
    treeControllerForAVL.displayLines(root);

    // Display BST Tree to screen
    treeControllerForBST.displayCircle(root);

    // Run rotate animation on BST Tree
    ParallelTransition insertAnima = treeControllerForBST.createAnimationHandleInsert(root, treeControllerForAVL.treeView);

    // Play rotate animation
    insertAnima.play();

    // Reassign the last AVLTree to BSTTree to continue
    treeControllerForBST.tree = treeControllerForAVL.tree.cloneTree();
  }

  private void insertRBT() {
    // Add value to tree
    treeControllerForRBT.tree.insert(currValue);
    treeControllerForBST.tree.insert(currValue);

    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    // Create treeView from treeNode
    treeControllerForRBT.updateTreeView();
    treeControllerForBST.updateTreeView();

    // Clear all lines on the screen
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    // Display BST Line to screen
    treeControllerForRBT.displayLines(root);

    // Create treeViewColorForBST
    treeControllerForBST.createTreeViewColor((RBTree) treeControllerForRBT.tree);
    // Display BST Tree to screen
    treeControllerForBST.displayCircle(root);

    // Run rotate animation on BST Tree
    ParallelTransition insertAnima = treeControllerForBST.createAnimationHandleInsert(root, treeControllerForRBT.treeView);

    // Play rotate animation
    insertAnima.play();

    // Reassign the last RBTree to BSTTree to continue
    treeControllerForBST.tree = treeControllerForRBT.tree.cloneTree();
  }

  private void insertBST() {
    // Add value to tree
    treeControllerForBSTAfterHandle.tree.insert(currValue);
    treeControllerForBST.tree.insert(currValue);

    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    // Create treeView from treeNode
    treeControllerForBSTAfterHandle.updateTreeView();
    treeControllerForBST.updateTreeView();

    // Clear all lines on the screen
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    //  Display lines again
    treeControllerForBST.displayLines(root);

    // Display BST Tree to screen
    treeControllerForBST.displayCircle(root);

    // Run rotate animation on BST Tree
    ParallelTransition insertAnima = treeControllerForBST.createAnimationHandleInsert(root, treeControllerForBST.treeView);

    // Play rotate animation
    insertAnima.play();

    // Reassign the last AVLTree to BSTTree to continue
    treeControllerForBST.tree = treeControllerForBSTAfterHandle.tree.cloneTree();
  }

  @FXML
  public void handleDeleteButton() {
    String value = extractInputValue();
    if (value != null) {
      try {
        if (list.contains(value)) {
          switch (typeTree) {
            case "AVL":
              this.deleteAVL();
              break;
            case "BST":
              this.deleteBST();
              break;
            case "RBT":
              this.deleteRBT();
              break;
          }

          list.remove(value);
          Console.appendText("Deleted " + value + "\n");
          printList();
        }
      } catch (NullPointerException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "You must select type of tree before.",
                ButtonType.OK);

        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
      }
    }
  }

  private void deleteAVL() {
    treeControllerForAVL.tree.delete(currValue);

    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    // Create treeView from treeNode
    treeControllerForAVL.updateTreeView();
    treeControllerForBST.updateTreeView();

    // Clear all lines on the screen
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    //  Display lines again
    treeControllerForAVL.displayLines(root);

    // Display BST Tree to screen
    treeControllerForBST.displayCircle(root);

    // Create animation when delete on BST Tree
    ParallelTransition deleteAnima = treeControllerForBST.createAnimationHandleDelete(root, treeControllerForAVL.treeView);

    // Play rotate animation
    deleteAnima.play();

    // Reassign the last AVLTree to BSTTree to continue
    treeControllerForBST.tree = treeControllerForAVL.tree.cloneTree();
  }

  private void deleteRBT() {
    // Delete element from tree
    treeControllerForRBT.tree.delete(currValue);

    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    // Create treeView from treeNode
    treeControllerForRBT.updateTreeView();
    treeControllerForBST.updateTreeView();

    // Clear all lines on the screen
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    //  Display lines again
    treeControllerForRBT.displayLines(root);

    // Create treeViewColorForBST
    treeControllerForBST.createTreeViewColor((RBTree) treeControllerForRBT.tree);
    // Display BST Tree to screen
    treeControllerForBST.displayCircle(root);

    // Create animation when delete on BST Tree
    ParallelTransition deleteAnima = treeControllerForBST.createAnimationHandleDelete(root, treeControllerForRBT.treeView);

    // Play rotate animation
    deleteAnima.play();

    // Reassign the last RBTree to BSTTree to continue
    treeControllerForBST.tree = treeControllerForRBT.tree.cloneTree();
  }

  private void deleteBST() {
    // Delete element from tree
    treeControllerForBSTAfterHandle.tree.delete(currValue);

    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    // Create treeView from treeNode
    treeControllerForBSTAfterHandle.updateTreeView();

    // Clear all lines on the screen
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    //  Display lines again
    treeControllerForBSTAfterHandle.displayLines(root);

    // Display BST Tree to screen
    treeControllerForBST.displayCircle(root);

    // Create animation when delete on BST Tree
    ParallelTransition deleteAnima = treeControllerForBST.createAnimationHandleDelete(root, treeControllerForBSTAfterHandle.treeView);

    // Play rotate animation
    deleteAnima.play();

    // Reassign the last AVLTree to BSTTree to continue
    treeControllerForBST.tree = treeControllerForBSTAfterHandle.tree.cloneTree();
  }

  @FXML
  public void handleClearButton() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Clear");
    alert.setContentText("Do you want to empty current tree?");
    ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
    alert.getButtonTypes().setAll(okButton, noButton);
    alert.showAndWait().ifPresent(type -> {
      if (type.getText().equals("Yes")) {
        clearTree();
      } else if (type.getText().equals("No")) {
      }
    });
  }

  private void clearTree() {
    // clear all nodes in tree
    root.getChildren().clear();
    this.typeTree = null;
    Console.setText("Message Dialog: ");
    Console.appendText("\n");
    list.clear();
  }

  @FXML
  public void handleFindButton() {
    String value = extractInputValue();
    if (value != null) {
      try {
        switch (typeTree) {
          case "AVL": {
            this.findAVL();
            break;
          }
          case "BST": {
            this.findBST();
            break;
          }
          case "RBT": {
            this.findRBT();
            break;
          }
        }
        printList();
      } catch (NullPointerException e) {
        if (this.typeTree == null) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "You must select type of tree before.",
                  ButtonType.OK);

          alert.showAndWait()
                  .filter(response -> response == ButtonType.OK)
                  .ifPresent(response -> alert.close());
        } else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION, "Can not find the value.",
                  ButtonType.OK);

          alert.showAndWait()
                  .filter(response -> response == ButtonType.OK)
                  .ifPresent(response -> alert.close());
        }
      }
    }
  }

  private void findAVL() {
    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    treeControllerForAVL.updateTreeView();
    treeControllerForAVL.displayLines(root);
    treeControllerForAVL.displayCircle(root);
    treeControllerForAVL.createAnimationOnSearchTree(root, currValue).play();
  }

  private void findRBT() {
    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    treeControllerForRBT.updateTreeView();
    treeControllerForRBT.displayLines(root);

    // Create treeViewColorForBST
    treeControllerForBST.createTreeViewColor((RBTree) treeControllerForRBT.tree);
    treeControllerForRBT.displayCircle(root);
    treeControllerForRBT.createAnimationOnSearchTree(root, currValue).play();
  }

  private void findBST() {
    // Remove the last tree to paint again
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof Line));
    root.getChildren().removeAll(root.getChildren().filtered(el -> el instanceof CircleNode));

    treeControllerForBST.updateTreeView();
    treeControllerForBST.displayLines(root);
    treeControllerForBST.displayCircle(root);
    treeControllerForBST.createAnimationOnSearchTree(root, currValue).play();
  }

  @FXML
  private void setAvlTree() {
    BST<Integer> bst = new BST<>();
    treeControllerForBST = new TreeController(bst);

    if (this.typeTree == null) {
      this.typeTree = "AVL";
      AVLTree<Integer> avlTree = new AVLTree<>();
      treeControllerForAVL = new TreeController(avlTree);
      Console.appendText("\n");
    } else {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Clear");
      alert.setContentText("Do you want to empty current tree?");
      ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
      ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
      alert.getButtonTypes().setAll(okButton, noButton);
      alert.showAndWait().ifPresent(type -> {
        if (type.getText().equals("Yes")) {
          clearTree();
          this.typeTree = "AVL";
          AVLTree<Integer> avlTree = new AVLTree<>();
          treeControllerForAVL = new TreeController(avlTree);
        } else if (type.getText().equals("No")) {
          return;
        }
      });
    }
  }

  @FXML
  private void setRedBlackTree() {
    BST<Integer> bst = new BST<>();
    treeControllerForBST = new TreeController(bst);

    if (this.typeTree == null) {
      this.typeTree = "RBT";
      RBTree<Integer> rbTree = new RBTree<>();
      treeControllerForRBT = new TreeController(rbTree);
      Console.appendText("\n");
    } else {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Clear");
      alert.setContentText("Do you want to empty current tree?");
      ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
      ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
      alert.getButtonTypes().setAll(okButton, noButton);
      alert.showAndWait().ifPresent(type -> {
        if (type.getText().equals("Yes")) {
          clearTree();
          this.typeTree = "RBT";
          RBTree<Integer> rbTree = new RBTree<>();
          treeControllerForRBT = new TreeController(rbTree);
        } else if (type.getText().equals("No")) {
          return;
        }
      });
    }
  }

  @FXML
  private void setBSTree() {
    BST<Integer> bst = new BST<>();
    treeControllerForBSTAfterHandle = new TreeController(bst);

    if (this.typeTree == null) {
      this.typeTree = "BST";
      BST<Integer> integerBST = new BST<>();
      treeControllerForBST = new TreeController(integerBST);
      Console.appendText("\n");
    } else {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Clear");
      alert.setContentText("Do you want to empty current tree?");
      ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
      ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
      alert.getButtonTypes().setAll(okButton, noButton);
      alert.showAndWait().ifPresent(type -> {
        if (type.getText().equals("Yes")) {
          clearTree();
          this.typeTree = "BST";
          BST<Integer> integerBST = new BST<>();
          treeControllerForBST = new TreeController(integerBST);
        } else if (type.getText().equals("No")) {
          return;
        }
      });
    }
  }

  public void printList() {
    String result = " ";
    for (String x : this.list) {
      result = result + x + " ";
    }

    Console.appendText(result + "\n");
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
