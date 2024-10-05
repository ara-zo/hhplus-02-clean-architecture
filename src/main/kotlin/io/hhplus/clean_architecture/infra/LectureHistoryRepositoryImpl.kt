package io.hhplus.clean_architecture.infra

import io.hhplus.clean_architecture.domain.lecture.LectureHistory
import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import io.hhplus.clean_architecture.domain.lecture.repository.LectureHistoryRepository
import io.hhplus.clean_architecture.infra.mapper.LectureHistoryMapper
import io.hhplus.clean_architecture.infra.mapper.LectureScheduleMapper.toEntity
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class LectureHistoryRepositoryImpl(
    private val lectureHistoryJpaRepository: LectureHistoryJpaRepository
) : LectureHistoryRepository {

    override fun findLectureHistoryByLectureScheduleAndUserId(lectureSchedule: LectureSchedule, userId: Long): List<LectureHistory> {
        val lectureScheduleEntity = toEntity(lectureSchedule)

        return lectureHistoryJpaRepository.findLectureHistoryByLectureScheduleAndUserId(lectureScheduleEntity, userId)
            .map(LectureHistoryMapper::toDomain)
    }

    override fun save(lectureHistory: LectureHistory): LectureHistory {
        return LectureHistoryMapper.toDomain(
            lectureHistoryJpaRepository.save(LectureHistoryMapper.toEntity(lectureHistory))
        )
    }

}