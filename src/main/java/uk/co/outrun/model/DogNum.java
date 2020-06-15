package uk.co.outrun.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class DogNum {

    @Id
    private ObjectId id;

    @Getter
    @Setter
    public long seq;

    public DogNum(long seq) {
        this.seq= seq;
    }

}
