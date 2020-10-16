package uk.co.outrun.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String regName;
    private String regNo;
    private String chipNo;
    private String dogImg;
    private String breederId;
    private String ownerId;
    private String kennelId;
    private Integer sireId;
    private Integer damId;
    private String callName;
    private String sex;
    private String dateOfBirth;
    private String dateOfDeath;
    private String landOfBirth;
    private String landOfStanding;
    private String size;
    private String weight;
    private String coat;
    private String coi;
    private String notes;

}
