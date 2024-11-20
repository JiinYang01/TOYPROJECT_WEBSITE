package com.example.demo.repository;

import com.example.demo.domain.disabledSportsCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisabledSportsCourseRepository extends JpaRepository<disabledSportsCourse, Long> {
    Page<disabledSportsCourse> findAllByOrderByCoursePrcAsc(Pageable pageable);
    Page<disabledSportsCourse> findAllByOrderByCoursePrcDesc(Pageable pageable);
    Page<disabledSportsCourse> findAllByOrderByCourseReqstNmprCoDesc(Pageable pageable);

    Page<disabledSportsCourse> findByCategory_CategoryIdOrderByCoursePrcAsc(Long categoryId, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdOrderByCoursePrcDesc(Long categoryId, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdOrderByCourseReqstNmprCoDesc(Long categoryId, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryId(Long categoryId, Pageable pageable);

    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(Long categoryId, String ctprvnNm, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(Long categoryId, String ctprvnNm, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCourseReqstNmprCoDesc(Long categoryId, String ctprvnNm, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNm(Long categoryId, String ctprvnNm, Pageable pageable);

    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);
    Page<disabledSportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNm(Long categoryId, String ctprvnNm, String signguNm, Pageable pageable);

    Page<disabledSportsCourse> findByCtprvnNmOrderByCoursePrcAsc(String ctprvnNm, Pageable pageable);
    Page<disabledSportsCourse> findByCtprvnNmOrderByCoursePrcDesc(String ctprvnNm, Pageable pageable);
    Page<disabledSportsCourse> findByCtprvnNmOrderByCourseReqstNmprCoDesc(String ctprvnNm, Pageable pageable);
    Page<disabledSportsCourse> findByCtprvnNm(String ctprvnNm, Pageable pageable);

    Page<disabledSportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(String ctprvnNm, String cignguNm, Pageable pageable);
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(String ctprvnNm, String cignguNm, Pageable pageable);
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(String ctprvnNm, String cignguNm, Pageable pageable);
    Page<disabledSportsCourse> findByCtprvnNmAndSignguNm(String ctprvnNm, String cignguNm, Pageable pageable);

    Page<disabledSportsCourse> findByCourseNmContainingIgnoreCase(String keyword, Pageable pageable);

    //내코드
    List<disabledSportsCourse> findAllByOrderByCoursePrcAsc();
    List<disabledSportsCourse> findByCtprvnNmAndSignguNmAndCoursePrcLessThanEqualAndCourseNmContainingIgnoreCase(
            String sido, String sigugun, Integer price, String keyword);


}
