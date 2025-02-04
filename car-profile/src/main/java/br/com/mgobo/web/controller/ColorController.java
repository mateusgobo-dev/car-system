package br.com.mgobo.web.controller;

import br.com.mgobo.api.entities.Color;
import br.com.mgobo.api.service.ColorService;
import br.com.mgobo.web.dto.ColorDto;
import br.com.mgobo.web.mappers.ColorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.mgobo.web.mappers.ColorMapper.INSTANCE;

@CrossOrigin(value = "http://localhost")
@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<?> getColor() {
        return colorService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return colorService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ColorDto color) {
        return colorService.save(INSTANCE.toEntity(color));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ColorDto color) {
        return colorService.update(INSTANCE.toEntity(color));
    }
}
