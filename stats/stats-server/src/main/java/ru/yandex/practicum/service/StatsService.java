package ru.yandex.practicum.service;

import ru.yandex.practicum.EndpointHit;
import ru.yandex.practicum.ViewStats;

import java.util.List;

public interface StatsService {
    EndpointHit saveEndpointHit(EndpointHit endpointHit);

    List<ViewStats> getViewStats(String start, String end, List<String> uris, Boolean unique);
}
