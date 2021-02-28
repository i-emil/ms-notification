package com.troojer.msnotification.dao;

import com.troojer.msnotification.model.InnerNotificationStatus;
import com.troojer.msnotification.model.InnerNotificationType;
import com.troojer.msnotification.model.SendingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

import static com.troojer.msnotification.model.InnerNotificationStatus.NEW;
import static com.troojer.msnotification.model.SendingStatus.PENDING;

@Entity
@Table(name = "inner_notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InnerNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    @SequenceGenerator(name = "seq_generator", sequenceName = "inner_notification_seq")
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private InnerNotificationType type;

    @ElementCollection
    @CollectionTable(name = "inner_notification_map",
            joinColumns = {@JoinColumn(name = "inner_notification_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> params;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "sending_date")
    private LocalDateTime sendingDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private InnerNotificationStatus status = NEW;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
