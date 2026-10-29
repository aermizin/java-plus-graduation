package ru.yandex.practicum.dto.event.projection;

import ru.yandex.practicum.model.Category;
import ru.yandex.practicum.model.User;

import java.time.LocalDateTime;

public interface EventShortProjection {
    String getAnnotation();

    Category getCategory();

    Long getConfirmedRequests();

    LocalDateTime getEventDate();

    Long getId();

    User getInitiator();

    Boolean getPaid();

    String getTitle();

    Double getDistance();
}