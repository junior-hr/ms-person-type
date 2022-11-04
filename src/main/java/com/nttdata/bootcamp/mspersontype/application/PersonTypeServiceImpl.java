package com.nttdata.bootcamp.mspersontype.application;

import com.nttdata.bootcamp.mspersontype.exception.ResourceNotFoundException;
import com.nttdata.bootcamp.mspersontype.infrastructure.PersonTypeRepository;
import com.nttdata.bootcamp.mspersontype.model.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonTypeServiceImpl implements PersonTypeService {

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Override
    public Flux<PersonType> findAll() {
        return personTypeRepository.findAll();
    }

    @Override
    public Mono<PersonType> findById(String idPersonType) {
        return Mono.just(idPersonType)
                .flatMap(personTypeRepository::findById)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Person Type", "idPersonType", idPersonType)));
    }

    @Override
    public Mono<PersonType> save(PersonType personType) {
        return Mono.just(personType).flatMap(dc -> personTypeRepository.save(dc));
    }

    @Override
    public Mono<PersonType> update(PersonType personType, String idPersonType) {
        return personTypeRepository.findById(idPersonType)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Person Type", "idPersonType", idPersonType)))
                .flatMap(dcu -> {
                    dcu.setPersonType(personType.getPersonType());
                    dcu.setValue(personType.getValue());
                    return personTypeRepository.save(dcu);
                });
    }

    @Override
    public Mono<Void> delete(String idPersonType) {
        return personTypeRepository.findById(idPersonType)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Person Type", "idPersonType", idPersonType)))
                .flatMap(dcu -> personTypeRepository.delete(dcu));
    }
}
