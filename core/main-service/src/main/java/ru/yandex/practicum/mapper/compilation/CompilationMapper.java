package ru.yandex.practicum.mapper.compilation;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.practicum.dto.compilation.CompilationDto;
import ru.yandex.practicum.dto.compilation.NewCompilationDto;
import ru.yandex.practicum.mapper.event.EventMapper;
import ru.yandex.practicum.model.Compilation;
import ru.yandex.practicum.model.Event;

import java.util.List;
import java.util.Map;

@Mapper (componentModel = "spring", uses = EventMapper.class)
public interface CompilationMapper {
    CompilationDto toDto(Compilation compilation,@Context Map<Long,Long> viewsMap);

    @Mapping(target = "events", source = "events")
    Compilation toEntity(NewCompilationDto newCompilationDto, List<Event> events);
}