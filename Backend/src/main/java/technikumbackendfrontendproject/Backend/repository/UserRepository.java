package technikumbackendfrontendproject.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technikumbackendfrontendproject.Backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}

/*
 * Repos sind interfaces!
 * 
 * Extende zu JPA repo
 * 
 * Links sage ich Repo was ihr übergeben wird (die klasse Registration),
 * Long für die ID die er generated (siehe in model- Registration)
 * 
 * > Daten gehen in die Datenbank
 */

/*
 * Siehe erklären Autowired
 * 
 * wir instanzieren RegisterRepository(RR)
 * darinst steht die methode createRegistration
 * Dajj raufen wir registerRepo auf(Instanzname)
 * und verwenden save function (ist in library drin)
 * und übergen ihr registration
 * 
 * Wir sagen der Datenbank speichere unsere Daten!
 * 
 * Gehe zu RegisterRepostiry
 */
/*
 * @Entity
 * Wie die Tabelle heißen wird
 * 
 * Klasse Registration
 * Erstellen ID mit typ Long
 * 
 * @ID -validation
 * 
 * @GeneratedVAlue generiert ID automatisch
 * 
 * @Column dafür wird ne Spalte verwendet
 * 
 * @Column macht ned Spalte mit vorname und speichert den String vorname hinein
 * 
 * Unten befindet sich Constructor siehe Kommentar
 * Constructor overload (mehrer Constructoren)
 * Einen ohne ID, und leeren Constructor (leeres Objekt (von Klasse
 * Registration) für den Methodenaufruf, zwecks Einfachheit, kann auch enstehen
 * wenn werte fehlen)
 * 
 * Getter und Setter für alles generiert, zunächst leer, dann werden werte
 * eingefüllt (automatisch)
 */
