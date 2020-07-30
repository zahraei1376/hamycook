package ir.hamycook.repository;

import ir.hamycook.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, String> {
}
