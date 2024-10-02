package io.hhplus.clean_architecture.domain.lecture

import java.time.LocalDateTime

data class LectureSchedule(
    private val id: Long,
    private val lecture: Lecture,
    private val lectureDateTime: LocalDateTime,
    private var registerCnt: Int,
    private val capacity: Int,
) {
    fun create(
        id: Long,
        lecture: Lecture,
        lectureDateTime: LocalDateTime,
        registerCnt: Int,
        capacity: Int
    ): LectureSchedule {
        if (registerCnt < 0 || capacity < 0) {
            throw IllegalArgumentException("registerCnt and capacity should be greater than 0");
        }
        return LectureSchedule(id, lecture, lectureDateTime, registerCnt, capacity);
    }

    fun apply(userId: Long): LectureSchedule {
        // 중복 수강 체크
        // 특강 시작일 체크
        // 특강 정원 체크

        this.registerCnt += 1

        return this
    }
}

