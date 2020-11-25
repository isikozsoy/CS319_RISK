import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.effect.*;

import java.io.*; //exceptions
import java.util.List;

public class RiskView extends StackPane {
    final int WIDTH = 1280;
    final int HEIGHT = 1024;

    final String DIRECTORY_NAME = "/img/";
    final String FILE_NAME_HELPER = "_bw.png";
    final String FILE_NAME_HOVERED_HELPER = "_bw_hovered.png";
    final String[] territories = {"Alaska", "Northwest Territory", "Greenland", "Alberta", "Ontario", "Quebec",
            "West America", "East America", "Central America", "Venezuela", "Peru", "Brazil", "Argentina",
            "North Africa", "Egypt", "East Africa", "Congo", "South Africa", "Madagascar",
            "Iceland", "Scandinavia", "Ukraine", "Britain", "NE", "SE", "WE",
            "Indonesia", "New Guinea", "Western Australia", "Eastern Australia",
            "Siam", "India", "China", "Mongolia", "Japan", "Irkutsk", "Yakutsk", "Kamchatka", "Siberia",
            "Afghanistan", "Ural", "Middle East"
    };
    final String BACKGROUND_IMG_PATH = "background_image_bw.png";

    private Stage mainStage;

    public RiskView(Stage stage) {
        makeClickableMap();
        addPlayButton(stage);
    }

    /** come back to this **/
    public RiskView( int minWidth, int minHeight) {
        setMinSize( minWidth, minHeight);
        makeClickableMap();
    }

    //below works for the first round (territory allocation), probably
    private ImageView addClickableTerritory( String countryName, String path, String hoverPath) {
        ImageView territoryClickable = new ClickableTerritory(countryName, path, hoverPath);
        return territoryClickable;
    }

    private void makeClickableMap() {
        ImageView bgImage = new ImageView( new Image( DIRECTORY_NAME + BACKGROUND_IMG_PATH, true));
        bindMapToPaneSize(bgImage);
        this.getChildren().add( bgImage);

        for( int i = 0; i < territories.length; i++) {
            ImageView clickableTerritory = addClickableTerritory( territories[i],
                                                             DIRECTORY_NAME + territories[i] + FILE_NAME_HELPER,
                                                          DIRECTORY_NAME + territories[i] + FILE_NAME_HOVERED_HELPER);
            bindMapToPaneSize(clickableTerritory);

            this.getChildren().add( clickableTerritory);
        }
    }

    private void bindMapToPaneSize( ImageView imageView) {
        imageView.fitWidthProperty().bind( this.widthProperty());
        imageView.fitHeightProperty().bind( this.heightProperty());
        //imageView.setPreserveRatio( true);
    }
    private void addPlayButton(Stage stage) {
        Button play = new Button("play");
        play.setLayoutX(500);
        play.setLayoutY(20);
        play.setOnMousePressed( e -> {
            RPSGameView gameView = new RPSGameView( stage);
            Scene newScene = new Scene(gameView, 1280, 1024);
            stage.setScene( newScene);
        });
        this.getChildren().add(play);
        this.setAlignment(play, Pos.TOP_RIGHT);
    }
    public void disableAllComponents() {
        List<Node> territoryList = this.getChildren();
        for (int i = 0; i < territories.length; i++)
            ((ClickableTerritory)territoryList.get(i)).removeEventListeners();
    }
}