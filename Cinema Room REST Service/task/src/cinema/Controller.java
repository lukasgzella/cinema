package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {
    private Room room = new Room();

    @GetMapping("/seats")
    public Room showAvailableSeats() {
        this.room = room;
        return room;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseTicket(@RequestBody Seat seat) {
        if (room.isAvailable(seat)) {
            Token token = room.removeSeatFromAvailable(seat);
            return new ResponseEntity(Map.of("ticket", token.getSeat(), "token", token.getTokenValue()), HttpStatus.OK);
        } else if (room.isSeatValid(seat)) {
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody TokenRequest tokenRequest) {
        if (room.getPurchasedSeats().containsKey(tokenRequest.getToken())) {
            Seat returnedSeat = room.removeSeatFromPurchased(tokenRequest);
            return new ResponseEntity(Map.of("returned_ticket", returnedSeat),HttpStatus.OK);
        } else {
            return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }
}