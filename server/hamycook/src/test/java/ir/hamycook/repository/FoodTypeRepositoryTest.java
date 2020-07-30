package ir.hamycook.repository;

import ir.hamycook.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FoodTypeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public static final String FOOD_TYPE_NAME = "فست فوت";

    @Test
    public void save_storeAFoodCenterType() {
        FoodType foodType = new FoodType(FOOD_TYPE_NAME);

        assertEquals(foodTypeRepository.findAll().size(), 0);

        FoodType savedFoodType = foodTypeRepository.save(foodType);

        assertEquals(foodTypeRepository.findAll().size(), 1);
        assertThat(savedFoodType).hasFieldOrPropertyWithValue("name", FOOD_TYPE_NAME);
    }

}