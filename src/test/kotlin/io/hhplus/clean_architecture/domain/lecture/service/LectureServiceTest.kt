package io.hhplus.clean_architecture.domain.lecture.service

import io.hhplus.clean_architecture.domain.lecture.Lecture
import io.hhplus.clean_architecture.domain.lecture.LectureHistory
import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
import io.hhplus.clean_architecture.domain.lecture.repository.LectureHistoryRepository
import io.hhplus.clean_architecture.domain.lecture.repository.LectureScheduleRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertFailsWith


@ExtendWith(MockitoExtension::class)
class LectureServiceTest(
    @InjectMocks
    private val lectureService: LectureService,

    @Mock
    private val lectureHistoryRepository: LectureHistoryRepository,

    @Mock
    private val lectureScheduleRepository: LectureScheduleRepository,

    @Mock
    private val lectureValidator: LectureValidator

) {
    private lateinit var defaultLectureSchedule: LectureSchedule

    @BeforeEach
    fun setUp() {
        // 특강 기본 세팅
        val defaultLecture = Lecture.create(1L, "항해 플러스 백엔드", "홍길동")
        defaultLectureSchedule = LectureSchedule.create(
            1L,
            defaultLecture,
            LocalDateTime.of(2024, 10, 3, 12, 0, 0),
            0,
            30
        )
    }

    @Test
    @DisplayName("특강 신청 성공")
    fun apply() {
        // given
        val lectureScheduleId = 1L
        val userId = 1L
        // when
        `when`(lectureScheduleRepository.findById(lectureScheduleId)).thenReturn(defaultLectureSchedule)
        `when`(lectureValidator.checkApplyLecture(defaultLectureSchedule, userId)).thenReturn(true)
        val apply = lectureService.apply(lectureScheduleId, userId)

        // then
        assertThat(apply.registerCnt).isEqualTo(1)
    }

    @Test
    @DisplayName("특강 정원 30명 초과 예외 발생")
    fun lectureCapacity() {
        // given
        val lectureScheduleId = 1L
        val userId = 1L
        val lecture = Lecture.create(1L, "TDD", "홍길삼")
        val lectureSchedule = LectureSchedule.create(
            1L,
            lecture,
            LocalDateTime.now(),
            30,
            30
        )

        // when
        `when`(lectureScheduleRepository.findById(lectureScheduleId)).thenReturn(lectureSchedule)
        `when`(lectureValidator.checkApplyLecture(lectureSchedule, userId)).thenReturn(false)

        // then
        assertFailsWith<IllegalArgumentException>("lecture capacity exceeded.") {
            lectureSchedule.apply(lectureValidator, userId)
        }
    }

    @Test
    @DisplayName("이미 신청한 특강 신청 여부 조회 시 false 반환")
    fun checkAlreadyAppliedLectureFalse() {
        // given
        val lectureScheduleId = 1L
        val userId = 1L
        val lecture = Lecture.create(1L, "TDD", "홍길동")
        val lectureSchedule = LectureSchedule.create(
            1L,
            lecture,
            LocalDateTime.now().plusDays(1),
            1,
            30
        )

        val lectureHistory = LectureHistory.create(null, lectureSchedule, userId)

        // when
        `when`(lectureScheduleRepository.findById(lectureScheduleId)).thenReturn(lectureSchedule)
        `when`(lectureHistoryRepository.findLectureHistoryByLectureScheduleAndUserId(lectureSchedule, userId)).thenReturn(Optional.of(lectureHistory))
        val result = lectureService.lectureApplicationCheck(userId, lectureScheduleId)

        // then
        assertThat(result).isFalse()
    }

}