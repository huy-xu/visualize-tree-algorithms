package view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class CircleNode extends StackPane implements Comparable{
  public String value;
  private final double RADIUS = 20;
  private final Font FONT = Font.font("Cooper Black", FontWeight.BOLD, 16);

  public CircleNode(String value, double centerX, double centerY) {
    this.value = value;

    Text text = new Text(value);
    text.setFont(FONT);
    text.setBoundsType(TextBoundsType.VISUAL);

    Circle cir = new Circle(RADIUS);
    cir.setFill(Color.TRANSPARENT);
    cir.setStroke(Color.BLUEVIOLET);
    cir.setStrokeWidth(3);

    this.getChildren().addAll(cir, text);
    this.setLayoutX(centerX);
    this.setLayoutY(centerY);
  }

  @Override
  public int compareTo(Object o) {
    if (o == null) return -1;
    if (this.getLayoutX() == ((CircleNode) o).getLayoutX() && this.getLayoutY() == ((CircleNode) o).getLayoutY()) {
      return 0;
    }
    return 1;
  }
}
