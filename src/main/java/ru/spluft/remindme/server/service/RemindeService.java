package ru.spluft.remindme.server.service;

import ru.spluft.remindme.server.entity.Remind;

import java.util.List;

public interface RemindeService {

    List<Remind> getAll();
    Remind getBiId(long id);
    Remind save(Remind remind);
    void remove(long id);
}
