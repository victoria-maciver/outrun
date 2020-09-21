package uk.co.outrun.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class Dog {

    @Id
    private String id;
    private Integer dogNum;
    private String regName;
    private String regNo;
    private String chipNo;
    private List<Map<String, Object>> dogImg;
    private String breeder;
    private String owner;
    private String kennel;
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
    private Map<String, Object> colour;
    private String coat;
    private List<String> titles;
    private String coi;
    private Map<String, String> healthTests;
    private String notes;

}
