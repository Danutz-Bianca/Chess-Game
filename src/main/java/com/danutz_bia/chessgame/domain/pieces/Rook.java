package com.danutz_bia.chessgame.domain.pieces;


import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.BoardUtils;
import com.danutz_bia.chessgame.domain.board.Move;
import com.danutz_bia.chessgame.domain.board.Tile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Rook extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8};
    public Rook(int piecePosition, Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final Collection<Move> legalMoves = new ArrayList<>();
        for(int candidate:CANDIDATE_MOVE_VECTOR_COORDINATES){
            int destinationCandidate=this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(destinationCandidate)){
                destinationCandidate+=candidate;
                if(isFirstColumnExclusion(destinationCandidate,candidate) || isEighthColumnExclusion(destinationCandidate,candidate)){
                    break;
                }
                if(BoardUtils.isValidTileCoordinate(destinationCandidate)){
                    if(BoardUtils.isValidTileCoordinate(destinationCandidate)){
                        final Tile candidateDestinationTile = board.getTile(destinationCandidate);
                        if (!candidateDestinationTile.isTileOccupied()) {
                            legalMoves.add(new Move.MajorMove(board,this,destinationCandidate));
                        }
                        else {
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
        }
        return Collections.unmodifiableCollection(legalMoves);
    }
    private  static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return (BoardUtils.FIRST_COLOUMN != null && BoardUtils.FIRST_COLOUMN[currentPosition]) && (candidateOffset == -1);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return (BoardUtils.EIGHTH_COLOUMN != null && BoardUtils.EIGHTH_COLOUMN[currentPosition]) && (candidateOffset == 1);
    }
}
