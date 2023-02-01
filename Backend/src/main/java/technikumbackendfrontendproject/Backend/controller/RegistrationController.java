package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technikumbackendfrontendproject.Backend.model.Registration;
import technikumbackendfrontendproject.Backend.service.RegistrationService;

@RestController
@RequestMapping("/registration") // API
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")  //registrations
    public ResponseEntity<Long> createRegistration(@RequestBody Registration registration) {
        registrationService.createRegistration(registration);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}












/*
 * Zusammengefasst > "Macht viele coole Sachen" - Zitat Mladen
 * Haben uns das JASON vom Javascript schicken lassen, dies ist die 1. Instanz
 * 
 * @RestController > schreibt man immer wenn man Springboot verwendet
 * 
 * @RequestMapping auf welche URL(Pfad) zugegriffen wird
 * Autowired: wenn es gebraucht wird, wird eine Instanz von registrationService
 * erstellt
 * 
 * @PostMapping
 * Alle Subpfade weitermappen (Localhost 8080/registration/register > dann wird
 * die Methode public ResponeEntity ausgeführt)
 * 
 * Was passiert in methode ResponseEntity (RE): RE übernimmt Objekt Long und es
 * schickt den HTTP-Status zurück
 * 
 * @RequestBody
 * der Postrequest den wir von Javascript bekommen haben, Data von Typ JSON ein
 * Objekt, haben wir gemoddelt auf die Klasse Registration > weiter zu
 * registration.java
 * 
 * Zurück von registration.java
 * bekommen @Requestbody übergeben mit variablenname registration (wie zb String
 * Name)
 * 
 * rufen createRegistration auf und übergeben variable registration(der klasse
 * registration)
 * 
 * Wir gehen jetzt in registrationservice rein
 */
