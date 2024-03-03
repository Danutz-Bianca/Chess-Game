package com.danutz_bia.chessgame.domain.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLOUMN = initColoumn(0);
    public static final boolean[] SECOND_COLOUMN=initColoumn(1);
    public static final boolean[] SEVENTH_COLOUMN = initColoumn(6);
    public static final boolean[] EIGHTH_COLOUMN =initColoumn(7) ;
    public static final boolean [] SECOND_ROW=null;
    public static final boolean [] SEVENTH_ROW=null;

    private static boolean[] initColoumn(int i) {
        return null;
    }

    BoardUtils(){
        throw new RuntimeException("Cannot instantiate");
    }
    //is a valid coloumn if is between 0 and 64
    public static boolean isValidTileCoordinate(int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < 64;
    }
}
