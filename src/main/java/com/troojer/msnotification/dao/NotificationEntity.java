package com.troojer.msnotification.dao;

import com.troojer.msnotification.model.enm.MessageType;
import com.troojer.msnotification.model.enm.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

import static com.troojer.msnotification.model.enm.NotificationStatus.OK;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "status = 'OK'")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notification_generator")
    @SequenceGenerator(name = "seq_notification_generator", sequenceName = "notification_seq")
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "notification_map",
            joinColumns = {@JoinColumn(name = "notification_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> params;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "sending_date")
    private LocalDateTime sendingDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private NotificationStatus status = OK;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
