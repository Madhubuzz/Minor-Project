package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> snakeladderPosition;

    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }
    private void populatePositionCoordinates(){
        positionCoordinates.add(new Pair<>(0,0));//dummy value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //X coordinates
                int Xcord = 0;
                if(i%2==0)
                {
                    Xcord = j*SnakeLadder.titleSize + SnakeLadder.titleSize/2;
                }
                else{
                    Xcord = SnakeLadder.titleSize * SnakeLadder.height - (j*SnakeLadder.titleSize) - SnakeLadder.titleSize/2;
                }
                //Y coordinates
                int Ycord = SnakeLadder.titleSize*SnakeLadder.height - (i*SnakeLadder.titleSize) - SnakeLadder.titleSize/2;
                positionCoordinates.add(new Pair<>(Xcord,Ycord));

            }

        }

    }

    private void populateSnakeLadder(){
        snakeladderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeladderPosition.add(i);
        }
        snakeladderPosition.set(4,25);
        snakeladderPosition.set(13,46);
        snakeladderPosition.set(27,5);
        snakeladderPosition.set(33,49);
        snakeladderPosition.set(40,3);
        snakeladderPosition.set(42,63);
        snakeladderPosition.set(43,18);
        snakeladderPosition.set(50,69);
        snakeladderPosition.set(54,31);
        snakeladderPosition.set(62,81);
        snakeladderPosition.set(66,45);
        snakeladderPosition.set(76,58);
        snakeladderPosition.set(74,92);
        snakeladderPosition.set(89,53);
        snakeladderPosition.set(99,41);

    }

    public int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeladderPosition.get(currentPosition);
        }
        return -1;
    }

    int getXCoordinate(int position){
        if(position>=1 && position<=100)
            return positionCoordinates.get(position).getKey();
        return -1;
    }

    int getYCoordinate(int position){
        if(position>=1 && position<=100)
            return positionCoordinates.get(position).getValue();
        return -1;
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.positionCoordinates.size(); i++) {
//            System.out.println(i + "$ x :" + board.positionCoordinates.get(i).getKey()+
//                    " y : " + board.positionCoordinates.get(i).getValue());
//
//        }
//
//    }
}




