package com.infosys.educationConsultancyApplication.dao;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.infosys.educationConsultancyApplication.bean.Course;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("select max(courseId) from Course")
	public long getMaxCourseId();
	
	@Query("select a from Course a where technology=?1")
	public List<Course> getCoursesByTechnology(String technology);
	
}
