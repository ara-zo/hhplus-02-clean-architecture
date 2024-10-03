package io.hhplus.clean_architecture.domain.lecture

import io.hhplus.clean_architecture.domain.lecture.service.LectureValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import kotlin.test.assertFailsWith


@ExtendWith(MockitoExtension::class)
class LectureScheduleTest(
    @Mock
    private val lectureValidator: LectureValidator
) {

    @Test
    @DisplayName("특강 신청")
    fun apply() {
        // given
        val userId = 1L
        val lecture = Lecture.create(1L, "TDD", "홍길동")
        val lectureSchedule = LectureSchedule.create(
            1L,
            lecture,
            LocalDateTime.of(2024, 10, 3, 13, 0, 0),
            0,
            30
        )

        // when
        val apply = lectureSchedule.apply(lectureValidator, userId)

        // then
        assertThat(apply.registerCnt).isEqualTo(1)
    }

    @Test
    @DisplayName("이미 신청한 특강 신청")
    fun alreadyExistLecture() {
        // given
        val userId = 1L
        val lecture = Lecture.create(1L, "TDD", "홍길동")
        val lectureSchedule = LectureSchedule.create(
            1L,
            lecture,
            LocalDateTime.of(2024, 10, 3, 13, 0, 0),
            0,
            30
        )

        // when & then
        `when`(lectureValidator.checkApplyLecture(lectureSchedule, userId)).thenReturn(true)
        assertFailsWith<IllegalArgumentException>("lecture is already exist.") {
            lectureSchedule.apply(lectureValidator, userId)
        }
    }

    @Test
    @DisplayName("시작일 이전 특강 신청")
    fun applyLectureBeforeStartDate() {
        // given
        val userId: Long = 1L
        val lecture = Lecture.create(1L, "TDD", "홍길동")
        val lectureSchedule = LectureSchedule.create(
            1L,
            lecture,
            LocalDateTime.of(2024, 10, 3, 13, 0, 0),
            0,
            30
        )

        // when & then
        `when`(lectureValidator.checkApplyLecture(lectureSchedule, userId)).thenReturn(true)
        assertFailsWith<IllegalArgumentException>("before the start date.") {
            lectureSchedule.apply(lectureValidator, userId)
        }
    }

    @Test
    @DisplayName("정원 초과 상태에서 특강 신청")
    fun lectureCapacityOver() {
        // given
        val userId: Long = 1L
        val lecture = Lecture.create(1L, "TDD", "홍길동")
        val lectureSchedule = LectureSchedule.create(
            1L,
            lecture,
            LocalDateTime.of(2024, 10, 3, 13, 0, 0),
            0,
            30
        )

        // when & then
        `when`(lectureValidator.checkApplyLecture(lectureSchedule, userId)).thenReturn(true)
        assertFailsWith<IllegalArgumentException>("lecture capacity exceeded.") {
            lectureSchedule.apply(lectureValidator, userId)
        }
    }
}