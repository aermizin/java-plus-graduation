package ru.yandex.practicum.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}