package cinema;

import java.util.*;

public class Room {
    private final int totalRows = 9;
    private final int totalColumns = 9;
    private Set<Seat> availableSeats = new LinkedHashSet<>();
//    private Set<Token> purshasedSeats = new LinkedHashSet<>();

    public Room() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                if (row <= 4) {
                    availableSeats.add(new Seat(row, column, 10));
                } else {
                    availableSeats.add(new Seat(row, column, 8));
                }
            }
        }
    }

    public void removeSeatFromAvailableWithParams (int row, int column) {
        if (row <= 4) {
            this.availableSeats.remove(new Seat(row, column, 10));
        } else {
            this.availableSeats.remove(new Seat(row, column, 8));
        }
    }
    public boolean isAvailable(Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        return availableSeats.contains(new Seat(row, column, 8))
                || availableSeats.contains(new Seat(row, column, 10));
    }
    public boolean isSeatValid(Seat seat) {
        return seat.getRow() > 0 && seat.getRow() <= totalRows
                && seat.getColumn() > 0 && seat.getColumn() <= totalColumns;
    }

    public Seat removeSeatFromAvailableWithBody (Seat seat) {
        if (seat.getRow() <= 4) {
            Seat removingSeat = new Seat(seat.getRow(), seat.getColumn(), 10);
            this.availableSeats.remove(removingSeat);
            return removingSeat;
        } else {
            Seat removingSeat = new Seat(seat.getRow(), seat.getColumn(), 8);
            this.availableSeats.remove(removingSeat);
            return removingSeat;
        }
    }

    @Override
    public String toString() {
        return "Room {" +
                "totalRows = " + totalRows +
                ", totalColumns = " + totalColumns +
                ",\navailableSeats = " + availableSeats +
                '}';
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public Set<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Set<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}