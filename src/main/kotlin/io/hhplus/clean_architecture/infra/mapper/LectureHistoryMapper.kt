package io.hhplus.clean_architecture.infra.mapper

import io.hhplus.clean_architecture.domain.lecture.LectureHistory
import io.hhplus.clean_architecture.infra.entity.LectureHistoryEntity


object LectureHistoryMapper {
    fun toDomain(lectureHistoryEntity: LectureHistoryEntity): LectureHistory {
        return LectureHistory.create(
            lectureHistoryEntity.id,
            LectureScheduleMapper.toDomain(lectureHistoryEntity.lectureSchedule),
            lectureHistoryEntity.userId
        )
    }

    fun toEntity(lectureHistory: LectureHistory): LectureHistoryEntity {
        return LectureHistoryEntity(
            lectureHistory.id!!,
            LectureScheduleMapper.toEntity(lectureHistory.lectureSchedule),
            lectureHistory.userId
        )
    }
}