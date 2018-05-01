package ru.spluft.remindme.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spluft.remindme.server.entity.Remind;

public interface RemindRepository extends JpaRepository<Remind, Long> {
}