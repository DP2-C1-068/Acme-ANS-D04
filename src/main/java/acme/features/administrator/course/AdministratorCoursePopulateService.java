
package acme.features.administrator.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import acme.client.components.principals.Administrator;
import acme.client.helpers.MomentHelper;
import acme.client.helpers.SpringHelper;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.course.Course;
import acme.entities.course.CourseApiResponse;

@GuiService
public class AdministratorCoursePopulateService extends AbstractGuiService<Administrator, Course> {

	@Autowired
	AdministratorCourseRepository repository;
	// Services ---------------------------------------------------------------


	public List<Course> fetchNewCoursesFromApi() {
		RestTemplate api = new RestTemplate();
		String url = "https://courses.edx.org/api/courses/v1/courses/ ";

		List<Course> courses;

		if (SpringHelper.isRunningOn("testing"))
			courses = this.createMockedCourses();
		else {
			CourseApiResponse response = api.getForObject(url, CourseApiResponse.class);
			courses = response.getResults().stream().map(courseData -> courseData.toCourse()).toList();
		}

		// Filtrar duplicados por courseId
		List<String> existingIds = this.repository.findAllCourseIds();
		return courses.stream().filter(course -> !existingIds.contains(course.getCourseId())).toList();
	}

	// Mocked data for development ---------------------------------------

	protected List<Course> createMockedCourses() {
		List<Course> result = new ArrayList<>();

		Course c1 = new Course();
		c1.setBlocksUrl("https://example.com/course1 ");
		c1.setName("AP Physics 1");
		c1.setOrg("BUx");
		c1.setShortDescription("Curso introductorio de física.");
		c1.setStart(MomentHelper.getCurrentMoment());
		c1.setCourseId("PY1x");

		Course c2 = new Course();
		c2.setBlocksUrl("https://example.com/course2 ");
		c2.setName("CS169.1x");
		c2.setOrg("BerkeleyX");
		c2.setShortDescription("Fundamentos de Ruby on Rails.");
		c2.setStart(MomentHelper.getCurrentMoment());
		c2.setCourseId("CS169.1x");

		result.add(c1);
		result.add(c2);

		return result;
	}

}
