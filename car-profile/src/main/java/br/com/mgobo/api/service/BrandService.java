package br.com.mgobo.api.service;

import br.com.mgobo.api.entities.Brand;
import br.com.mgobo.api.entities.Brand_;
import br.com.mgobo.api.repository.BrandRepository;
import br.com.mgobo.web.mappers.BrandMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.mgobo.api.HttpErrorsMessage.*;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final EntityManagerFactory entityManagerFactory;

    public ResponseEntity<?> save(Brand brand) {
        try {
            brand.setCreatedAt(LocalDateTime.now());
            brand = brandRepository.save(brand);
            return ResponseEntity.created(new URI("/find/%s".formatted(brand.getId()))).body(CREATED.getMessage().formatted(brand.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandService[save]", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(Brand brand) {
        try {
            brand.setUpdatedAt(LocalDateTime.now());
            brand = brandRepository.save(brand);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ACCEPTED.getMessage().formatted(brand.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandService[update]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
            Root<Brand> brandRoot = criteriaQuery.from(Brand.class);
            List<Brand> brandCollection = entityManager.createQuery(criteriaQuery.select(brandRoot)).getResultList();
            return ResponseEntity.ok(brandCollection.stream().map(brand -> BrandMapper.INSTANCE.toDto(brand)).toList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandService[findAll]", e.getMessage()));
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public ResponseEntity<?> findById(Long id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
            Root<Brand> brandRoot = criteriaQuery.from(Brand.class);
            Optional<TypedQuery<Brand>> brand = Optional.ofNullable(entityManager.createQuery(criteriaQuery.select(brandRoot).where(criteriaBuilder.equal(brandRoot.get(Brand_.id), id))));
            if(brand.isPresent()) {
                return ResponseEntity.ok(BrandMapper.INSTANCE.toDto(brand.get().getSingleResult()));
            }
            return null;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("BrandService[findById %s]".formatted(id), e.getMessage()));
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }
}
