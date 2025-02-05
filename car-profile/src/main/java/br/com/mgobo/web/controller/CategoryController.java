package br.com.mgobo.web.controller;

import br.com.mgobo.api.service.CategoryService;
import br.com.mgobo.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.mgobo.web.mappers.CategoryMapper.INSTANCE;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(INSTANCE.toEntity(categoryDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(INSTANCE.toEntity(categoryDto));
    }
}
