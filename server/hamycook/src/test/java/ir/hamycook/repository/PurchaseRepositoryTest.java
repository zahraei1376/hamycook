package ir.hamycook.repository;

import ir.hamycook.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchaseRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PurchaseRepository purchaseRepository;

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
    public static final Food FOOD2 = new Food("چلو کباب", FOOD_TYPE, 12500, 15L, FOOD_CENTER);
    public static final Cart CART = new Cart(USER2, FOOD, 2L, FOOD.getActualPrice());
    public static final Cart CART2 = new Cart(USER2, FOOD2, 4L, FOOD.getActualPrice());

    @Test
    public void save_successfulPurchase() {
        testEntityManager.persist(FOOD_TYPE);
        testEntityManager.persist(USER);
        testEntityManager.persist(STATE);

        testEntityManager.persist(CITY);

        FOOD_CENTER.addFoodType(FOOD_TYPE);
        testEntityManager.persist(FOOD_CENTER);
        testEntityManager.persist(FOOD);
        testEntityManager.persist(FOOD2);
        testEntityManager.persist(USER2);
        testEntityManager.persist(CART);
        testEntityManager.persist(CART2);

        double actualTotalPrice = FOOD.getActualPrice() * CART.getCount() + FOOD.getActualPrice() * CART2.getCount();
        Purchase purchase = new Purchase(CART.getUser());
        purchase.addFood(CART.getFood(), CART.getCount(), CART.getBasePrice());
        testEntityManager.remove(CART);
        purchase.addFood(CART2.getFood(), CART2.getCount(), CART2.getBasePrice());
        testEntityManager.remove(CART2);
        purchaseRepository.save(purchase);

        assertEquals(cartRepository.findAll().size(), 0);
        assertEquals(purchaseRepository.findAll().size(), 1);
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchase.getId());
        assertTrue(optionalPurchase.isPresent());
        assertThat(optionalPurchase.get().getFoods()).hasSize(2);
        assertEquals(optionalPurchase.get().getTotalPrice(), actualTotalPrice);
    }
}