package io.hhplus.clean_architecture.domain.lecture

import io.hhplus.clean_architecture.domain.lecture.service.LectureValidator
import java.time.LocalDateTime

data class LectureSchedule(
    val id: Long,
    val lecture: Lecture,
    val lectureDateTime: LocalDateTime,
    var registerCnt: Int,
    val capacity: Int,
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
        return LectureSchedule(id, lecture, lectureDateTime, registerCnt, capacity)
    }

    fun apply(lectureValidator: LectureValidator, userId: Long): LectureSchedule {

        // 중복 수강 체크
        if (lectureValidator.checkApplyLecture(this, userId)) {
            throw IllegalArgumentException("lecture is already exist.")
        }

        // 특강 시작일 체크
        if (!LocalDateTime.now().isAfter(this.lectureDateTime)) {
            throw IllegalArgumentException("before the start date.")
        }

        // 특강 정원 체크
        if (this.capacity <= this.registerCnt) {
            throw IllegalArgumentException("lecture capacity exceeded.")
        }

        this.registerCnt += 1

        return this
    }

    companion object {
        fun create(
            id: Long,
            lecture: Lecture,
            lectureDatetime: LocalDateTime,
            registerCnt: Int,
            capacity: Int
        ): LectureSchedule {
            if (registerCnt < 0 || capacity < 0) {
                IllegalArgumentException("registerCnt and capacity should be greater than 0")
            }
            return LectureSchedule(id, lecture, lectureDatetime, registerCnt, capacity)
        }
    }

}

