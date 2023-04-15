package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;

    private int currPos;

    private String name;

    private static Board gameBoard = new Board();

    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currPos =0;
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int diceValue){
        if(currPos+diceValue<=100){
            currPos += diceValue;

            TranslateTransition SecondMove = null, FirstMove = translateAnimation(diceValue);


            int newPosition = gameBoard.getNewPosition(currPos);
            if(newPosition!=currPos && newPosition!=-1){
               currPos = newPosition;
                SecondMove = translateAnimation(6);

            }

            if(SecondMove==null){
                FirstMove.play();
            }
            else{
                SequentialTransition sequentialTransition = new SequentialTransition(FirstMove,
                        new PauseTransition(Duration.millis(1000)),SecondMove);
                sequentialTransition.play();
            }

            
        }

//            int X = gameBoard.getXCoordinate(currPos);
//            int Y = gameBoard.getYCoordinate(currPos);
//            coin.setTranslateX(X);
//            coin.setTranslateY(Y);
    }

        private TranslateTransition translateAnimation(int diceValue){
            TranslateTransition animate = new TranslateTransition(Duration.millis(1000),coin);
            animate.setToX(gameBoard.getXCoordinate(currPos));
            animate.setToY(gameBoard.getYCoordinate(currPos));
            animate.setAutoReverse(false);
            return animate;
        }

        public void startingPosition(){
        currPos = 1;
        movePlayer(0);
        }

        boolean isWinner(){
        if(currPos==100) return true;
        return false;
        }

        public Circle getCoin() {
            return coin;
        }

        public int getCurrPos() {
            return currPos;
        }

        public String getName() {
            return name;
        }

    }

