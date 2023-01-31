package technikumbackendfrontendproject.Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/*
 * @Entity
 * Wie die Tabelle heißen wird
 * 
 * Klasse Registration
 * Erstellen ID mit typ Long
 * @ID -validation
 * @GeneratedVAlue generiert ID automatisch
 * @Column dafür wird ne Spalte verwendet
 * @Column macht ned Spalte mit vorname und speichert den String vorname hinein
 * 
 * Unten befindet sich Constructor siehe Kommentar
 * Constructor overload (mehrer Constructoren)
 * Einen ohne ID, und leeren Constructor (leeres Objekt (von Klasse Registration) für den Methodenaufruf, zwecks Einfachheit, kann auch enstehen wenn werte fehlen)
 * 
 * Getter und Setter für alles generiert, zunächst leer, dann werden werte eingefüllt (automatisch)
 */

@Entity(name = "registration")
public class Registration {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "nachname")
    private String nachname;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "plz")
    private String plz;

    @Column(name = "ort")
    private String ort;

    @Column(name = "strasse")
    private String strasse;

    @Column(name = "hausnummer")
    private String hausnummer;

    // CONSTRUCTORS

    public Registration(Long id, String vorname, String nachname, String eMail, String plz, String ort, String strasse,
            String hausnummer) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.eMail = eMail;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public Registration(String vorname, String nachname, String eMail, String plz, String ort, String strasse,
            String hausnummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.eMail = eMail;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }
}
