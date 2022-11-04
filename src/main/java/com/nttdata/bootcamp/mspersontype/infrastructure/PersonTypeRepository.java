package com.nttdata.bootcamp.mspersontype.infrastructure;

import com.nttdata.bootcamp.mspersontype.model.PersonType;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class PersonTypeRepository {
    private final ReactiveRedisOperations<String, PersonType> reactiveRedisOperations;

    public Flux<PersonType> findAll() {
        return this.reactiveRedisOperations.<String, PersonType>opsForHash().values("personTypes");
    }

    public Mono<PersonType> findById(String id) {
        return this.reactiveRedisOperations.<String, PersonType>opsForHash().get("personTypes", id);
    }

    public Mono<PersonType> save(PersonType personType) {
        if (personType.getId() == null) {
            String id = UUID.randomUUID().toString();
            personType.setId(id);
        }
        return this.reactiveRedisOperations.<String, PersonType>opsForHash()
                .put("personTypes", personType.getId(), personType).log().map(p -> personType);
    }

    public Mono<Void> delete(PersonType personType) {
        return this.reactiveRedisOperations.<String, PersonType>opsForHash()
                .remove("personTypes", personType.getId())
                .flatMap(p -> Mono.just(personType)).then();
    }

}
