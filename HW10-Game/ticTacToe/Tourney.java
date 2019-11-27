package ticTacToe;

import java.util.*;

class Tourney {
    private ArrayList<Player> players;
    private final Board board;
    int[] scores;
    private int c;
    private int playersNum;

    public Tourney(Board board, ArrayList<Player> players, int c) {
        this.board = board;
        this.players = players;
        this.c = c;
        this.playersNum = players.size();
        scores = new int[playersNum];
    }

    public void begin() {
        for (int i = 0; i < c; i++) {
            round();
        }
    }

    private void round() {
        for (int i = 0; i < playersNum; i++) {
            for (int j = i; j < playersNum; j++) {
                Game game = new Game(false, players.get(i), players.get(j));
                int result = game.play(board);
                if (result == 1) {
                    scores[i] += 3;
                } else if (result == 2) {
                    scores[j] += 3;
                } else if (result == 0) {
                    scores[j] += 1;
                    scores[i] += 1;
                }
                board.clean();
            }
        }
    }

    public String result() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < playersNum; i++) {
            result.append(new String("Player #" + i + " score: " + scores[i] + "\n"));
        }
        return result.toString();
    }

}