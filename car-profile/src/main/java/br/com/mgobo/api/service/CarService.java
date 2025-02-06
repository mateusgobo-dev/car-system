package br.com.mgobo.api.service;

import br.com.mgobo.api.entities.*;
import br.com.mgobo.api.repository.BrandCategoryRepository;
import br.com.mgobo.api.repository.CarRepository;
import br.com.mgobo.api.repository.ColorRepository;
import br.com.mgobo.web.mappers.CarMapper;
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

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.mgobo.api.HttpErrorsMessage.*;
import static br.com.mgobo.web.mappers.CarMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BrandCategoryRepository brandCategoryRepository;
    private final ColorRepository colorRepository;
    private final EntityManagerFactory entityManagerFactory;

    private BrandCategory findBrandCategoryByBrandAndCategory(Car car) {
        Brand brand = new Brand();
        brand.setId(car.getBrandCategory().getBrand().getId());
        Category category = new Category();
        category.setId(car.getBrandCategory().getCategory().getId());
        BrandCategory brandCategory = brandCategoryRepository.findByBrandAndCategory(brand, category);
        if (brandCategory == null) {
            brandCategory = new BrandCategory();
            brandCategory.setBrand(brand);
            brandCategory.setCategory(category);
            brandCategory = this.brandCategoryRepository.save(brandCategory);
        }
        return brandCategory;
    }

    public ResponseEntity<?> save(Car car) {
        try {
            car.setBrandCategory(this.findBrandCategoryByBrandAndCategory(car));
            car.setCreatedAt(LocalDateTime.now());
            car = carRepository.save(car);
            return ResponseEntity.created(new URI("/find/%s".formatted(car.getId()))).body(CREATED.getMessage().formatted(car.getVehicle()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CarService[save]", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(Car car) {
        try {
            car.setBrandCategory(this.findBrandCategoryByBrandAndCategory(car));
            car.setColor(this.colorRepository.findById(car.getColor().getId()).get());
            car.setUpdatedAt(LocalDateTime.now());
            car = carRepository.save(car);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ACCEPTED.getMessage().formatted(car.getVehicle()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CarService[update]", e.getMessage()));
        }
    }

    public ResponseEntity<?> findAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = criteriaQuery.from(Car.class);
            List<Car> carCollection = entityManager.createQuery(criteriaQuery.select(carRoot)).getResultList();
            return ResponseEntity.ok(carCollection.stream().map(INSTANCE::toDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CarService[findAll]", e.getMessage()));
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public ResponseEntity<?> findById(Long id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = criteriaQuery.from(Car.class);
            Optional<TypedQuery<Car>> car = Optional.ofNullable(entityManager.createQuery(criteriaQuery.select(carRoot).where(criteriaBuilder.equal(carRoot.get(Car_.id), id))));
            if (car.isPresent()) {
                return ResponseEntity.ok(INSTANCE.toDto(car.get().getSingleResult()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST.getMessage().formatted("CarService[findById %s]".formatted(id), e.getMessage()));
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }
}
