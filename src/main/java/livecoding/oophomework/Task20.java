package livecoding.oophomework;

import java.util.HashSet;
import java.util.Set;

class CinemaSession {
    private String title;
    private int totalSeats;
    private Set<Integer> occupiedSeats = new HashSet<>();

    public CinemaSession(String title, int totalSeats) {
        this.title = title;
        this.totalSeats = totalSeats;
    }

    public void reserveSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            throw new IllegalArgumentException
                    ("Вы можете забронировать любое место от 1 до " + totalSeats + " при наличии свободных мест");
        }

        if (!occupiedSeats.add(seatNumber)) {
            throw new IllegalStateException("Это место уже занято, пожалуйста, выберите другое");
        }
    }

    public void cancelReservation(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            throw new IllegalArgumentException
                    ("В кинозале таких мест нет");
        }

        if (!occupiedSeats.remove(seatNumber)) {
            throw new IllegalStateException
                    ("Место не было забронировано");
        }
    }

    public boolean isSeatReserved(int seatNumber) {
        return occupiedSeats.contains(seatNumber);
    }

    public int getFreeSeatsCount() {
        return totalSeats - occupiedSeats.size();
    }
}

public class Task20 {
    public static void main(String[] args) {
        CinemaSession cinemaSession = new CinemaSession("Интерстеллар", 10);

        cinemaSession.reserveSeat(1);
        cinemaSession.reserveSeat(2);
        cinemaSession.reserveSeat(3);
        System.out.println("Свободных мест: " + cinemaSession.getFreeSeatsCount());

        cinemaSession.cancelReservation(2);
        System.out.println("Свободных мест: " + cinemaSession.getFreeSeatsCount());

        System.out.println("Место свободно: " + cinemaSession.isSeatReserved(1));
    }
}
