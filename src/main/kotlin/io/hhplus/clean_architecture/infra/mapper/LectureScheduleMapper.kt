package io.hhplus.clean_architecture.infra.mapper

import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import io.hhplus.clean_architecture.infra.entity.LectureScheduleEntity
import io.hhplus.clean_architecture.infra.mapper.LectureMapper.toDomain


object LectureScheduleMapper {
    fun toDomain(lectureScheduleEntity: LectureScheduleEntity): LectureSchedule {
        return LectureSchedule.create(
            lectureScheduleEntity.id,
            toDomain(lectureScheduleEntity.lecture),
            lectureScheduleEntity.lectureDateTime,
            lectureScheduleEntity.registerCnt,
            lectureScheduleEntity.capacity
        )
    }

    fun toEntity(lectureSchedule: LectureSchedule): LectureScheduleEntity {
        return LectureScheduleEntity(
            lectureSchedule.id,
            LectureMapper.toEntity(lectureSchedule.lecture),
            lectureSchedule.lectureDateTime,
            lectureSchedule.registerCnt,
            lectureSchedule.capacity
        )
    }
}