package br.com.mgobo.api.service;

import br.com.mgobo.api.entities.Brand;
import br.com.mgobo.api.entities.Category;
import br.com.mgobo.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.mgobo.api.HttpErrorsMessage.*;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> save(Category category) {
        try {
            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(LocalDateTime.now());
            category = categoryRepository.save(category);
            return ResponseEntity.created(new URI("/find/%s".formatted(category.getId()))).body(CREATED.getMessage().formatted(category.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[save]", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(Category category) {
        try {
            Category categoryUpdate = Optional.of(this.categoryRepository.findById(category.getId())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setUpdatedAt(LocalDateTime.now());
            category = categoryRepository.save(categoryUpdate);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ACCEPTED.getMessage().formatted(category.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[update]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(categoryRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[findAll]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseEntity.ok(categoryRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CategoryService[findById %s]".formatted(id), e.getMessage()));
        }
    }
}
