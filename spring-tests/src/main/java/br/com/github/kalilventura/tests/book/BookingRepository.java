package br.com.github.kalilventura.tests.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Optional<Booking> findByReserveName(String name);
}

