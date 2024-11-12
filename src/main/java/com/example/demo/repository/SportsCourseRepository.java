package com.example.demo.repository;

import com.example.demo.domain.SportsCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportsCourseRepository extends JpaRepository<SportsCourse, Long> {

    List<SportsCourse> findAllByOrderByCoursePrcAsc();
    List<SportsCourse> findAllByOrderByCoursePrcDesc();
    List<SportsCourse> findAllByOrderByCourseReqstNmprCoDesc();

    List<SportsCourse> findByCategory_CategoryIdOrderByCoursePrcAsc(Long categoryId);
    List<SportsCourse> findByCategory_CategoryIdOrderByCoursePrcDesc(Long categoryId);
    List<SportsCourse> findByCategory_CategoryIdOrderByCourseReqstNmprCoDesc(Long categoryId);
    List<SportsCourse> findByCategory_CategoryId(Long categoryId);

    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(Long categoryId, String ctprvnNm);
    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(Long categoryId, String ctprvnNm);
    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmOrderByCourseReqstNmprCoDesc(Long categoryId, String ctprvnNm);
    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNm(Long categoryId, String ctprvnNm);

    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(Long categoryId, String ctprvnNm, String signguNm);
    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(Long categoryId, String ctprvnNm, String signguNm);
    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(Long categoryId, String ctprvnNm, String signguNm);
    List<SportsCourse> findByCategory_CategoryIdAndCtprvnNmAndSignguNm(Long categoryId, String ctprvnNm, String signguNm);

    List<SportsCourse> findByCtprvnNmOrderByCoursePrcAsc(String ctprvnNm);
    List<SportsCourse> findByCtprvnNmOrderByCoursePrcDesc(String ctprvnNm);
    List<SportsCourse> findByCtprvnNmOrderByCourseReqstNmprCoDesc(String ctprvnNm);
    List<SportsCourse> findByCtprvnNm(String ctprvnNm);

    List<SportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(String ctprvnNm, String cignguNm);
    List<SportsCourse> findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(String ctprvnNm, String cignguNm);
    List<SportsCourse> findByCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(String ctprvnNm, String cignguNm);
    List<SportsCourse> findByCtprvnNmAndSignguNm(String ctprvnNm, String cignguNm);

}
