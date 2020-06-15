package uk.co.outrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.outrun.model.DogNum;
import uk.co.outrun.repository.DogNumRepository;

@Service
public class DogNumServiceImpl implements DogNumService {

    @Autowired
    DogNumRepository dogNumRepository;

    @Override
    public long getNext() {
        DogNum last = dogNumRepository.findTopByOrderByIdDesc();
        long lastNum = last.seq;
        DogNum next = new DogNum(lastNum + 1);
        dogNumRepository.save(next);
        return next.seq;
    }
}
