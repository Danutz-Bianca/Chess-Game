package com.danutz_bia.chessgame.domain.pieces;


import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.Move;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
@AllArgsConstructor
@Getter
@Setter
public abstract class Piece {
    protected final int piecePosition;
    protected final Color pieceColor;
    protected final boolean isTheFirstMove;

    public Piece(final int piecePosition,final Color pieceColor){
        this.pieceColor=pieceColor;
        this.piecePosition=piecePosition;
        //TODO
        this.isTheFirstMove=false;
    }
    //puteam si set sau collection pt ca nu ar trebui sa fie duplicate
    public abstract Collection<Move> calculateLegalMoves(final Board board);
    protected boolean isTheFirstMove() {
        return this.isTheFirstMove;
    }
}
