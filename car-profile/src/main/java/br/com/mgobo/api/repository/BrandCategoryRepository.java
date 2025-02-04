package br.com.mgobo.api.repository;

import br.com.mgobo.api.entities.BrandCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandCategoryRepository extends JpaRepository<BrandCategory, Long> {
}
