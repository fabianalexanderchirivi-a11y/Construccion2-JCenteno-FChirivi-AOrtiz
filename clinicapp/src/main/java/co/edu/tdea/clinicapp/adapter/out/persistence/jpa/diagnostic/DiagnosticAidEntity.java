package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.diagnostic;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnostic_aids")
public class DiagnosticAidEntity {

    @Id
    @Column(name = "id", length = 10, nullable = false)
    private String id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "unit_cost", nullable = false)
    private Integer unitCost;

    public String getId() { return id; }
    public void setId(String id) { this.id = id != null ? id.trim() : null; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name != null ? name.trim() : null; }

    public Integer getUnitCost() { return unitCost; }
    public void setUnitCost(Integer unitCost) { this.unitCost = unitCost; }
}
