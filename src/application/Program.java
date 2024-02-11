package application;


import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(ler);

                boolean[][] possibleMoveis = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoveis);


                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(ler);

                ChessPiece capturedPiece = chessMatch.performChessMovie(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece); // adicionando uma peça capturada
                }

                if (chessMatch.getPromoted() != null) {
                    System.out.println("Enter piece for promotion (B/N/R/Q): ");
                    String type = ler.nextLine();
                    while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
                        System.out.println("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        type = ler.nextLine();
                    }
                    chessMatch.replacePromotedPiece(type);
                }

            }
            catch (ChessException e) {
                System.out.println(e.getMessage());
                ler.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                ler.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
