package com.nttdata.bootcamp.mspersontype.application;

import com.nttdata.bootcamp.mspersontype.model.PersonType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class PersonTypeService.
 * PersonType microservice class PersonTypeService.
 */
public interface PersonTypeService {

    public Flux<PersonType> findAll();

    public Mono<PersonType> findById(String idPersonType);

    public Mono<PersonType> save(PersonType personType);

    public Mono<PersonType> update(PersonType personType, String idPersonType);

    public Mono<Void> delete(String idPersonType);

}
