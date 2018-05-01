package ru.spluft.remindme.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spluft.remindme.server.entity.Remind;
import ru.spluft.remindme.server.service.RemindeService;

import java.util.List;

@Service
public class RemindServiceImpl  implements RemindeService {

    @Autowired
    public RemindRepository remindRepository;

    @Override
    public List<Remind> getAll() {
        return remindRepository.findAll();
    }

    @Override
    public Remind getBiId(long id) {
        return remindRepository.getOne(id);
    }

    @Override
    public Remind save(Remind remind) {
        return remindRepository.saveAndFlush(remind);
    }

    @Override
    public void remove(long id) {
        remindRepository.deleteById(id);
    }
}
