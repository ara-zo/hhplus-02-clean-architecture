package io.hhplus.clean_architecture.infra.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.hibernate.annotations.Comment

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "lecture_history")
data class LectureHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("특강 신청 내역 id")
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_schedule_id")
    var lectureSchedule: LectureScheduleEntity,

    @Column
    @Comment("유저 id")
    var userId: Long,
) : BaseCreateDateTimeEntity()