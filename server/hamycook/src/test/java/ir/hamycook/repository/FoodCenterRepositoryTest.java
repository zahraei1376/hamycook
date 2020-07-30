package ir.hamycook.repository;

import ir.hamycook.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FoodCenterRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FoodCenterRepository foodCenterRepository;

    @Test
    public void save_storeFoodCenter() {
        User user = new User("111111", "111111", "eh");
        State state = new State("stateName");
        City city = new City("city", state);
        FoodType foodType = new FoodType("فست فوت");

        State persistedState = testEntityManager.persist(state);
        city.setState(persistedState);
        System.out.println(city.getCityId());
        User persistUser = testEntityManager.persist(user);
        City persistCity = testEntityManager.persist(city);
        FoodType persistedFoodType = testEntityManager.persist(foodType);

        System.out.println(city.getCityId());

        FoodCenter foodCenter = new FoodCenter("اغذيه مسعود", "بابل خيابان شريعتي", "11111111111",
                new Time(11, 0, 0), new Time(23, 0, 0), city, user);
        foodCenter.addFoodType(persistedFoodType);
        FoodCenter savedFoodCenter = foodCenterRepository.save(foodCenter);
        assertNotNull(savedFoodCenter);
        Optional<FoodCenter> optionalFoodCenter = foodCenterRepository.findById(savedFoodCenter.getId());
        assertTrue(optionalFoodCenter.isPresent());
        assertEquals(optionalFoodCenter.get().getOwner(), persistUser);
        assertEquals(optionalFoodCenter.get().getCity(), persistCity);
        assertEquals(optionalFoodCenter.get().getName(), "اغذيه مسعود");

    }

    @Test
    public void findByName_getFoodCentersWithName() {
        User user = new User("111111", "111111", "eh");
        State state = new State("stateName");
        City city = new City("city", state);
        FoodType foodType1 = new FoodType("فست فوت");
        FoodType foodType2 = new FoodType("رستوران");

        State persistedState = testEntityManager.persist(state);
        city.setState(persistedState);
        User persistUser = testEntityManager.persist(user);
        City persistCity = testEntityManager.persist(city);
        FoodType persistedFoodType1 = testEntityManager.persist(foodType1);
        FoodType persistedFoodType2 = testEntityManager.persist(foodType2);
        FoodCenter foodCenter1 = new FoodCenter("اغذيه مسعود", "بابل خيابان شريعتي", "11111111111",
                new Time(11, 0, 0), new Time(23, 0, 0), persistCity, persistUser);
        foodCenter1.addFoodType(persistedFoodType1);
        FoodCenter foodCenter2 = new FoodCenter("اغذيه مسعود", "ميدان حمزه كلاه", "11251111111",
                new Time(11, 0, 0), new Time(23, 0, 0), persistCity, persistUser);
        foodCenter2.addFoodType(persistedFoodType2);

        FoodCenter persistedFoodCenter1 = testEntityManager.persist(foodCenter1);
        FoodCenter persistedFoodCenter2 = testEntityManager.persist(foodCenter2);


        List<FoodCenter> foodCenters = foodCenterRepository.findByName("اغذيه مسعود");

        assertNotNull(foodCenters);
        assertThat(foodCenters).hasSize(2).contains(persistedFoodCenter1, persistedFoodCenter2);
    }

    @Test
    public void save_updateFoodCenterType() {
        User user = new User("111111", "111111", "eh");
        State state = new State("stateName");
        City city = new City("city", state);
        FoodType foodType1 = new FoodType("فست فوت");
        FoodType foodType2 = new FoodType("رستوران");

        State persistedState = testEntityManager.persist(state);
        city.setState(persistedState);
        User persistUser = testEntityManager.persist(user);
        City persistCity = testEntityManager.persist(city);
        testEntityManager.persist(foodType1);
        testEntityManager.persist(foodType2);
        FoodCenter foodCenter = new FoodCenter("اغذيه مسعود", "بابل خيابان شريعتي", "11111111111",
                new Time(11, 0, 0), new Time(23, 0, 0), persistCity, persistUser);

        foodCenter.addFoodType(foodType1);
        foodCenter.addFoodType(foodType2);

        foodCenterRepository.save(foodCenter);

        assertEquals(foodCenterRepository.findByName("اغذيه مسعود").get(0).getFoodType().size(),
                2);

        foodCenter.removeFoodType(foodType1);

        assertEquals(foodCenterRepository.findByName("اغذيه مسعود").get(0).getFoodType().size(),
                1);
    }
}