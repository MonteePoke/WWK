package kurlyk.controllers;

import kurlyk.models.Usver;
import kurlyk.models.UsverLabWorkAccess;
import kurlyk.models.UsverProgressLabWork;
import kurlyk.services.usver.UsverService;
import kurlyk.services.usverProgress.UsverProgressService;
import kurlyk.transfer.UsverLabWorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UsverService usverService;
    @Autowired
    private UsverProgressService usverProgressService;

    /*
        Usver
     */
    @GetMapping("/usvers")
    public List<Usver> getUsvers() {
        return usverService.findAll();
    }

    @GetMapping("/usvers/{usver-id}")
    public Usver getUsver(@PathVariable("usver-id") Long usverId) {
        return usverService.findOne(usverId);
    }

    @PostMapping("/usvers")
    public ResponseEntity<Object> addUsver(@RequestBody Usver usver) {
        usverService.signUp(usver);
        return ResponseEntity.ok().build();
    }

    /*
        UsverProgress
     */
    @GetMapping("/usver/progress")
    public List<UsverProgressLabWork> getUsverProgress(
            @RequestParam("usverId") Long usverId,
            @RequestParam("labWorkId") Long labWorkId
    ) {
        return usverProgressService.getUsverProgress(
                UsverLabWorkDto
                        .builder()
                        .usverId(usverId)
                        .labWorkId(labWorkId)
                        .build()
        );
    }

    @PostMapping("/usver/progress")
    public Long saveUsverProgress(@RequestBody UsverProgressLabWork usverProgressLabWork) {
        return usverProgressService.saveUsverProgress(usverProgressLabWork);
    }

    @DeleteMapping("/usver/progress/{id}")
    public ResponseEntity<Object> deleteUsverProgress(@PathVariable("id") Long id) {
        try {
            usverProgressService.deleteUsverProgress(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


    /*
        UsverLabWorkAccess
    */
    @GetMapping("/usver/access")
    public Optional<UsverLabWorkAccess> getUsverLabWorkAccess(
            @RequestParam("usverId") Long usverId,
            @RequestParam("labWorkId") Long labWorkId
    ) {
        return usverProgressService.getUsverLabWorkAccess(
                UsverLabWorkDto
                        .builder()
                        .usverId(usverId)
                        .labWorkId(labWorkId)
                        .build()
        );
    }

    @PostMapping("/usver/access")
    public Long saveUsverLabWorkAccess(@RequestBody UsverLabWorkAccess usverLabWorkAccess) {
        return usverProgressService.saveUsverLabWorkAccess(usverLabWorkAccess);
    }

    @DeleteMapping("/usver/access/{id}")
    public ResponseEntity<Object> deleteUsverLabWorkAccess(@PathVariable("id") Long id) {
        try {
            usverProgressService.deleteUsverLabWorkAccess(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
