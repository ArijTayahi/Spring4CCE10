package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    public ReservationServiceImpl(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation create(Reservation reservation) { return reservationRepository.save(reservation); }

    @Override
    public Reservation findById(Long id) { return reservationRepository.findById(id).orElse(null); }

    @Override
    public List<Reservation> findAll() { return reservationRepository.findAll(); }

    @Override
    public Reservation update(Long id, Reservation reservation) { reservation.setIdReservation(id); return reservationRepository.save(reservation); }

    @Override
    public void deleteById(Long id) { reservationRepository.deleteById(id); }
}
