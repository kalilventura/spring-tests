package br.com.github.kalilventura.tests.book;

import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

@Service
public class DaysCalculatorUseCase {
    private final BookingRepository bookingRepository;

    public DaysCalculatorUseCase(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public int daysCalculator(String name) {
        Optional<Booking> booking = bookingRepository.findByReserveName(name);
        if (booking.isEmpty())
            return 0;

        return Period.between(booking.get().getCheckIn(), booking.get().getCheckOut()).getDays();
    }
}
