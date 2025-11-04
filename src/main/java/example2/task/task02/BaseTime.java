package example2.task.task02;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  // Auditing 활성화
public class BaseTime {
    @CreatedDate
    private LocalDateTime createDate;   // 생성 날짜 및 시간
    @LastModifiedDate
    private LocalDateTime updateDate;   // 수정 날짜 및 시간
} // class end