package ir.hamycook.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "otp_code")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OtpCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String code;
    @NonNull
    private Timestamp expireTimestamp;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;
}
