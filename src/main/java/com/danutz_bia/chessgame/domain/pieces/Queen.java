package com.danutz_bia.chessgame.domain.pieces;

import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.BoardUtils;
import com.danutz_bia.chessgame.domain.board.Move;
import com.danutz_bia.chessgame.domain.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


public class Queen extends Piece {
    private static int[] CANDIDATE_MOVE={-9,-8,-7,-1,1,7,8,9};
    public Queen(int piecePosition, Color pieceColor) {
        super(PieceType.QUEEN,piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final Collection<Move> legalMoves =new ArrayList<>();
        for(final int candidate :CANDIDATE_MOVE){
            int destinationCandidate=this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(destinationCandidate)){
                if(isFirstColumnExclusion(destinationCandidate,candidate) || isEighthColumnExclusion(destinationCandidate,candidate)){
                    break;
                }
                destinationCandidate+=candidate;
                if(BoardUtils.isValidTileCoordinate(destinationCandidate)){
                    final Tile candidateDestinationTile = board.getTile(destinationCandidate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board,this,destinationCandidate));
                    } else {
                        //daca e ocupata de piesa inamica il putem lua
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Color pieceColor = pieceAtDestination.getPieceColor();
                        if (this.pieceColor != pieceColor) {
                            legalMoves.add(new Move.AttackMove(board,this,destinationCandidate,pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return Objects.requireNonNull(BoardUtils.FIRST_COLUMN).get(currentPosition) && (candidateOffset == -9 || candidateOffset == 7 || candidateOffset==-1);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return Objects.requireNonNull(BoardUtils.EIGHTH_COLUMN).get(currentPosition) && (candidateOffset == -7 || candidateOffset == 9 || candidateOffset==1);
    }
}
