package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

    @Query ("SELECT pr FROM ProductEntity pr WHERE pr.discountPrice is not null")
    List<ProductEntity> findAllWithDiscountPrice();
}
