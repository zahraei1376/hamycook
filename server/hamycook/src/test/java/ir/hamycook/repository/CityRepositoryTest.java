package ir.hamycook.repository;

import ir.hamycook.entity.City;
import ir.hamycook.entity.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CityRepository cityRepository;

    public static final String STATE_NAME = "مازندران";
    public static final String BABOL = "بابل";
    public static final String SARI = "ساری";

    @Test
    public void store_saveCity() {
        State state = new State(STATE_NAME);
        State persistedState = testEntityManager.persist(state);
        City city = new City(BABOL, persistedState);
        City savedCity = cityRepository.save(city);
        assertNotNull(savedCity);
        assertEquals(savedCity.getState(), persistedState);
        assertEquals(savedCity.getName(), BABOL);
        assertNotNull(savedCity.getCityId());
    }

    @Test
    public void delete_stateDelete() {

        State state = new State(STATE_NAME);
        City city1 = new City(BABOL, state);
        city1.setState(state);
        City city2 = new City(SARI, state);
        city2.setState(state);

        State persistedState = testEntityManager.persist(state);
        City persistedCity1 = testEntityManager.persist(city1);
        City persistedCity2 = testEntityManager.persist(city2);

        assertEquals(cityRepository.findAll().size(), 2);

        cityRepository.deleteById(persistedCity1.getCityId());
        assertEquals(cityRepository.findAll().size(), 1);
        Optional<City> optionalCity2 = cityRepository.findById(persistedCity2.getCityId());
        assertTrue(optionalCity2.isPresent());
        assertEquals(optionalCity2.get().getState(), persistedState);
    }

    @Test
    public void findByName_getCityByName() {
        State state = new State(STATE_NAME);
        testEntityManager.persist(state);
        City city = new City(BABOL, state);
        testEntityManager.persist(city);
        Optional<City> optionalCity = cityRepository.findByName(BABOL);
        assertTrue(optionalCity.isPresent());
        assertEquals(optionalCity.get().getName(), BABOL);
        assertEquals(optionalCity.get().getState(), state);
    }


    @Test
    public void save_updateCity() {
        State state = new State(STATE_NAME);
        State persistedState = testEntityManager.persist(state);
        State state2 = new State(STATE_NAME + STATE_NAME);
        State persistedState2 = testEntityManager.persist(state2);
        City city1 = new City(BABOL, state);
        city1.setState(state);
        City persistedCity = testEntityManager.persist(city1);
        persistedCity.setState(state2);
        cityRepository.save(persistedCity);

        Optional<City> optionalCity = cityRepository.findByName(BABOL);
        assertTrue(optionalCity.isPresent());
        assertEquals(optionalCity.get().getState(), persistedState2);
    }

}