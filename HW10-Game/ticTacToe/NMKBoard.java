package ticTacToe;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class NMKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(Cell.X, 'X', Cell.O, 'O', Cell.E, '.');

    private final Cell[][] cells;
    private Cell turn;
    private final int n;
    private final int m;
    private final int k;
    private int moveCounter;

    public NMKBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];
        clean();
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return check(move);
    }

    private int count(Move move, int x, int y) {
        int c = move.getColumn();
        int r = move.getRow();
        int cnt = 0;
        r += y;
        c += x;
        while (r >= 0 && r < n && c >= 0 && c < m && cells[r][c] == move.getValue()) {
            cnt++;
            r += y;
            c += x;
        }
        return cnt;
    }

    private Result check(Move move) {
        moveCounter--;
        Result result;
        int horisontalCount = count(move, 1, 0) + count(move, -1, 0) + 1;
        int verticalCount = count(move, 0, 1) + count(move, 0, -1) + 1;
        int diagonalCount1 = count(move, 1, 1) + count(move, -1, -1) + 1;
        int diagonalCount2 = count(move, -1, 1) + count(move, 1, -1) + 1;

        if (horisontalCount >= k || verticalCount >= k || diagonalCount1 >= k || diagonalCount2 >= k) {
            result = Result.WIN;
        } else if (moveCounter == 0) {
            result = Result.DRAW;
        } else {
            result = Result.UNKNOWN;
        }
        return result;
    }

    @Override
    public boolean isValid(final Move move) {
        return moveCounter > 0 && 0 <= move.getRow() && move.getRow() < m && 0 <= move.getColumn()
                && move.getColumn() < n && cells[move.getRow()][move.getColumn()] == Cell.E && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder coordinates = new StringBuilder();
        coordinates.append(" ");
        for (int i = 0; i < n; i++) {
            coordinates.append(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(coordinates);

        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        sb.append("\n------------");
        return sb.toString();
    }

    @Override
    public int getRows() {
        return n;
    }

    @Override
    public int getColumns() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public void clean() {
        moveCounter = m * n;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }
}
