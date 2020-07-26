package ir.hamycook.repository;

import ir.hamycook.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FoodCenterTypeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FoodCenterTypeRepository foodCenterTypeRepository;

    public static final String FOOD_CENTER_TYPE_NAME = "فست فوت";

    @Test
    public void save_storeAFoodCenterType() {
        FoodCenterType foodCenterType = new FoodCenterType(FOOD_CENTER_TYPE_NAME);

        assertEquals(foodCenterTypeRepository.findAll().size(), 0);

        FoodCenterType savedFoodCenterType = foodCenterTypeRepository.save(foodCenterType);

        assertEquals(foodCenterTypeRepository.findAll().size(), 1);
        assertThat(savedFoodCenterType).hasFieldOrPropertyWithValue("name", FOOD_CENTER_TYPE_NAME);
    }

}