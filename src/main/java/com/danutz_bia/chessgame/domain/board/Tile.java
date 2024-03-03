package com.danutz_bia.chessgame.domain.board;


import com.danutz_bia.chessgame.domain.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract  class Tile {
    //doar clasa si cine il foloseste
    protected final int tileCoordinate;
    private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE=createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer,EmptyTile> emptyTileMap=new HashMap<>();
        for(int i=0;i<64;i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }
        return Collections.unmodifiableMap(emptyTileMap);//trebuia sa o facem imutabila ca sa nu o poata schimba nimeni
    }
    //facand constructorul privat singura metoda sa creez un obiect de tipul asta e sa folosesc metoda asta
    public static Tile createTile(final int tileCoordinate,final Piece piece){
        //ori ia un cache(Empty_Title_Cache) ori creeaza un obiect nou
        return piece!=null?new OccupiedTile(tileCoordinate,piece):EMPTY_TILES_CACHE.get(tileCoordinate);
    }
    private Tile(final int tileCoordinate){
        this.tileCoordinate=tileCoordinate;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
    //to define nested classes that are associated with the Tile class but do not depend on specific instances of it

    //Static: The static keyword means that the nested class belongs to the type itself, not to instances of the type. In this case, EmptyTile and OccupiedTile are nested classes of the Tile class,
    // and making them static implies that they can exist independently of any specific instance of Tile.

    //The final keyword in this context ensures that these nested classes cannot be subclassed. It means that no other class can extend EmptyTile or OccupiedTile
    private static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate){
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }
    //i want to know if it's occupied and if it is occupied i want to know what piece is on it
    private static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        OccupiedTile(final int tileCoordinate,Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile=pieceOnTile;
        }
        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
