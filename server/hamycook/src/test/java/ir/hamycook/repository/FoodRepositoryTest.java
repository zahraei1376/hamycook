package ir.hamycook.repository;

import ir.hamycook.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void save_saveAFood() {

        FoodType foodType = new FoodType("رستوران");
        User user = new User("09154789856", "123", "امید رضایی");
        State state = new State("مازندران");
        testEntityManager.persist(foodType);
        testEntityManager.persist(user);
        testEntityManager.persist(state);

        City city = new City("بابل", state);
        testEntityManager.persist(city);

        FoodCenter foodCenter = new FoodCenter("میثم",
                "حمزه کلاه",
                "09105784789",
                new Time(8, 0, 0),
                new Time(19, 0, 0),
                city,
                user);
        foodCenter.addFoodType(foodType);
        testEntityManager.persist(foodCenter);

        Food food = new Food("چلو کباب", foodType, 12500, 15L, foodCenter);
        foodRepository.save(food);

        Food savedFood = testEntityManager.find(Food.class, food.getId());

        assertNotNull(savedFood);
        assertEquals(savedFood.getName(), food.getName());
        assertEquals(savedFood.getFoodCenter(), foodCenter);
        assertEquals(savedFood.getFoodType(), foodType);
        assertEquals(savedFood.getActualPrice(), food.getActualPrice());
        assertEquals(savedFood.getRemainFoodNumber(), food.getRemainFoodNumber());
    }

    @Test
    public void updateRemainFoodNumber_correctNumber() {
        FoodType foodType = new FoodType("رستوران");
        User user = new User("09154789856", "123", "امید رضایی");
        State state = new State("مازندران");
        testEntityManager.persist(foodType);
        testEntityManager.persist(user);
        testEntityManager.persist(state);

        City city = new City("بابل", state);
        testEntityManager.persist(city);

        FoodCenter foodCenter = new FoodCenter("میثم",
                "حمزه کلاه",
                "09105784789",
                new Time(8, 0, 0),
                new Time(19, 0, 0),
                city,
                user);
        foodCenter.addFoodType(foodType);
        testEntityManager.persist(foodCenter);

        Food food = new Food("چلو کباب", foodType, 12500, 15L, foodCenter);
        testEntityManager.persist(food);

        foodCenter.addFood(food);
        testEntityManager.persist(foodCenter);

        foodRepository.updateRemainFoodNumber(food.getId(), 14L);

        Food updatedFood = testEntityManager.find(Food.class, food.getId());

        assertEquals(updatedFood.getRemainFoodNumber(), 14L);

    }

    @Test
    public void updateDiscount_discount() {
        FoodType foodType = new FoodType("رستوران");
        User user = new User("09154789856", "123", "امید رضایی");
        State state = new State("مازندران");
        testEntityManager.persist(foodType);
        testEntityManager.persist(user);
        testEntityManager.persist(state);

        City city = new City("بابل", state);
        testEntityManager.persist(city);

        FoodCenter foodCenter = new FoodCenter("میثم",
                "حمزه کلاه",
                "09105784789",
                new Time(8, 0, 0),
                new Time(19, 0, 0),
                city,
                user);
        foodCenter.addFoodType(foodType);
        testEntityManager.persist(foodCenter);

        Food food = new Food("چلو کباب", foodType, 12500, 15L, foodCenter);
        testEntityManager.persist(food);

        foodCenter.addFood(food);
        testEntityManager.persist(foodCenter);

        foodRepository.updateDiscount(food.getId(), 10000);

        Food updatedFood = testEntityManager.find(Food.class, food.getId());

        assertEquals(updatedFood.getDiscount(), 10000);
    }

    @Test
    public void delete_removeFoodsAfterDeleteFoodCenter() {
        FoodType foodType = new FoodType("رستوران");
        User user = new User("09154789856", "123", "امید رضایی");
        State state = new State("مازندران");
        testEntityManager.persist(foodType);
        testEntityManager.persist(user);
        testEntityManager.persist(state);

        City city = new City("بابل", state);
        testEntityManager.persist(city);

        FoodCenter foodCenter = new FoodCenter("میثم",
                "حمزه کلاه",
                "09105784789",
                new Time(8, 0, 0),
                new Time(19, 0, 0),
                city,
                user);
        foodCenter.addFoodType(foodType);
        testEntityManager.persist(foodCenter);

        Food food = new Food("چلو کباب", foodType, 12500, 15L, foodCenter);
        testEntityManager.persist(food);

        foodCenter.addFood(food);
        testEntityManager.persist(foodCenter);


        testEntityManager.remove(foodCenter);

        FoodCenter removedFoodCenter = testEntityManager.find(FoodCenter.class, foodCenter.getId());
        assertNull(removedFoodCenter);
        Food removedFood = testEntityManager.find(Food.class, food.getId());
        assertNull(removedFood);

    }

}