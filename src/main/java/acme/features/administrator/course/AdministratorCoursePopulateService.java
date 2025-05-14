
package acme.features.administrator.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import acme.client.components.principals.Administrator;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.course.Course;
import acme.entities.course.CourseApiResponse;
import acme.entities.course.CourseApiResponse.CourseData;

@GuiService
public class AdministratorCoursePopulateService extends AbstractGuiService<Administrator, Course> {

	@Autowired
	AdministratorCourseRepository repository;
	// Services ---------------------------------------------------------------


	public void populateAndSaveCoursesFromJson() {
		RestTemplate api = new RestTemplate();
		String url = "https://courses.edx.org/api/courses/v1/courses/ ";

		try {
			// Llamada a la API
			CourseApiResponse response = api.getForObject(url, CourseApiResponse.class);

			// Mapeamos los datos a Course
			List<Course> courses = response.getResults().stream().map(CourseData::toCourse).toList();

			// Filtramos los que ya existen (por courseId)
			List<String> existingIds = this.repository.findAllCourseIds();
			List<Course> newCourses = courses.stream().filter(course -> !existingIds.contains(course.getCourseId())).toList();

			// Guardamos solo los nuevos
			if (!newCourses.isEmpty())
				this.repository.saveAll(newCourses);

		} catch (Exception e) {
			throw new RuntimeException("Error fetching or saving courses: " + e.getMessage(), e);
		}
	}

	// Mocked data for development ---------------------------------------

	protected List<Course> createMockedCourses() {
		List<Course> result = new ArrayList<>();

		Course c1 = new Course();
		c1.setBlocksUrl("https://example.com/course1 ");
		c1.setName("AP Physics 1");
		c1.setOrg("BUx");
		c1.setShortDescription("Curso introductorio de física.");
		c1.setStart("2016-09-12T18:00:00Z");
		c1.setCourseId("PY1x");

		Course c2 = new Course();
		c2.setBlocksUrl("https://example.com/course2 ");
		c2.setName("CS169.1x");
		c2.setOrg("BerkeleyX");
		c2.setShortDescription("Fundamentos de Ruby on Rails.");
		c2.setStart("2016-05-24T20:00:00Z");
		c2.setCourseId("CS169.1x");

		result.add(c1);
		result.add(c2);

		return result;
	}

}
