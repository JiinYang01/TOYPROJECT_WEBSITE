package com.example.demo.repository;

import com.example.demo.domain.SportsCourse;
import com.example.demo.domain.disabledSportsCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisabledSportsCourseRepository extends JpaRepository<disabledSportsCourse, Long> {
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1")
    List<disabledSportsCourse> findAllByRowNum(@Param("rowNum") Long rownum);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1")
    Page<disabledSportsCourse> findByRowNum(Long rowNum, Pageable pageable);
    /////////////////////////////////////////////////////////////////////////////////
    Page<disabledSportsCourse> findByRowNumOrderByCoursePrcAsc(Long rowNum, Pageable pageable);
    Page<disabledSportsCourse> findByRowNumOrderByCoursePrcDesc(Long rowNum, Pageable pageable);
    Page<disabledSportsCourse> findByRowNumOrderByCrseNumDesc(Long rowNum, Pageable pageable);
    ///////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId ORDER BY sc.coursePrc ASC")
    Page<disabledSportsCourse> findByCategory_CategoryIdOrderByCoursePrcAsc(@Param("categoryId")Long categoryId, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId ORDER BY sc.coursePrc DESC")
    Page<disabledSportsCourse> findByCategory_CategoryIdOrderByCoursePrcDesc(@Param("categoryId")Long categoryId, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId ORDER BY sc.crseNum DESC")
    Page<disabledSportsCourse> findByCategory_CategoryIdOrderByCrseNumDesc(@Param("categoryId")Long categoryId, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId")
    Page<disabledSportsCourse> findByCategory_CategoryId(@Param("categoryId")Long categoryId, Pageable pageable);
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNm(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    // CategoryId와 CtprvnNm로 필터링하고 가격 오름차순 정렬
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc ASC")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    // CategoryId와 CtprvnNm로 필터링하고 가격 내림차순 정렬
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc DESC")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    // CategoryId와 CtprvnNm로 필터링하고 요청 인원수 내림차순 정렬
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.crseNum DESC")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCrseNumDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc ASC")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc DESC")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.crseNum DESC")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCrseNumDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm")
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNm(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc ASC")
    Page<disabledSportsCourse> findByCtprvnNmOrderByCoursePrcAsc(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc DESC")
    Page<disabledSportsCourse> findByCtprvnNmOrderByCoursePrcDesc(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.crseNum DESC")
    Page<disabledSportsCourse> findByCtprvnNmOrderByCrseNumDesc(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm")
    Page<disabledSportsCourse> findByCtprvnNm(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);

    //////////////////////////////////////////////////////////////////////////////////////
    // CtprvnNm와 SignguNm로 필터링하고 가격 오름차순 정렬
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc ASC")
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    // CtprvnNm와 SignguNm로 필터링하고 가격 내림차순 정렬
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc DESC")
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    // CtprvnNm와 SignguNm로 필터링하고 요청 인원수 내림차순 정렬

    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.courseReqstNmprCo DESC")
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNmOrderByCrseNumDesc(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm")
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNm(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM disabledSportsCourse sc WHERE LOWER(sc.courseNm) LIKE LOWER(CONCAT('%', :keyword, '%')) AND sc.rowNum = 1")
    Page<disabledSportsCourse> findByCourseNmContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
    //////////////////////////////////////////////////////////////////////////

    List<disabledSportsCourse> findByCtprvnNm(String ctprvnNm);
    List<disabledSportsCourse> findByCtprvnNmAndSignguNm(String ctprvnNm, String signguNm);
    //내코드
    List<disabledSportsCourse> findAllByOrderByCoursePrcAsc();
    List<disabledSportsCourse> findByCtprvnNmAndSignguNmAndCoursePrcLessThanEqualAndCourseNmContainingIgnoreCase(
            String sido, String sigugun, Integer price, String keyword);


}