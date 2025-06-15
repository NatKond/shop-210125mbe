package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntityTemplate;

    @BeforeAll
    static void setStart() {
        System.out.println(YELLOW + "Set start in progress (only one time before all the tests)" + RESET);
    }

    @AfterAll
    static void setEnd() {
        System.out.println(YELLOW + "Set end in progress (only one time after all the tests)" + RESET);
    }

    @BeforeEach
    void setUp() {
        System.out.println(YELLOW + "Set up in progress (multiple times before each test)" + RESET);
        userEntityTemplate = UserEntity.builder()
                .name("TestUser")
                .email("test@i.com")
                .build();
        userEntityTemplate = userRepository.save(userEntityTemplate);
    }

    @AfterEach
    void tearDown() {
        System.out.println(YELLOW + "Tear down in progress (multiple times after each test)" + RESET);
        userRepository.delete(userEntityTemplate);
    }

    @Test
    void findByEmailNativeQueryTest() {
        //given
        UserEntity userEntityExpected = userEntityTemplate;
        String emailExpected = userEntityExpected.getEmail();

        //when
        UserEntity userEntityActual = userRepository.findByEmailNativeQuery(emailExpected).orElseThrow();

        //then
        assertNotNull(userEntityActual);
        assertEquals(emailExpected, userEntityActual.getEmail());
        assertEquals(userEntityExpected, userEntityActual);
    }

    @Test
    void saveUpdateTest() {
        //given
        Long expectedId = userEntityTemplate.getUserId();
        UserEntity userEntityExpected = userRepository.findById(expectedId).orElseThrow();
        String newNameUserExpected = "NewTestUser";
        userEntityExpected.setName(newNameUserExpected);

        //when
        UserEntity userEntityActual = userRepository.save(userEntityExpected);

        //then
        assertNotNull(userEntityActual);
        assertNotNull(userEntityActual.getUserId());
        assertEquals(userEntityExpected.getUserId(), userEntityActual.getUserId());
        assertEquals(userEntityExpected, userEntityActual);
    }

    @Test
    void findByNameHql() {
        //given
        UserEntity userEntityExpected = userEntityTemplate;
        String name = userEntityExpected.getName();

        //when
        List<UserEntity> userEntityListActual = userRepository.findByNameHql(name);

        //then
        assertNotNull(userEntityListActual);
        assertEquals(1, userEntityListActual.size());
        assertEquals(userEntityExpected, userEntityListActual.getFirst());
    }

    @Test
    void findByPhoneNumber() {
        //given
        UserEntity userEntityExpected = userEntityTemplate;
        String phone = userEntityExpected.getPhoneNumber();

        //when
        UserEntity userEntityActual = userRepository.findByPhoneNumber(phone).orElse(null);

        //then
        assertNotNull(userEntityActual);
        assertEquals(userEntityExpected, userEntityActual);
    }

    @Test
    void setPhoneNumber() {
        // given
        UserEntity userEntityExpected = userEntityTemplate;
        String newPhoneNumber = "+49111222222222";
        userEntityExpected.setPhoneNumber(newPhoneNumber);
        int linesAffectedExpected = 1;

        //when
        int linesAffectedActual = userRepository.setPhoneNumber(userEntityExpected.getUserId(), newPhoneNumber);
        UserEntity userEntityActual = userRepository.findById(userEntityExpected.getUserId()).orElse(null);

        //then
        assertEquals(linesAffectedExpected, linesAffectedActual);
        assertNotNull(userEntityActual);
        assertEquals(userEntityExpected,userEntityActual);
    }

    @Test
    void findByNameContainingAndEmail() {
        //given
        UserEntity userEntityExpected = userEntityTemplate;
        String name = userEntityTemplate.getName().substring(0, 4);
        String email = userEntityTemplate.getEmail();

        //when
        List<UserEntity> userEntityListActual = userRepository.findByNameContainingAndEmail(name, email);

        //then
        assertNotNull(userEntityListActual);
        assertEquals(1, userEntityListActual.size());
        assertEquals(userEntityExpected, userEntityListActual.getFirst());
    }
}