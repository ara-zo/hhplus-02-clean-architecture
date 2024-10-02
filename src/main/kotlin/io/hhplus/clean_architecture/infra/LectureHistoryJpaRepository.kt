package io.hhplus.clean_architecture.infra

import io.hhplus.clean_architecture.infra.entity.LectureHistoryEntity
import io.hhplus.clean_architecture.infra.entity.LectureScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository


interface LectureHistoryJpaRepository : JpaRepository<LectureHistoryEntity, Long> {
    fun findLectureHistoryByLectureScheduleAndUserId(lectureScheduleEntity: LectureScheduleEntity, userId: Long): List<LectureHistoryEntity>
}