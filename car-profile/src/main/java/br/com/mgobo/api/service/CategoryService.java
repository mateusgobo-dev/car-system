package br.com.mgobo.api.service;

import br.com.mgobo.api.entities.Category;
import br.com.mgobo.api.repository.CategoryRepository;
import br.com.mgobo.web.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

import static br.com.mgobo.api.HttpErrorsMessage.*;
import static br.com.mgobo.web.mappers.CategoryMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> save(Category category) {
        try {
            category.setCreatedAt(LocalDateTime.now());
            category = categoryRepository.save(category);
            return ResponseEntity.created(new URI("/find/%s".formatted(category.getId()))).body(CREATED.getMessage().formatted(category.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[save]", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(Category category) {
        try {
            category.setUpdatedAt(LocalDateTime.now());
            category = categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ACCEPTED.getMessage().formatted(category.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[update]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(categoryRepository.findAll().stream().map(INSTANCE::toDto).toList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[findAll]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseEntity.ok(INSTANCE.toDto(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(NOT_FOUND.getMessage()))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[findById %s]".formatted(id), e.getMessage()));
        }
    }
}
