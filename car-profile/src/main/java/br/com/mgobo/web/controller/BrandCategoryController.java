package br.com.mgobo.web.controller;

import br.com.mgobo.api.service.BrandCategoryService;
import br.com.mgobo.web.dto.BrandCategoryDto;
import br.com.mgobo.web.dto.BrandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.mgobo.web.mappers.BrandCategoryMapper.INSTANCE;

@CrossOrigin(value = "http://localhost")
@RestController
@RequestMapping("/api/v1/brandcategory")
@RequiredArgsConstructor
public class BrandCategoryController {

    private final BrandCategoryService brandCategoryService;

    @GetMapping
    public ResponseEntity<?> getColor() {
        return brandCategoryService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return brandCategoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BrandCategoryDto brandCategoryDto) {
        return brandCategoryService.save(INSTANCE.toEntity(brandCategoryDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BrandCategoryDto brandCategoryDto) {
        return brandCategoryService.update(INSTANCE.toEntity(brandCategoryDto));
    }
}
