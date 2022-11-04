package com.nttdata.bootcamp.mspersontype.controller;

import com.nttdata.bootcamp.mspersontype.application.PersonTypeService;
import com.nttdata.bootcamp.mspersontype.model.PersonType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/persontype")
@Slf4j
public class PersonTypeController {

    @Autowired
    private PersonTypeService personTypeService;

    @GetMapping
    public Mono<ResponseEntity<Flux<PersonType>>> listPersonTypes() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personTypeService.findAll()));
    }

    @GetMapping("/{idPersonType}")
    public Mono<ResponseEntity<PersonType>> getPersonTypeDetails(@PathVariable("idPersonType") String idPersonType) {
        return personTypeService.findById(idPersonType)
                .map(c -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> savePersonType(@Valid @RequestBody Mono<PersonType> personType) {
        Map<String, Object> request = new HashMap<>();
        return personType
                .flatMap(pType -> personTypeService.save(pType).map(baSv -> {
                            request.put("PersonType", baSv);
                            request.put("message", "Tipo de persona guardado con exito");
                            request.put("timestamp", new Date());
                            return ResponseEntity.created(URI.create("/api/persontype/".concat(baSv.getId())))
                                    .contentType(MediaType.APPLICATION_JSON).body(request);
                        })
                );
    }

    @PutMapping("/{idPersonType}")
    public Mono<ResponseEntity<PersonType>> editPersonType(@Valid @RequestBody PersonType personType, @PathVariable("idPersonType") String idPersonType) {
        return personTypeService.update(personType, idPersonType)
                .map(c -> ResponseEntity.created(URI.create("/api/persontype/".concat(idPersonType)))
                        .contentType(MediaType.APPLICATION_JSON).body(c));
    }

    @DeleteMapping("/{idPersonType}")
    public Mono<ResponseEntity<Void>> deletePersonType(@PathVariable("idPersonType") String idPersonType) {
        return personTypeService.delete(idPersonType)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
