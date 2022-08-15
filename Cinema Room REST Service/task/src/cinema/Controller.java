package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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
            return new ResponseEntity(room.removeSeatFromAvailableWithBody(seat), HttpStatus.OK);
        } else if (room.isSeatValid(seat)) {
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
    }
}