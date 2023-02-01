package technikumbackendfrontendproject.Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity(name = "registration")
public class Registration {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "location")
    private String location;

    @Column(name = "street")
    private String street;

    @Column(name = "streetNumber")
    private String streetnumber;

    
    // CONSTRUCTORS
    
    public Registration(Long id, String firstName, String lastName, String eMail, String postcode, String location, String street, String streetnumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.postcode = postcode;
        this.location = location;
        this.street = street;
        this.streetnumber = streetnumber;
    }
    
    public Registration(String firstName, String lastName, String eMail, String postcode, String location, String street, String streetnumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.postcode = postcode;
        this.location = location;
        this.street = street;
        this.streetnumber = streetnumber;
    }

    public Registration() {
        }
            

    // GETTER & SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }
}

/*
 * @Entity
 * Wie die Tabelle heißen wird
 * 
 * Klasse Registration
 * Erstellen ID mit typ Long
 * @ID -validation
 * @GeneratedVAlue generiert ID automatisch
 * @Column dafür wird ne Spalte verwendet
 * @Column macht ned Spalte mit firstName und speichert den String firstName hinein
 * 
 * Unten befindet sich Constructor siehe Kommentar
 * Constructor overload (mehrer Constructoren)
 * Einen ohne ID, und leeren Constructor (leeres Objekt (von Klasse Registration) für den Methodenaufruf, zwecks Einfachheit, kann auch enstehen wenn werte fehlen)
 * 
 * Getter und Setter für alles generiert, zunächst leer, dann werden werte eingefüllt (automatisch)
 */