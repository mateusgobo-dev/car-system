package br.com.mgobo.web.controller;

import br.com.mgobo.api.service.CarService;
import br.com.mgobo.web.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.mgobo.web.mappers.CarMapper.INSTANCE;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<?> getCars() {
        return carService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CarDto carDto) {
        return carService.save(INSTANCE.toEntity(carDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CarDto carDto) {
        return carService.update(INSTANCE.toEntity(carDto));
    }
}
