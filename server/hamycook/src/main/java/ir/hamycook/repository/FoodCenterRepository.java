package ir.hamycook.repository;

import ir.hamycook.entity.FoodCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodCenterRepository extends JpaRepository<FoodCenter, Long> {

    List<FoodCenter> findByName(String foodCenterName);

}
