package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private Room room;

    @GetMapping("/seats")
    public Room getRoom() {
        List<Seat> listOfSeats = new ArrayList<>();
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                listOfSeats.add(new Seat(row, column));
            }
        }
        return new Room(9, 9, listOfSeats);
    }
}