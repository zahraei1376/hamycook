package ir.hamycook.repository;

import ir.hamycook.entity.FoodCenterType;
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
public class FoodCenterTypeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FoodCenterTypeRepository foodCenterTypeRepository;


    @Test
    public void save_storeAFoodCenterType() {
        String foodCenterTypeName = "فست فوت";
        FoodCenterType foodCenterType = new FoodCenterType(foodCenterTypeName);
        assertEquals(foodCenterTypeRepository.findAll().size(), 0);

        FoodCenterType savedFoodCenterType = foodCenterTypeRepository.save(foodCenterType);
        assertEquals(foodCenterTypeRepository.findAll().size(), 1);

        assertThat(savedFoodCenterType).hasFieldOrPropertyWithValue("name", foodCenterTypeName);
    }



}