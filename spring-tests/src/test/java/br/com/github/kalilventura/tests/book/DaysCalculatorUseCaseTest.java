package br.com.github.kalilventura.tests.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DaysCalculatorUseCaseTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private DaysCalculatorUseCase bookingService;

    @BeforeEach
    void setUp() {
        bookingService = new DaysCalculatorUseCase(bookingRepository);

        LocalDate checkIn = LocalDate.parse("2020-11-10");
        LocalDate checkout = LocalDate.parse("2020-11-20");

        Booking booking = new Booking("1", "John", checkIn, checkout, 2);

        when(bookingRepository.findByReserveName(booking.getReserveName())).thenReturn(Optional.of(booking));
    }

    @Test
    void daysCalculator() {
        String name = "John";
        int days = bookingService.daysCalculator(name);

        assertEquals(10, days);
    }
}