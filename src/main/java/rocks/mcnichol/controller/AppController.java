package rocks.mcnichol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    Logger log = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("Logging message");
        return new ResponseEntity<>("World", HttpStatus.OK);
    }
}
