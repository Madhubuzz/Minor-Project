package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int titleSize=40, width=10, height=10;
    public static final int buttonline = height*titleSize + 50,infoline = buttonline - 30;

    private static Dice dice = new Dice();
    private Player Player1,Player2;

    private boolean gameStarted = false, Player1Turn = false, Player2Turn = false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*titleSize, height*titleSize + 100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(titleSize);
                tile.setTranslateX(j*titleSize);
                tile.setTranslateY(i*titleSize);
                root.getChildren().addAll(tile);

            }
        }

        Image img = new Image("C:\\Users\\madhu\\IdeaProjects\\SnakeandLadder\\src\\main\\img.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*titleSize);
        board.setFitWidth(width*titleSize);

        //Button
        Button player1button = new Button("Player 1");
        Button player2button = new Button("Player 2");
        Button startbutton = new Button("Start");

        player1button.setTranslateY(buttonline);
        player1button.setTranslateX(20);
        player1button.setDisable(true);
        player2button.setTranslateY(buttonline);
        player2button.setTranslateX(300);
        player2button.setDisable(true);
        startbutton.setTranslateY(buttonline);
        startbutton.setTranslateX(160);

        //info display
        Label player1label = new Label("");
        Label player2label = new Label("");
        Label dicelabel = new Label("Start the Game");

        player1label.setTranslateY(infoline);
        player1label.setTranslateX(20);
        player2label.setTranslateY(infoline);
        player2label.setTranslateX(300);
        dicelabel.setTranslateY(infoline);
        dicelabel.setTranslateX(160);

        Player1 = new Player(titleSize, Color.AQUAMARINE,"A");
        Player2 = new Player(titleSize-5, Color.ROYALBLUE,"B");

        //Player Action
        player1button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(Player1Turn){
                        int dicevalue = dice.getRolledDiceValue();
                        dicelabel.setText("Dice value :" + dicevalue);
                        Player1.movePlayer(dicevalue);
                        //Winning Condition
                        if(Player1.isWinner()){
                            dicelabel.setText(" Winner is " + Player1.getName());
                            Player1Turn = false;
                            player1button.setDisable(true);
                            player1label.setText("");
                            Player2Turn = false;
                            player2button.setDisable(true);
                            player2label.setText("");

                            startbutton.setDisable(false);
                            startbutton.setText("Restart");
                        }
                        else{
                            Player1Turn = false;
                            player1button.setDisable(true);
                            player1label.setText("");

                            Player2Turn = true;
                            player2button.setDisable(false);
                            player2label.setText(" Your turn " + Player2.getName());
                        }
                    }
                }
            }
        });

        player2button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(Player2Turn){
                        int dicevalue = dice.getRolledDiceValue();
                        dicelabel.setText("Dice value :" + dicevalue);
                        Player2.movePlayer(dicevalue);
                        //Winning Condition
                        if(Player2.isWinner()){
                            dicelabel.setText(" Winner is " + Player2.getName());
                            Player1Turn = false;
                            player1button.setDisable(true);
                            player1label.setText("");
                            Player2Turn = false;
                            player2button.setDisable(true);
                            player2label.setText("");

                            startbutton.setDisable(false);
                            startbutton.setText("Restart");
                            gameStarted = false;
                        }
                        else{
                            Player1Turn = true;
                            player1button.setDisable(false);
                            player1label.setText(" Your turn " + Player1.getName());

                            Player2Turn = false;
                            player2button.setDisable(true);
                            player2label.setText("");
                        }
                    }
                }
            }
        });


        startbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                dicelabel.setText("Game Started");
                startbutton.setDisable(true);
                Player1Turn = true;
                player1label.setText(" Your Turn " + Player1.getName());
                player1button.setDisable(false);
                Player1.startingPosition();
                Player2Turn = false;
                player2label.setText("");
                player2button.setDisable(true);
                Player2.startingPosition();
            }
        });

        root.getChildren().addAll(board, player1button,player2button,startbutton,player1label,
                player2label,dicelabel,Player1.getCoin(),Player2.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake And Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}