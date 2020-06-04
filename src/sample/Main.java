package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    private static final int MIN_HEIGHT = 600;
    private static final int MIN_WIDTH = 500;


    @Override
    public void start(Stage stage) throws Exception {
        Image ICON = new Image("file:icon.png");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Visualize Tree Algorithm");
        stage.setScene(new Scene(root, MIN_HEIGHT, MIN_WIDTH));
        stage.getIcons().add(ICON);
        stage.show();

    }

    public static void  main(String[] args)
    {
        launch(args);
    }
}