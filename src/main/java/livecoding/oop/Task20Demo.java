package livecoding.oop;

import java.util.HashSet;
import java.util.Set;

class CinemaSession {
    private final String movieTitle;
    private final int totalSeats;
    private final Set<Integer> reservedSeats = new HashSet<>();

    public CinemaSession(String movieTitle, int totalSeats) {
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Количество мест должно быть положительным");
        }
        this.movieTitle = movieTitle;
        this.totalSeats = totalSeats;
    }

    public void reserveSeat(int seatNumber) {
        validateSeatNumber(seatNumber);
        // add() возвращает false, если такое место уже содержится в Set
        if (!reservedSeats.add(seatNumber)) {
            throw new IllegalStateException("Место уже занято: " + seatNumber);
        }
    }

    public void cancelReservation(int seatNumber) {
        validateSeatNumber(seatNumber);
        // remove() возвращает false, если брони на это место не было
        if (!reservedSeats.remove(seatNumber)) {
            throw new IllegalStateException("Место не было забронировано: " + seatNumber);
        }
    }

    public boolean isSeatReserved(int seatNumber) {
        validateSeatNumber(seatNumber);
        return reservedSeats.contains(seatNumber);
    }

    public int getFreeSeatsCount() {
        return totalSeats - reservedSeats.size(); // Все места минус занятые
    }

    public void validateSeatNumber(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            throw new IllegalArgumentException("Место вне диапазона: " + seatNumber);
        }
    }
}

public class Task20Demo {
    public static void main(String[] args) {
        CinemaSession session = new CinemaSession("Интерстеллар", 10);
        session.reserveSeat(1);
        session.reserveSeat(2);
        session.reserveSeat(3);

        System.out.println("Свободных мест: " + session.getFreeSeatsCount());
        session.cancelReservation(2);
        System.out.println("Свободных мест: " + session.getFreeSeatsCount());
    }
}
