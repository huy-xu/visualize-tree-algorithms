package view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import static model.Constants.FONT;
import static model.Constants.RADIUS;

public class CircleNode extends StackPane implements Comparable{
  public String value;
  private double hGap;
  private Line lineLeft, lineRight;

  public CircleNode(String value, double centerX, double centerY, double hGap) {
    this.value = value;
    this.hGap = hGap;
    this.lineLeft = null;
    this.lineRight = null;

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

  public double gethGap() {
    return hGap;
  }

  public void sethGap(double hGap) {
    this.hGap = hGap;
  }

  public Line getLineLeft() {
    return lineLeft;
  }

  public void setLineLeft(Line lineLeft) {
    this.lineLeft = lineLeft;
  }

  public Line getLineRight() {
    return lineRight;
  }

  public void setLineRight(Line lineRight) {
    this.lineRight = lineRight;
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
