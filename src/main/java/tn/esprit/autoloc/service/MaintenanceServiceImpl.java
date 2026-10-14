package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImpl(IMaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance create(Maintenance maintenance) { return maintenanceRepository.save(maintenance); }

    @Override
    public Maintenance findById(Long id) { return maintenanceRepository.findById(id).orElse(null); }

    @Override
    public List<Maintenance> findAll() { return maintenanceRepository.findAll(); }

    @Override
    public Maintenance update(Long id, Maintenance maintenance) { maintenance.setIdMaintenance(id); return maintenanceRepository.save(maintenance); }

    @Override
    public void deleteById(Long id) { maintenanceRepository.deleteById(id); }
}
