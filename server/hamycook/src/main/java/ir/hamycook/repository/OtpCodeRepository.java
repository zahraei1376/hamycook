package ir.hamycook.repository;

import ir.hamycook.entity.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {

    @Query(value = "SELECT * FROM otp_code WHERE otp_code.user_id = :userId", nativeQuery = true)
    Optional<OtpCode> findByUserId(@Param("userId") String userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE otp_code WHERE otp_code.user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") String userId);

}
