package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    public Maintenance create(Maintenance maintenance) { return maintenanceRepository.save(maintenance); }
    public Maintenance findById(Long id) { return maintenanceRepository.findById(id).orElse(null); }
    public List<Maintenance> findAll() { return maintenanceRepository.findAll(); }
    public Maintenance update(Long id, Maintenance maintenance) { maintenance.setIdMaintenance(id); return maintenanceRepository.save(maintenance); }
    public void deleteById(Long id) { maintenanceRepository.deleteById(id); }
}
