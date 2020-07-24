package ir.hamycook.repository;

import ir.hamycook.entity.City;
import ir.hamycook.entity.State;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public static final String STATE_NAME = "مازندران";
    public static final String BABOL = "بابل";
    public static final String SARI = "ساری";

    @Test
    public void delete_stateDelete() {

        State state = new State(STATE_NAME);
        City city1 = new City(BABOL, state);
        City city2 = new City(SARI, state);
        state.getCities().add(city1);
        state.getCities().add(city2);

        testEntityManager.persist(state);
        assertEquals(stateRepository.findAll().size(), 1);
        assertEquals(stateRepository.findById(STATE_NAME).get().getCities().size(), 2);
        assertEquals(cityRepository.findAll().size(), 2);

        Long cityId = stateRepository.findById(STATE_NAME).get().getCities().get(0).getCityId();

        // remove city in state
        Optional<State> optionalState = stateRepository.findById(STATE_NAME);
        State state1 = optionalState.get();
        for (int i = 0; i < state1.getCities().size(); i++) {
            if (state1.getCities().get(i).getCityId().equals(cityId)) {
                state1.getCities().remove(i);
                break;
            }
        }
        stateRepository.save(state1);

        // delete city
        cityRepository.deleteById(cityId);
        assertEquals(stateRepository.findAll().size(), 1);
        assertEquals(cityRepository.findAll().size(), 1);

        assertTrue(stateRepository.findById(STATE_NAME).isPresent());
        assertEquals(stateRepository.findById(STATE_NAME).get().getCities().size(), 1);
    }

}