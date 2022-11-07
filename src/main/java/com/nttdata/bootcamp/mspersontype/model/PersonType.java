package com.nttdata.bootcamp.mspersontype.model;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class PersonType.
 * PersonType microservice class PersonType.
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonType {

    @Id
    private String id;

    @NotNull
    private String personType;

    @NotNull
    private String value;
    
}
