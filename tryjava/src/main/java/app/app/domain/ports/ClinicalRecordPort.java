package app.app.domain.ports;

import java.util.Date;
import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.ClinicalRecord;
import app.app.domain.model.Pet;
import app.app.domain.model.User;

public interface ClinicalRecordPort {

    public void save(ClinicalRecord record) throws Exception;

    public ClinicalRecord findById(long id) throws Exception;

    public List<ClinicalRecord> findAll() throws Exception;

    public List<ClinicalRecord> findByPet(Pet pet) throws Exception;

    public List<ClinicalRecord> findByVeterinarian(User veterinarian) throws Exception;

    public List<ClinicalRecord> findByDate(Date date) throws Exception;

    public List<ClinicalRecord> findByDateRange(Date startDate, Date endDate) throws Exception;

    public List<ClinicalRecord> findByDiagnosis(String diagnosis) throws Exception;

    public List<ClinicalRecord> findByMedicine(String medicine) throws Exception;

    public List<ClinicalRecord> findByProcedure(String procedure) throws Exception;
    
    public ClinicalRecord findByOrder(ClinicalOrder order) throws Exception;
    
    public void update(ClinicalRecord record) throws Exception;

    public void deleteById(long id) throws Exception;

    List<ClinicalRecord> findByStatus(boolean status) throws Exception;
}

