package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    public EmployeServiceImpl(IEmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe create(Employe employe) { return employeRepository.save(employe); }

    @Override
    public Employe findById(Long id) { return employeRepository.findById(id).orElse(null); }

    @Override
    public List<Employe> findAll() { return employeRepository.findAll(); }

    @Override
    public Employe update(Long id, Employe employe) { employe.setIdEmploye(id); return employeRepository.save(employe); }

    @Override
    public void deleteById(Long id) { employeRepository.deleteById(id); }
}
