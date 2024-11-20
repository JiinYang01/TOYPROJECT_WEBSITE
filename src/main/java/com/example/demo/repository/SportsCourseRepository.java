package com.example.demo.repository;

import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.domain.SportsCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//SportsCourseCustomRepository 내코드
public interface SportsCourseRepository extends JpaRepository<SportsCourse, Long> {

    Page<SportsCourse> findAllByOrderByCoursePrcAsc(Pageable pageable);
    Page<SportsCourse> findAllByOrderByCoursePrcDesc(Pageable pageable);
    Page<SportsCourse> findAllByOrderByCourseReqstNmprCoDesc(Pageable pageable);

    Page<SportsCourse> findByCategory_CategoryIdOrderByCoursePrcAsc(Long categoryId, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdOrderByCoursePrcDesc(Long categoryId, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdOrderByCourseReqstNmprCoDesc(Long categoryId, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryId(Long categoryId, Pageable pageable);

    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(Long categoryId, String ctprvnNm, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(Long categoryId, String ctprvnNm, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCourseReqstNmprCoDesc(Long categoryId, String ctprvnNm, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNm(Long categoryId, String ctprvnNm, Pageable pageable);

    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNm(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);

    Page<SportsCourse> findByCtprvnNmOrderByCoursePrcAsc(String ctprvnNm, Pageable pageable);
    Page<SportsCourse> findByCtprvnNmOrderByCoursePrcDesc(String ctprvnNm, Pageable pageable);
    Page<SportsCourse> findByCtprvnNmOrderByCourseReqstNmprCoDesc(String ctprvnNm, Pageable pageable);
    Page<SportsCourse> findByCtprvnNm(String ctprvnNm, Pageable pageable);

    Page<SportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(String ctprvnNm, String cignguNm, Pageable pageable);
    Page<SportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(String ctprvnNm, String cignguNm, Pageable pageable);
    Page<SportsCourse> findByCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(String ctprvnNm, String cignguNm, Pageable pageable);
    Page<SportsCourse> findByCtprvnNmAndSignguNm(String ctprvnNm, String cignguNm, Pageable pageable);

    Page<SportsCourse> findByCourseNmContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT sc FROM SportsCourse sc " +
            "WHERE (sc.category.categoryId = :preferredSports1 or sc.category.categoryId = :preferredSports2 or sc.category.categoryId = :preferredSports3) " +
            "AND sc.ctprvnNm = :sido " +
            "AND sc.signguNm = :sigugun " +
            "AND sc.coursePrc <= :price " +
            "ORDER BY sc.courseReqstNmprCo DESC")
    List<SportsCourse> findRecommendedCourses(
            @Param("preferredSports1") Long categoryId1,
            @Param("preferredSports2") Long categoryId2,
            @Param("preferredSports3") Long categoryId3,
            @Param("sido") String sido,
            @Param("sigugun") String sigugun,
            @Param("price") Long price,
            Pageable pageable);

}
