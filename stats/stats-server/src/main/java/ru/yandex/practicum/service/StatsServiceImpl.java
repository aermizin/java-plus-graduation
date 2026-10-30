package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.EndpointHit;
import ru.yandex.practicum.ViewStats;
import ru.yandex.practicum.exception.ValidationDataException;
import ru.yandex.practicum.mapper.StatsMapper;
import ru.yandex.practicum.model.EndpointHitEntity;
import ru.yandex.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    @Override
    public EndpointHit saveEndpointHit(EndpointHit endpointHit) {
        log.info("Сохранение EndpointHit uri= {}, timestamp= {}", endpointHit.uri(), endpointHit.timestamp());
        EndpointHitEntity endpointHitEntity = StatsMapper.toEntity(endpointHit);
        EndpointHitEntity responseEntity = statsRepository.save(endpointHitEntity);
        log.info("Сохранение успешно id= {}", responseEntity.getId());

        return StatsMapper.toEndpointHit(responseEntity);
    }

    @Override
    public List<ViewStats> getViewStats(String start, String end, List<String> uris, Boolean unique) {
        log.info("Получение списка ViewStats от: {} до: {}", start, end);
        LocalDateTime startDateTime = LocalDateTime.parse(start, FORMATTER);
        LocalDateTime endDateTime = LocalDateTime.parse(end, FORMATTER);
        validateDates(startDateTime, endDateTime);
        String[] urisArray = uris.toArray(String[]::new);

        List<Object[]> results;
        if (!uris.isEmpty()) {
            results = unique ? statsRepository.findUniqueStatsWithUris(startDateTime, endDateTime, urisArray)
                    : statsRepository.findStatsWithUris(startDateTime, endDateTime, urisArray);
        } else {
            results = unique ? statsRepository.findUniqueStats(startDateTime, endDateTime)
                    : statsRepository.findStats(startDateTime, endDateTime);
        }

        return mapToViewStats(results);
    }

    private List<ViewStats> mapToViewStats(List<Object[]> results) {
        List<ViewStats> response = results.stream()
                .map(obj -> new ViewStats((String) obj[0], (String) obj[1], (long)obj[2]))
                .toList();
        log.info("ViewStats успешно получены");

        return response;
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(LocalDateTime.now())) {
            log.warn("Передана дата начала позже текущего момента start= {}, now= {}", start, LocalDateTime.now());
            throw new ValidationDataException("Дата начала не должна быть позднее текущего момента");
        }
        if (start.isAfter(end)) {
            log.warn("Передана дата начала позже даты конца start= {}, end= {}", start, end);
            throw new ValidationDataException("Дата начала не может быть раньше конца");
        }
    }
}