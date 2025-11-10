package example2.day111.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// 여러 엔티티들의 생성/수정 날짜 자동 주입
@Getter
@MappedSuperclass   // Entity 상속 용도로 사용
// AppStart에 @EnableJpaAuditing 추가 + EntityListeners 추가
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
public class BaseTime {
    @CreatedDate                        // 현재 날짜 및 시간을 자동으로 주입
    private LocalDateTime createDate;   // 생성 날짜 및 시간
    @LastModifiedDate                   // 수정 날짜 및 시간을 자동으로 주입
    private LocalDateTime updateDate;   // 수정 날짜 및 시간
} // class end