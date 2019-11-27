package ticTacToe;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        out.println("Position");
        out.println(position);
        out.println(cell + "'s move");
        int column = -1, row = -1;
        while (true) {
            out.println("Enter column and row: ");

            boolean valid = false;
            while (!valid) {
                if (in.hasNextInt()) {
                    column = in.nextInt();
                    valid = true;
                } else {
                    if (in.hasNext()) {
                        out.print("Please, be careful and input correct column: ");
                        in.next();
                    } else {
                        out.print("PROGRAM TERMINATED!\n");
                        System.exit(1);
                    }
                }

            }
            valid = false;

            while (!valid) {
                if (in.hasNextInt()) {
                    row = in.nextInt();
                    valid = true;
                } else {
                    if (in.hasNext()) {
                        out.print("Please, be careful and input correct row: ");
                        in.next();
                    } else {
                        out.print("PROGRAM TERMINATED!\n");
                        System.exit(1);
                    }
                }
            }

            final Move move = new Move(column, row, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid\nPlease, make valid move!");
        }
    }

}
