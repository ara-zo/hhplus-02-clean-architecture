package io.hhplus.clean_architecture.controller

import io.hhplus.clean_architecture.domain.lecture.Lecture
import io.hhplus.clean_architecture.domain.lecture.LectureSchedule
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
    ): ResponseEntity<LectureSchedule> {
        return ResponseEntity.ok(lectureService.apply(lectureScheduleId = lectureScheduledId, userId = userId))
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
    fun findAllLecutureList(): ResponseEntity<List<Lecture>> {
        return ResponseEntity.ok(lectureService.findAllLectureList())
    }

    // 특강 스케쥴 목록
    @GetMapping("/schedule/{lectureId}")
    fun findAllLectureScheduleList(
        @PathVariable lectureId: Long,
    ): ResponseEntity<List<LectureSchedule>> {
        return ResponseEntity.ok(lectureService.findAllLectureScheduleList(lectureId))
    }

    fun findLectureScheduleBySearchDate(
        @RequestParam searchDate: LocalDate,
    ): ResponseEntity<List<LectureSchedule>> {
        return ResponseEntity.ok(lectureService.findLectureScheduleBySearchDate(searchDate))
    }

}