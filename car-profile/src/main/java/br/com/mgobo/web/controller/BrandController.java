package br.com.mgobo.web.controller;

import br.com.mgobo.api.service.BrandService;
import br.com.mgobo.web.dto.BrandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.mgobo.web.mappers.BrandMapper.INSTANCE;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getBrands() {
        return brandService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return brandService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BrandDto brandDto) {
        return brandService.save(INSTANCE.toEntity(brandDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BrandDto brandDto) {
        return brandService.update(INSTANCE.toEntity(brandDto));
    }
}
