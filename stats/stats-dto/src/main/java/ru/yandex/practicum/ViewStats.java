package ru.yandex.practicum;

public record ViewStats(
        String app,
        String uri,
        long hits
) {
}
