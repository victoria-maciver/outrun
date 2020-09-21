package uk.co.outrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uk.co.outrun.model.DogNum;
import uk.co.outrun.repository.DogNumRepository;
import uk.co.outrun.repository.DogRepository;

@Service
public class DogNumServiceImpl implements DogNumService {

    @Autowired
    public DogNumServiceImpl(DogRepository repository) {
    }

    @Autowired
    DogNumRepository dogNumRepository;

    @Override
    public int getNext() {
        try {
            DogNum last = dogNumRepository.findTopByOrderByIdDesc();
            int lastNum = last.seq;
            DogNum next = new DogNum(lastNum + 1);
            dogNumRepository.save(next);
            return next.seq;
        } catch (NullPointerException e) {
            dogNumRepository.save(new DogNum(0));
            return 1;
        }
    }
}
