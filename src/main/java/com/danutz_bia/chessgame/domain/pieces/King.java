package com.danutz_bia.chessgame.domain.pieces;


import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.BoardUtils;
import com.danutz_bia.chessgame.domain.board.Move;
import com.danutz_bia.chessgame.domain.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(int piecePosition, Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (int candidate : CANDIDATE_MOVE_COORDINATES) {
            candidate += this.piecePosition;
            if (isFirstColumnExclusion(this.piecePosition, candidate) ||
                    isEighthColumnExclusion(this.piecePosition, candidate)) {
                continue;
            }
            if (BoardUtils.isValidTileCoordinate(candidate)) {
                final Tile candidateDestinationTile = board.getTile(candidate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidate));
                } else {
                    final Piece pieceOnTile = candidateDestinationTile.getPiece();
                    final Color pieceColorOnTile = candidateDestinationTile.getPiece().getPieceColor();
                    if (this.pieceColor != pieceColorOnTile) {
                            //attack move
                            //if you can do that and you are not in check
                            legalMoves.add(new Move.AttackMove(board, this, candidate, pieceOnTile));
                    }
                }
            }
        }


        return Collections.unmodifiableList(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLOUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLOUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }
}
