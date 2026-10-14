package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    public Reservation create(Reservation reservation) { return reservationRepository.save(reservation); }
    public Reservation findById(Long id) { return reservationRepository.findById(id).orElse(null); }
    public List<Reservation> findAll() { return reservationRepository.findAll(); }
    public Reservation update(Long id, Reservation reservation) { reservation.setIdReservation(id); return reservationRepository.save(reservation); }
    public void deleteById(Long id) { reservationRepository.deleteById(id); }
}
