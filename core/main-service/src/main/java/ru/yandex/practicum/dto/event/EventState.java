package ru.yandex.practicum.dto.event;

public enum EventState {
    PENDING,    // Ожидает модерации
    PUBLISHED,  // Опубликовано
    CANCELED    // Отменено
}