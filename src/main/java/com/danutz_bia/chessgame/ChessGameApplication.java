package com.danutz_bia.chessgame;

import com.danutz_bia.chessgame.domain.board.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessGameApplication {

    public static void main(String[] args) {

        Board board= Board.createInitialBoard();
        System.out.println(board.toString());

        SpringApplication.run(ChessGameApplication.class, args);
    }
}
