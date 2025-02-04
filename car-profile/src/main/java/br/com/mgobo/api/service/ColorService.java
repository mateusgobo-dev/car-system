package br.com.mgobo.api.service;

import br.com.mgobo.api.entities.Color;
import br.com.mgobo.api.repository.ColorRepository;
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
public class ColorService {
    private final ColorRepository colorRepository;

    public ResponseEntity<?> save(Color color) {
        try {
            color.setCreatedAt(LocalDateTime.now());
            color.setUpdatedAt(LocalDateTime.now());
            color = colorRepository.save(color);
            return ResponseEntity.created(new URI("/find/%s".formatted(color.getId()))).body(CREATED.getMessage().formatted(color.getDescription()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("ColorService[save]", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(Color color) {
        try {
            Color colorUpdate = Optional.of(this.colorRepository.findById(color.getId())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).get();
            colorUpdate.setDescription(color.getDescription());
            colorUpdate.setUpdatedAt(LocalDateTime.now());
            color = colorRepository.save(colorUpdate);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ACCEPTED.getMessage().formatted(color.getDescription()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("ColorService[update]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(colorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("ColorService[findAll]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseEntity.ok(colorRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("ColorService[findById %s]".formatted(id), e.getMessage()));
        }
    }
}
