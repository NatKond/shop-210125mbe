package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Repository  //после Spring 6 или SpringBoot 3.0 и выше - можно не ставить эту аннотацию
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Чистый SQL
    @Query(nativeQuery = true, value = "SELECT * FROM Users u WHERE u.Email=?1")
    Optional<UserEntity> findByEmailNativeQuery(String email);

    // HQL /OQL
    @Query(nativeQuery = false, value = "SELECT ue FROM UserEntity ue WHERE ue.name=?1")
    List<UserEntity> findByNameHql(String name);

    Optional<UserEntity> findUserByPhoneNumber(String phoneNumber);

    // Update phoneNumber
    @Modifying
    @Transactional
    // @Query("update UserEntity ue set ue.PhoneNumber=?2 where ue.UserID=?1")
    @Query("UPDATE UserEntity ue SET ue.phoneNumber=:newPhoneNumber WHERE ue.userId=:userId")
    int setPhoneNumber(Long userId, String newPhoneNumber);

    // Name like '%Alice%' AND Email='alice.johnson@example.com'
    List<UserEntity> findByNameContainingAndEmail(String name, String email);

}
