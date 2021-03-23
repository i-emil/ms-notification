package com.troojer.msnotification.dao;

import com.troojer.msnotification.model.enm.SendingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.troojer.msnotification.model.enm.SendingStatus.PENDING;

@Entity
@Table(name = "sms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    @SequenceGenerator(name = "seq_generator", sequenceName = "sms_seq")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String region;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "sending_date")
    private LocalDateTime sendingDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private SendingStatus status = PENDING;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
