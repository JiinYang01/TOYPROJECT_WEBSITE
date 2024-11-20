package com.example.demo.repository;

import com.example.demo.domain.SportsCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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

    //내코드
    List<SportsCourse> findAllByOrderByCoursePrcAsc();
    List<SportsCourse> findByCtprvnNmAndSignguNmAndCoursePrcLessThanEqualAndCourseNmContainingIgnoreCase(
            String sido, String sigugun, Integer price, String keyword);


}
