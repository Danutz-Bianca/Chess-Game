package com.danutz_bia.chessgame.domain.board;

import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.pieces.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Board {
    //nu poti avea un imutabble array in java dar poti avea list asa ca de aia folosim
    @Getter
    private final List<Tile> gameBoard;
    @Getter
    @Setter
    private Collection<Piece>allWhitePieces;
    @Getter
    @Setter
    private Collection<Piece>allBlackPieces;

//fiecare tile din board o sa aiba un id
    private Board(Builder builder) {
        this.gameBoard=createGameBoard(builder);
        this.allWhitePieces=null;
        this.allBlackPieces=null;
    }
    private static  Board createStandardBoard(){
        Builder builder=new Builder();
        builder.setPiece(new Rook(0,Color.BLACK));
        builder.setPiece(new Knight(1,Color.BLACK));
        builder.setPiece(new Bishop(2,Color.BLACK));
        builder.setPiece(new Queen(3,Color.BLACK));
        builder.setPiece(new King(4,Color.BLACK));
        builder.setPiece(new Bishop(5,Color.BLACK));
        builder.setPiece(new Knight(6,Color.BLACK));
        builder.setPiece(new Rook(7,Color.BLACK));
        for(int i=8;i<16;i++) {
            builder.setPiece(new Pawn(i, Color.BLACK));
        }
        builder.setPiece(new Rook(56,Color.WHITE));
        builder.setPiece(new Knight(57,Color.WHITE));
        builder.setPiece(new Bishop(58,Color.WHITE));
        builder.setPiece(new Queen(59,Color.WHITE));
        builder.setPiece(new King(60,Color.WHITE));
        builder.setPiece(new Bishop(61,Color.WHITE));
        builder.setPiece(new Knight(62,Color.WHITE));
        builder.setPiece(new Rook(63,Color.WHITE));
        for(int i=48;i<56;i++) {
            builder.setPiece(new Pawn(i, Color.WHITE));
        }
        builder.setNextMoveMaker(Color.WHITE);
        return builder.build();
    }

    private static List<Tile> createGameBoard( final Builder builder) {
        final Tile[] tiles =new Tile[64];
        for(int i=0;i<64;i++){
            tiles[i]=Tile.createTile(i,builder.boardConfig.get(i));
        }
        //a vector is transformed in list
        List<Tile> tilesList = new ArrayList<>(Arrays.asList(tiles));
        return Collections.unmodifiableList(tilesList);
    }

    public Tile getTile(int candidateDestinationCoordinate) {
        return null;
    }

    //we build an instance of the board
    public static class Builder{
        Map<Integer, Piece> boardConfig;
        @Getter
        private Color nextMoveMaker;
        public Builder(){
        }
        //Integer is for Tile id and Piece is for the piece that is on the tile
        //we have immutable things and once we invoke the build method we will create a board
        public Board build(){
            return new Board(this);
        }
        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }
        public Builder setNextMoveMaker(final Color nextMoveMaker){
            this.nextMoveMaker=nextMoveMaker;
            return this;
        }

    }
}
