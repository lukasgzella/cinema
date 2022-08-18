package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Room {
    private final int totalRows = 9;
    private final int totalColumns = 9;
    private Set<Seat> availableSeats = new LinkedHashSet<>();
    @JsonIgnore
    private Map<String, Seat> purchasedSeats = new HashMap<>();
    @JsonIgnore
    private int currentIncome = 0;

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

    public Token removeSeatFromAvailable(Seat seat) {
        if (seat.getRow() <= 4) {
            Seat removingSeat = new Seat(seat.getRow(), seat.getColumn(), 10);
            this.availableSeats.remove(removingSeat);
            this.currentIncome += removingSeat.getPrice();

            Token createdToken = new Token(removingSeat);
            this.purchasedSeats.put(createdToken.getTokenValue(), removingSeat);
            return createdToken;
        } else {
            Seat removingSeat = new Seat(seat.getRow(), seat.getColumn(), 8);
            this.availableSeats.remove(removingSeat);
            this.currentIncome += removingSeat.getPrice();

            Token createdToken = new Token(removingSeat);
            this.purchasedSeats.put(createdToken.getTokenValue(), removingSeat);
            return createdToken;
        }
    }

    public Seat removeSeatFromPurchased(TokenRequest tokenRequest) {
        Seat returnedSeat = purchasedSeats.remove(tokenRequest.getToken());
        availableSeats.add(returnedSeat);
        this.currentIncome -= returnedSeat.getPrice();

        return returnedSeat;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
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

    public Map<String, Seat> getPurchasedSeats() {
        return purchasedSeats;
    }

    public void setPurchasedSeats(Map<String, Seat> purchasedSeats) {
        this.purchasedSeats = purchasedSeats;
    }
}