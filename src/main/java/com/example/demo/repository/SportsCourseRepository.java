package com.example.demo.repository;
import com.example.demo.domain.SportsCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//SportsCourseCustomRepository 내코드
@Repository
public interface SportsCourseRepository extends JpaRepository<SportsCourse, Long> {
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1")
    List<SportsCourse> findAllByRowNum(@Param("rowNum") Long rownum);
    /////////////////////////////////////////////////////////////////////////////////
    Page<SportsCourse> findByRowNumOrderByCoursePrcAsc(Long rowNum, Pageable pageable);
    Page<SportsCourse> findByRowNumOrderByCoursePrcDesc(Long rowNum, Pageable pageable);
    Page<SportsCourse> findByRowNumOrderByCourseReqstNmprCoDesc(Long rowNum, Pageable pageable);
    ///////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId ORDER BY sc.coursePrc ASC")
    Page<SportsCourse> findByCategory_CategoryIdOrderByCoursePrcAsc(@Param("categoryId")Long categoryId, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId ORDER BY sc.coursePrc DESC")
    Page<SportsCourse> findByCategory_CategoryIdOrderByCoursePrcDesc(@Param("categoryId")Long categoryId, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId")
    Page<SportsCourse> findByCategory_CategoryIdOrderByCourseReqstNmprCoDesc(@Param("categoryId")Long categoryId, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId")
    Page<SportsCourse> findByCategory_CategoryId(@Param("categoryId")Long categoryId, Pageable pageable);
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNm(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    // CategoryId와 CtprvnNm로 필터링하고 가격 오름차순 정렬
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc ASC")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    // CategoryId와 CtprvnNm로 필터링하고 가격 내림차순 정렬
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc DESC")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    // CategoryId와 CtprvnNm로 필터링하고 요청 인원수 내림차순 정렬
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.courseReqstNmprCo DESC")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCourseReqstNmprCoDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc ASC")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc DESC")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.courseReqstNmprCo DESC")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.category.categoryId = :categoryId AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm")
    Page<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNm(@Param("categoryId") Long categoryId, @Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc ASC")
    Page<SportsCourse> findByCtprvnNmOrderByCoursePrcAsc(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.coursePrc DESC")
    Page<SportsCourse> findByCtprvnNmOrderByCoursePrcDesc(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm ORDER BY sc.courseReqstNmprCo DESC")
    Page<SportsCourse> findByCtprvnNmOrderByCourseReqstNmprCoDesc(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm")
    Page<SportsCourse> findByCtprvnNm(@Param("ctprvnNm") String ctprvnNm, Pageable pageable);


    //////////////////////////////////////////////////////////////////////////////////////
    // CtprvnNm와 SignguNm로 필터링하고 가격 오름차순 정렬
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc ASC")
    Page<SportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    // CtprvnNm와 SignguNm로 필터링하고 가격 내림차순 정렬
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.coursePrc DESC")
    Page<SportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    // CtprvnNm와 SignguNm로 필터링하고 요청 인원수 내림차순 정렬


    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm ORDER BY sc.courseReqstNmprCo DESC")
    Page<SportsCourse> findByCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1 AND sc.ctprvnNm = :ctprvnNm AND sc.signguNm = :signguNm")
    Page<SportsCourse> findByCtprvnNmAndSignguNm(@Param("ctprvnNm") String ctprvnNm, @Param("signguNm") String signguNm, Pageable pageable);
    @Query("SELECT sc FROM SportsCourse sc WHERE LOWER(sc.courseNm) LIKE LOWER(CONCAT('%', :keyword, '%')) AND sc.rowNum = 1")
    Page<SportsCourse> findByCourseNmContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
    //////////////////////////////////////////////////////////////////////////

    @Query("SELECT sc FROM SportsCourse sc " +
            "WHERE (sc.category.categoryId = :preferredSports1 or sc.category.categoryId = :preferredSports2 or sc.category.categoryId = :preferredSports3) " +
            "AND sc.ctprvnNm = :sido " +
            "AND sc.signguNm = :sigugun " +
            "AND sc.coursePrc <= :price " +
            "ORDER BY sc.courseReqstNmprCo DESC")
    List<SportsCourse> findRecommendedCourses(@Param("preferredSports1") Long categoryId1, @Param("preferredSports2") Long categoryId2, @Param("preferredSports3") Long categoryId3, @Param("sido") String sido, @Param("sigugun") String sigugun, @Param("price") Long price);


    @Query("SELECT sc FROM SportsCourse sc WHERE sc.rowNum = 1")
    Page<SportsCourse> findByRowNum(Long rowNum, Pageable pageable);


    List<SportsCourse> findByCtprvnNm(String ctprvnNm);
    List<SportsCourse> findByCtprvnNmAndSignguNm(String ctprvnNm, String signguNm);




    @Query(value = "SELECT c.category_name AS categoryName, " +
            "SUBSTRING(sc.course_begin_de, 1, 4) AS year, " +
            "CASE " +
            "   WHEN SUBSTRING(sc.course_begin_de, 5, 2) IN ('12', '01', '02') THEN 'Winter' " +
            "   WHEN SUBSTRING(sc.course_begin_de, 5, 2) IN ('03', '04', '05') THEN 'Spring' " +
            "   WHEN SUBSTRING(sc.course_begin_de, 5, 2) IN ('06', '07', '08') THEN 'Summer' " +
            "   WHEN SUBSTRING(sc.course_begin_de, 5, 2) IN ('09', '10', '11') THEN 'Autumn' " +
            "END AS season, " +
            "SUM(sc.course_reqst_nmpr_co) AS totalRequestCount " +
            "FROM sports_course sc " +
            "JOIN category c ON sc.category_id = c.category_id " +
            "GROUP BY c.category_name, SUBSTRING(sc.course_begin_de, 1, 4), season",
            nativeQuery = true)
    List<Object[]> findSeasonalCourseDataByYear();




}