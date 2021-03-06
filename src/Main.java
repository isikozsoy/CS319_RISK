import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    final private int WIDTH = 1000;
    final private int HEIGHT = 750;

    Scene mainScene;
    //Stage mainStage;

    @Override
    public void start(Stage stage) {
        try {
            MainMenuView menuView = new MainMenuView( stage, WIDTH, HEIGHT);
            mainScene = new Scene(menuView, WIDTH, HEIGHT);
            stage.setScene( mainScene);
            stage.setTitle("Annexation");
            stage.show();
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args) {
        launch(args);
    }
}