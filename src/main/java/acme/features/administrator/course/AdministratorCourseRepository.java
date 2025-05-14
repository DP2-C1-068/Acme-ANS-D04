
package acme.features.administrator.course;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.course.Course;

@Repository
public interface AdministratorCourseRepository extends AbstractRepository {

	// Base CRUD operations are inherited from JpaRepository
	@Query("SELECT c.courseId FROM Course c")
	List<String> findAllCourseIds();

	@Query("SELECT c FROM Course c")
	Collection<Course> findAllCourses();

	@Query("SELECT c FROM Course c WHERE c.courseId = :courseId")
	Course findOneByCourseId(String courseId);

	@Query("DELETE FROM Course c")
	void deleteAllCourses();
}
