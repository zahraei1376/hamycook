package ir.hamycook.repository;

import ir.hamycook.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Food food SET food.remainFoodNumber = :count WHERE food.id = :id")
    void updateRemainFoodNumber(@Param("id") Long id, @Param("count") Long count);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Food food SET food.discount = :discount WHERE food.id = :id")
    void updateDiscount(@Param("id") Long id, @Param("discount") double discount);

}
