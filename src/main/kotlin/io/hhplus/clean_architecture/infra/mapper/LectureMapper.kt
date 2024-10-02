package io.hhplus.clean_architecture.infra.mapper

import io.hhplus.clean_architecture.domain.lecture.Lecture
import io.hhplus.clean_architecture.infra.entity.LectureEntity


object LectureMapper {
    fun toDomain(lectureEntity: LectureEntity): Lecture {
        return Lecture.create(lectureEntity.id, lectureEntity.lectureName)
    }

    fun toEntity(lecture: Lecture): LectureEntity {
        return LectureEntity(lecture.id, lecture.lectureName)
    }
}