package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    public Employe create(Employe employe) { return employeRepository.save(employe); }
    public Employe findById(Long id) { return employeRepository.findById(id).orElse(null); }
    public List<Employe> findAll() { return employeRepository.findAll(); }
    public Employe update(Long id, Employe employe) { employe.setIdEmploye(id); return employeRepository.save(employe); }
    public void deleteById(Long id) { employeRepository.deleteById(id); }
}
