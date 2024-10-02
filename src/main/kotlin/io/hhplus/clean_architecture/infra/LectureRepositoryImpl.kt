package io.hhplus.clean_architecture.infra

import io.hhplus.clean_architecture.domain.lecture.Lecture
import io.hhplus.clean_architecture.domain.lecture.repository.LectureRepository
import io.hhplus.clean_architecture.infra.mapper.LectureMapper
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class LectureRepositoryImpl(
    private val lectureJpaRepository: LectureJpaRepository
) : LectureRepository {
    override fun findById(lectureId: Long): Lecture {
        return lectureJpaRepository.findById(lectureId)
            .map(LectureMapper::toDomain)
            .orElseThrow { EntityNotFoundException() }
    }

    override fun findAll(): List<Lecture> {
        return lectureJpaRepository.findAll()
            .map(LectureMapper::toDomain)
    }
}