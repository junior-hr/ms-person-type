package com.nttdata.bootcamp.mspersontype.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;

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
