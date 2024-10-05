package io.hhplus.clean_architecture.infra.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.hibernate.annotations.Comment

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "lecture")
data class LectureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("특강 id")
    val id: Long,

    @Column(nullable = false)
    @Comment("특강명")
    var lectureName: String,

    @Column(nullable = false)
    @Comment("교수명")
    var professorName: String
) : BaseEntity()