package com.danutz_bia.chessgame.domain.board;

import com.danutz_bia.chessgame.domain.pieces.Piece;

import java.util.Map;

public class Board {
    //nu poti avea un imutabble array in java dar poti avea list asa ca de aia folosim
//fiecare tile din board o sa aiba un id
    private Board(Builder builder) {
    }
    public Tile getTile(int candidateDestinationCoordinate) {
        return null;
    }
    //we build an instance of the board
    public static class Builder{
        Map<Integer, Piece> boardConfig;
        //Integer is for Tile id and Piece is for the piece that is on the tile
        //we have immutable things and once we invoke the build method we will create a board
        public Board build(){
            return new Board(this);
        }
    }
}
