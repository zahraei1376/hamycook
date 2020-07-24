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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StateRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    public static final String STATE_NAME = "مازندران";
    public static final String BABOL = "بابل";
    public static final String SARI = "ساری";

    @Test
    public void save_storeState() {
        State state = new State(STATE_NAME);
        State savedState = stateRepository.save(state);

        assertThat(savedState).hasFieldOrPropertyWithValue("name", STATE_NAME);
        assertThat(savedState).hasFieldOrPropertyWithValue("cities", null);
    }

    @Test
    public void save_storeStateWithCities() {
        State state = new State(STATE_NAME);
        City city1 = new City(BABOL, state);
        City city2 = new City(SARI, state);
        state.getCities().add(city1);
        state.getCities().add(city2);
        State savedState = stateRepository.save(state);

        assertThat(savedState).hasFieldOrPropertyWithValue("name", STATE_NAME);
        assertThat(savedState.getCities()).hasSize(2);
    }

    @Test
    public void findById_getStateWithName() {
        State state = new State(STATE_NAME);
        entityManager.persist(state);

        Optional<State> optionalState = stateRepository.findById(STATE_NAME);

        assertTrue(optionalState.isPresent());
        assertEquals(optionalState.get(), state);
    }

    @Test
    public void delete_deleteState() {
        State state = new State(STATE_NAME);
        entityManager.persist(state);
        assertEquals(stateRepository.findAll().size(), 1);
        stateRepository.deleteById(STATE_NAME);
        assertEquals(stateRepository.findAll().size(), 0);
    }

    @Test
    public void delete_cascadeStateDelete() {
        State state = new State(STATE_NAME);
        City city1 = new City(BABOL, state);
        City city2 = new City(SARI, state);
        state.getCities().add(city1);
        state.getCities().add(city2);

        entityManager.persist(state);
        assertNotNull(stateRepository.findAll());
        assertEquals(stateRepository.findAll().size(), 1);
        assertEquals(cityRepository.findAll().size(), 2);

        stateRepository.deleteById(STATE_NAME);

        assertEquals(stateRepository.findAll().size(), 0);

        assertEquals(cityRepository.findAll().size(), 0);
    }

}