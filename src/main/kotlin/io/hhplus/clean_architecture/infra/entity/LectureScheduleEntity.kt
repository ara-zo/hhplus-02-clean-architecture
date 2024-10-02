package io.hhplus.clean_architecture.infra.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "lecture_schedule")
data class LectureScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("특강 스케쥴 id")
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @Comment("특강 PK")
    var lecture: LectureEntity,

    @Column(nullable = false)
    @Comment("특강 시작일시")
    var lectureDateTime: LocalDateTime,

    @Column(nullable = false)
    @Comment("신청 인원")
    var registerCnt: Int = 0,

    @Column(nullable = false)
    @Comment("정원")
    var capacity: Int = 30
) : BaseEntity()