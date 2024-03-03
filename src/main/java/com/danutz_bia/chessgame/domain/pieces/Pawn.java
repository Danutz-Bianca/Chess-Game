package com.danutz_bia.chessgame.domain.pieces;

import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.BoardUtils;
import com.danutz_bia.chessgame.domain.board.Move;
import com.danutz_bia.chessgame.domain.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Pawn extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};
    Pawn(final int piecePosition,final Color pieceColor) {
        super(piecePosition, pieceColor);
    }
    //TODO
    //if it is in an initial position (2 row ) it can move 2 squares
    //if it is not in the initial position it can move only 1 square
    //if it  is an enemy piece in the next square it can attack it
    //if it arrive at the final it can be promoted to a queen
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final Collection<Move> legalMoves = new ArrayList<>();
        for(int candidate:CANDIDATE_MOVE_COORDINATES){
            int destinationCandidate=this.piecePosition+(this.pieceColor.directionPices()*candidate);
            if(!BoardUtils.isValidTileCoordinate(destinationCandidate)){
                continue;
            }
            //if it is not in the initial position it can move only 1 square,if this square is not occupied
            if(candidate==8 && !board.getTile(destinationCandidate).isTileOccupied()) {
                //TODO
                //it is it on the final row it can be promoted to a queen

                    if(BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceColor.isBlack() ||
                            BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceColor.isWhite())
                    {
                        //TODO
                        legalMoves.add(new Move.PawnMove(board,this,destinationCandidate));
                    }

            }else if(candidate==16 && this.isTheFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceColor().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceColor().isWhite())){
                final int firstTile =this.piecePosition+8*this.getPieceColor().directionPices();
                //final int secondTile =this.piecePosition+16*this.getPieceColor().directionPices();
                if(!board.getTile(firstTile).isTileOccupied() && !board.getTile(destinationCandidate).isTileOccupied()){
                    legalMoves.add(new Move.PawnMove(board,this,destinationCandidate));
                }
            }else if(candidate==7 &&!((BoardUtils.EIGHTH_COLOUMN[this.piecePosition] && this.pieceColor.isWhite()) ||
                        (BoardUtils.FIRST_COLOUMN[this.piecePosition] && this.pieceColor.isBlack()))){
                    if(board.getTile(destinationCandidate).isTileOccupied()){
                        final Piece pieceOnCandidate = board.getTile(destinationCandidate).getPiece();
                        if(this.pieceColor!=pieceOnCandidate.getPieceColor()){
                            //TODO
                            //if it is on the final row it can be promoted to a queen
                            if(BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceColor.isBlack() ||
                                    BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceColor.isWhite()){
                                legalMoves.add(new Move.AttackMove(board,this,destinationCandidate,pieceOnCandidate));
                            }else{
                                //TODO
                                legalMoves.add(new Move.AttackMove(board,this,destinationCandidate,pieceOnCandidate));
                            }
                        }
                    }
            }else if(candidate==9 &&!((BoardUtils.FIRST_COLOUMN[this.piecePosition] && this.pieceColor.isWhite()) ||
                        (BoardUtils.EIGHTH_COLOUMN[this.piecePosition] && this.pieceColor.isBlack()))){
                    if(board.getTile(destinationCandidate).isTileOccupied()){
                        final Piece pieceOnCandidate = board.getTile(destinationCandidate).getPiece();
                        if(this.pieceColor!=pieceOnCandidate.getPieceColor()){
                            //TODO
                            //if it is on the final row it can be promoted to a queen
                            if(BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceColor.isBlack() ||
                                    BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceColor.isWhite()){
                                legalMoves.add(new Move.AttackMove(board,this,destinationCandidate,pieceOnCandidate));
                            }else{
                                legalMoves.add(new Move.AttackMove(board,this,destinationCandidate,pieceOnCandidate));
                            }
                        }

                    }
            }
        }
        return Collections.unmodifiableCollection(legalMoves);
    }
}
