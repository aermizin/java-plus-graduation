package ru.yandex.practicum.dto.compilation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import ru.yandex.practicum.dto.event.EventShortDto;

import java.util.List;

public record CompilationDto(
        @Positive(message = "ID подборки должен быть положительным числом")
        Long id,

        boolean pinned,

        @NotBlank(message = "Заголовок подборки не должен быть пустым")
        String title,

        List<EventShortDto> events
) { }