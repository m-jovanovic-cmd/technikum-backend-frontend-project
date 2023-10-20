package technikumbackendfrontendproject.Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity class representing a tax category used for calculating taxes in the system.
 * This Table is called by Product
 */
@Entity(name = "tax")
public class Tax {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "factor")
    private double factor;

    public Tax(long l, String tax, double v) {
    }

    public Tax() {

    }


    // Getter & Setter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    } 
}
