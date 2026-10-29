package ru.yandex.practicum.service.event.participation;

import ru.yandex.practicum.dto.participation.EventRequestStatusUpdateRequest;
import ru.yandex.practicum.dto.participation.EventRequestStatusUpdateResult;
import ru.yandex.practicum.dto.participation.ParticipationRequestDto;

import java.util.List;

public interface EventRequestService {

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResult updateRequestStatuses(Long userId, Long eventId,
                                                         EventRequestStatusUpdateRequest updateRequest);
}