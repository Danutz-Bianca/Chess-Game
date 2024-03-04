package com.danutz_bia.chessgame.domain;

import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.Move;
import com.danutz_bia.chessgame.domain.pieces.King;
import com.danutz_bia.chessgame.domain.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player{


    public WhitePlayer(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves) {
        super(board, legalMoves, opponentMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getAllWhitePieces();
    }
}
