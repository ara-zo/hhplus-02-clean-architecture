package io.hhplus.clean_architecture.controller

import io.hhplus.clean_architecture.controller.dto.ApplyLectureResponse
import io.hhplus.clean_architecture.controller.dto.FindLectureResponse
import io.hhplus.clean_architecture.controller.dto.FindLectureScheduleResponse
import io.hhplus.clean_architecture.domain.lecture.service.LectureService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/lectures")
class LectureController(
    private val lectureService: LectureService
) {
    // 특강 신청
    @PostMapping("/apply/{lectureScheduledId}")
    fun apply(
        @PathVariable lectureScheduledId: Long,
        @RequestParam userId: Long
    ): ResponseEntity<ApplyLectureResponse> {
        val result = lectureService.apply(lectureScheduleId = lectureScheduledId, userId = userId)
        return ResponseEntity.ok(ApplyLectureResponse.of(result))
    }

    // 유저 신청 특강 목록
    @GetMapping("/lecture/{userId}")
    fun lectureApplyCheck(
        @PathVariable userId: Long,
        @RequestParam lectureScheduleId: Long
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok(lectureService.lectureApplicationCheck(userId, lectureScheduleId))
    }

    // 특강 목록
    @GetMapping
    fun findAllLecutureList(): ResponseEntity<List<FindLectureResponse>> {
        return ResponseEntity.ok(lectureService.findAllLectureList().map(FindLectureResponse::of))
    }

    // 특강 스케쥴 목록
    @GetMapping("/schedule/{lectureId}")
    fun findAllLectureScheduleList(
        @PathVariable lectureId: Long,
    ): ResponseEntity<List<FindLectureScheduleResponse>> {
        return ResponseEntity.ok(
            lectureService.findAllLectureScheduleList(lectureId)
                .map(FindLectureScheduleResponse::of)
                .toList()
        )
    }

    // 날짜별 특강 스케쥴 목록 조회
    @GetMapping("/schedule")
    fun findLectureScheduleBySearchDate(
        @RequestParam searchDate: LocalDate,
    ): ResponseEntity<List<FindLectureScheduleResponse>> {
        return ResponseEntity.ok(
            lectureService.findLectureScheduleBySearchDate(searchDate)
                .map(FindLectureScheduleResponse::of)
                .toList()
        )
    }

}