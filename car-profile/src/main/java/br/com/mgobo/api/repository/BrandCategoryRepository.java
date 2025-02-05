package br.com.mgobo.api.repository;

import br.com.mgobo.api.entities.Brand;
import br.com.mgobo.api.entities.BrandCategory;
import br.com.mgobo.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandCategoryRepository extends JpaRepository<BrandCategory, Long> {
    BrandCategory findByBrandAndCategory(Brand brand, Category category);
}
