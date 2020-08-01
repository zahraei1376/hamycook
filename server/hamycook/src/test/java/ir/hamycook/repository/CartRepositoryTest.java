package ir.hamycook.repository;

import ir.hamycook.entity.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CartRepository cartRepository;
    public static final FoodType FOOD_TYPE = new FoodType("رستوران");
    public static final User USER = new User("09154789856", "123", "امید رضایی");
    public static final User USER2 = new User("0919389236", "1ods", "عرفان امیدی");
    public static final State STATE = new State("مازندران");
    public static final City CITY = new City("بابل", STATE);
    public static final FoodCenter FOOD_CENTER = new FoodCenter("میثم",
            "حمزه کلاه",
            "09105784789",
            new Time(8, 0, 0),
            new Time(19, 0, 0),
            CITY,
            USER);
    public static final Food FOOD = new Food("چلو کباب", FOOD_TYPE, 12500, 15L, FOOD_CENTER);

    @Test
    public void  save_successfulSaveCart() {
        testEntityManager.persist(FOOD_TYPE);
        testEntityManager.persist(USER);
        testEntityManager.persist(STATE);

        testEntityManager.persist(CITY);

        FOOD_CENTER.addFoodType(FOOD_TYPE);
        testEntityManager.persist(FOOD_CENTER);

        testEntityManager.persist(FOOD);

        // buyer user
        testEntityManager.persist(USER2);

        Cart cart = new Cart(USER2, FOOD, 2L, FOOD.getActualPrice());

        cartRepository.save(cart);

        assertEquals(cartRepository.findAll().size(), 1);

        Optional<Cart> optionalCart = cartRepository.findById(cart.getId());
        assertTrue(optionalCart.isPresent());
        assertEquals(optionalCart.get().getCount(), 2);
        assertEquals(optionalCart.get().getUser(), USER2);
        assertEquals(optionalCart.get().getFood(), FOOD);

    }

}