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

public class Knight extends Piece{
    private final int[] knightMoves = {-17,-15,-10,-6,6,10,15,17};
    public Knight(int piecePosition, Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        for( int i=0;i<64;i++){
            candidateDestinationCoordinate= this.piecePosition + knightMoves[i];
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                //if is an coloumn witch is an exception
                if (isFirstColumnExclusion(this.piecePosition, knightMoves[i]) ||
                        isSecondColumnExclusion(this.piecePosition, knightMoves[i]) ||
                        isSeventhColumnExclusion(this.piecePosition, knightMoves[i]) ||
                        isEighthColumnExclusion(this.piecePosition, knightMoves[i])) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                } else {
                    //daca e ocupata de piesa inamica il putem lua
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Color pieceColor = pieceAtDestination.getPieceColor();
                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private boolean isEighthColumnExclusion(final int piecePosition,final int knightMove) {

        return BoardUtils.EIGHTH_COLOUMN[piecePosition] && (knightMove == 17 || knightMove == 10 || knightMove == -6 || knightMove == -15);
    }

    private boolean isSeventhColumnExclusion(final int piecePosition,final int knightMove) {
        return BoardUtils.SEVENTH_COLOUMN[piecePosition] && (knightMove == 10 || knightMove == -6);
    }

    private boolean isSecondColumnExclusion(final int piecePosition,final int knightMove) {
        return BoardUtils.SECOND_COLOUMN[piecePosition] && (knightMove == -10 || knightMove == 6);
    }

    private static boolean isFirstColumnExclusion(final int piecePosition,final int knightMove) {
        return BoardUtils.FIRST_COLOUMN[piecePosition] && (knightMove == -17 || knightMove == -10 || knightMove == 6 || knightMove == 15);
    }
}
