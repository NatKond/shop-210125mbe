package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private  UserEntity userEntityTemplate;

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
        final String emailExpected = "test@i.com";

        //when
        UserEntity userEntityActual = userRepository.findByEmailNativeQuery(emailExpected).orElseThrow();

        //then
        assertNotNull(userEntityActual);
        assertEquals(emailExpected, userEntityActual.getEmail());
    }

    @Test
    void saveUpdateTest() {
        //given
        Long expectedId = userEntityTemplate.getUserId();;
        UserEntity userEntityExpected = userRepository.findById(expectedId).orElseThrow();
        String newNameUserExpected = "NewTestUser";
        userEntityExpected.setName(newNameUserExpected);

        //when
        UserEntity userEntityActual = userRepository.save(userEntityExpected);

        //then
        assertNotNull(userEntityActual);
        assertNotNull(userEntityActual.getUserId());
        assertEquals(userEntityExpected.getName(),userEntityActual.getName());
    }

    @Test
    void findByNameHql() {
    }

    @Test
    void findByPhoneNumber() {
    }

    @Test
    void setPhoneNumber() {
    }

    @Test
    void findByNameContainingAndEmail() {
    }
}