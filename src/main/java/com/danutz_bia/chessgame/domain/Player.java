package com.danutz_bia.chessgame.domain;

import com.danutz_bia.chessgame.domain.board.Board;
import com.danutz_bia.chessgame.domain.board.Move;
import com.danutz_bia.chessgame.domain.pieces.King;
import com.danutz_bia.chessgame.domain.pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static java.lang.System.in;

public abstract  class Player {
    @Getter
    protected final Board board;
    @Getter
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    private  final boolean isInCheck;
    //private  final boolean isInCheckMate;

    public Player(Board board,Collection<Move>legalMoves,Collection<Move>opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves=legalMoves;
        this.isInCheck = !Player.calculateAllMovesOfOpponent(this.playerKing.getPiecePosition(),opponentMoves).isEmpty();
    }

    private  Collection<Move> hasEscapeMoves(int piecePosition, Collection<Move> opponentMoves) {
        List<Move>toEscape=new ArrayList<>();
        Collection<Move>movesKingCanDo=this.playerKing.calculateLegalMoves(board);
        //if it is in check ,we calculate the possibility moves
        if(isInCheck()){
            for(Move moveOpponent:opponentMoves){
                if (!movesKingCanDo.contains(moveOpponent.getDestinationCoordinate())) {
                    toEscape.add(moveOpponent);
                }
            }
        }
        return toEscape;
    }
    //to check if our king is on the board
    public King establishKing(){
        for(final Piece piece:getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (King)piece;
            }
        }
        return null;
    }
    public abstract  Collection<Piece>getActivePieces();



    private static Collection<Move> calculateAllMovesOfOpponent(int piecePosition, Collection<Move> opponentMoves) {
        List<Move>attackMoveForKing=new ArrayList<>();
        for (Move move :opponentMoves){
            if(move.getDestinationCoordinate()==piecePosition){
                attackMoveForKing.add(move);
            }
        }
        return Collections.unmodifiableList(attackMoveForKing);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }
    public boolean isInCheckMate(Collection<Move>opponentMoves){
        return this.isInCheck && hasEscapeMoves(this.playerKing.getPiecePosition(),opponentMoves).isEmpty();
    }

}
