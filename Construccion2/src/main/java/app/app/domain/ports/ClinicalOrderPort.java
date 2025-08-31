package app.app.domain.ports;

import java.util.Date;
import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.Person;
import app.app.domain.model.Pet;
import app.app.domain.model.User;

public interface ClinicalOrderPort {

    public void save(ClinicalOrder order) throws Exception;

    ClinicalOrder findById(long id) throws Exception;

    List<ClinicalOrder> findAll() throws Exception;

    List<ClinicalOrder> findByPet(Pet pet) throws Exception;

    List<ClinicalOrder> findByOwner(Person owner) throws Exception;

    List<ClinicalOrder> findByVeterinarian(User veterinarian) throws Exception;

    List<ClinicalOrder> findByMedicine(String medicine) throws Exception;

    List<ClinicalOrder> findByDateRange(Date startDate, Date endDate) throws Exception;

    void update(ClinicalOrder order) throws Exception;

    void deleteById(long id) throws Exception;
}

