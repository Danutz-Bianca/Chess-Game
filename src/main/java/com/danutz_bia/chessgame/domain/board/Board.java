package com.danutz_bia.chessgame.domain.board;

import com.danutz_bia.chessgame.domain.BlackPlayer;
import com.danutz_bia.chessgame.domain.Color;
import com.danutz_bia.chessgame.domain.WhitePlayer;
import com.danutz_bia.chessgame.domain.pieces.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Map<Integer,Piece>boardConfig;
    //nu poti avea un imutabble array in java dar poti avea list asa ca de aia folosim
    @Getter
    private final List<Tile> gameBoard;
    @Getter
    @Setter
    private Collection<Piece>allWhitePieces;
    @Getter
    @Setter
    private Collection<Piece>allBlackPieces;
    private Collection<Move>allBlackMove;
    private Collection<Move>allWhiteMove;
    @Getter
    @Setter
    private WhitePlayer whitePlayer;
    @Getter
    @Setter
    private BlackPlayer blackPlayer;

//fiecare tile din board o sa aiba un id
    private Board(Builder builder) {
        this.boardConfig=builder.boardConfig;
        this.gameBoard=createGameBoard(builder);
        this.allWhitePieces=bringAllPieces(gameBoard,Color.WHITE);
        this.allBlackPieces=bringAllPieces(gameBoard,Color.BLACK);
        final Collection<Move> whiteStandardMoves = calculateLegalMoves(this.allWhitePieces);
        final Collection<Move> blackStandardMoves = calculateLegalMoves(this.allBlackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteStandardMoves, blackStandardMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardMoves, blackStandardMoves);
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        Collection<Move>allMoves=new ArrayList<>();
        for(Piece piece:pieces){
            allMoves.add(new Move(this,piece, piece.getPiecePosition()));
        }
        return allMoves;
    }


    private Collection<Piece> bringAllPieces(List<Tile>gameBoard,Color colorPieces) {
        final List<Piece>piecesList=new ArrayList<>();
        for(Tile tile:gameBoard){
            Piece pieceOnTile=tile.getPiece();
            Color colorOfPiece=null;
            if(pieceOnTile!=null) {
                 colorOfPiece = pieceOnTile.getPieceColor();
            }
            if(colorOfPiece==colorPieces){
                piecesList.add(pieceOnTile);
            }
        }
        return Collections.unmodifiableCollection(piecesList);
    }


    //the initialization of the bord
    public static  Board createInitialBoard(){
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
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            final String tileText = prettyPrint(this.gameBoard.get(i).getPiece());
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % 8 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
    private static String prettyPrint(final Piece piece) {
        if(piece != null) {
            return piece.getPieceColor().isBlack() ?
                    piece.toString().toLowerCase() : piece.toString();
        }
        return "-";
    }

    private static List<Tile> createGameBoard( final Builder builder) {
        final Tile[] tiles =new Tile[64];
        for(int i=0;i<64;i++){
            tiles[i]=Tile.createTile(i, builder.getBoardConfig().get(i));
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
        private Map<Integer, Piece> boardConfig;
        @Getter
        private Color nextMoveMaker;
        public Builder(){
            this.setBoardConfig(new HashMap<>());
        }
        //Integer is for Tile id and Piece is for the piece that is on the tile
        //we have immutable things and once we invoke the build method we will create a board
        public Board build(){
            return new Board(this);
        }
        public Builder setPiece(final Piece piece){
            this.getBoardConfig().put(piece.getPiecePosition(), piece);
            return this;
        }
        public Builder setNextMoveMaker(final Color nextMoveMaker){
            this.nextMoveMaker=nextMoveMaker;
            return this;
        }

        public Map<Integer, Piece> getBoardConfig() {
            return boardConfig;
        }

        public void setBoardConfig(Map<Integer, Piece> boardConfig) {
            this.boardConfig = boardConfig;
        }
    }
}
