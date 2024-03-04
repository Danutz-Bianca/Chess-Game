package com.danutz_bia.chessgame.domain.pieces;

import lombok.Getter;
import lombok.Setter;

public enum PieceType {
    PAWN( "P"),
    KNIGHT( "N"),
    BISHOP("B"),
    ROOK("R"),
    QUEEN( "Q"),
    KING("K");

    @Getter
    private final String pieceName;



    @Override
    public String toString() {
        return this.pieceName;
    }

    PieceType(final String pieceName) {

        this.pieceName = pieceName;
    }

    public boolean isKing() {
        if(pieceName=="K"){
            return true;
        }
        return false;
    }
}
