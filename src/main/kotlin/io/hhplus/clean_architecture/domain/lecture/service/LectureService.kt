package io.hhplus.clean_architecture.domain.lecture.service

import io.hhplus.clean_architecture.domain.lecture.Lecture
import io.hhplus.clean_architecture.domain.lecture.LectureHistory
import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import io.hhplus.clean_architecture.domain.lecture.repository.LectureHistoryRepository
import io.hhplus.clean_architecture.domain.lecture.repository.LectureRepository
import io.hhplus.clean_architecture.domain.lecture.repository.LectureScheduleRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate


@Service
@RequiredArgsConstructor
class LectureService(
    private var lectureRepository: LectureRepository,
    private var lectureScheduleRepository: LectureScheduleRepository,
    private var lectureHistoryRepository: LectureHistoryRepository,
    private var lectureValidator: LectureValidator
) {

    @Transactional(rollbackFor = [Exception::class])
    fun apply(lectureScheduleId: Long, userId: Long): LectureSchedule {
        // 특강 스케쥴 조회
        val lectureSchedule: LectureSchedule = lectureScheduleRepository.findById(lectureScheduleId)

        // 특강 신청
        val apply = lectureSchedule.apply(lectureValidator, userId)

        // 특강 내역 등록
        lectureHistoryRepository.save(LectureHistory.create(null, lectureScheduleRepository.save(apply), userId!!))
        return lectureSchedule
    }

    // 특강 목록 조회
    fun findAllLectureList(): List<Lecture> {
        return lectureRepository.findAll()
    }

    // 특강 스케쥴 조회
    fun findAllLectureScheduleList(lectureId: Long): List<LectureSchedule> {
        // 1. 특강 조회
        val lecture = lectureRepository.findById(lectureId)
        // 2. 특강 스케쥴 목록 조회
        return lectureScheduleRepository.findAllLectureScheduleList(lecture)
    }

    // 유저 특강 신청 여부 체크
    fun lectureApplicationCheck(userId: Long, lectureScheduleId: Long): Boolean {
        // 1. 특강 스케쥴 조회
        val lectureSchedule = lectureScheduleRepository.findById(lectureScheduleId)

        // 2. 사용자 특강 조회
        return lectureHistoryRepository.findLectureHistoryByLectureScheduleAndUserId(lectureSchedule, userId) != null
    }

    fun findLectureScheduleBySearchDate(searchDate: LocalDate): List<LectureSchedule> {
        return lectureScheduleRepository.findLectureScheduleByCreateDate(searchDate)
    }
}