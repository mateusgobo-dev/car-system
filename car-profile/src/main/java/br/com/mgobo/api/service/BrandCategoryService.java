package br.com.mgobo.api.service;

import br.com.mgobo.api.entities.BrandCategory;
import br.com.mgobo.api.repository.BrandCategoryRepository;
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
public class BrandCategoryService {
    private final BrandCategoryRepository brandCategoryRepository;

    public ResponseEntity<?> save(BrandCategory brandCategory) {
        try {
            brandCategory.setCreatedAt(LocalDateTime.now());
            brandCategory.setUpdatedAt(LocalDateTime.now());
            brandCategory = brandCategoryRepository.save(brandCategory);
            return ResponseEntity.created(new URI("/find/%s".formatted(brandCategory.getId()))).body(CREATED.getMessage().formatted(brandCategory.getCategory().toString() + " x" + brandCategory.getBrand().toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandCategoryService[save]", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(BrandCategory brandCategory) {
        try {
            brandCategory.setUpdatedAt(LocalDateTime.now());
            brandCategory = brandCategoryRepository.save(brandCategory);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ACCEPTED.getMessage().formatted(brandCategory.getCategory().toString() + " x" + brandCategory.getBrand().toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandCategoryService[update]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(brandCategoryRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandCategoryService[findAll]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseEntity.ok(brandCategoryRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandCategoryService[findById %s]".formatted(id), e.getMessage()));
        }
    }
}
