package ru.yandex.practicum.repository.event.participation;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.Request;

import java.util.List;

public interface EventRequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByEventId(Long eventId);

    List<Request> findAllByIdIn(List<Long> ids);
}