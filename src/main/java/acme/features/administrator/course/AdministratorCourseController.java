
package acme.features.administrator.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.client.components.principals.Administrator;
import acme.client.controllers.GuiController;
import acme.client.helpers.Assert;
import acme.client.helpers.PrincipalHelper;

@GuiController
public class AdministratorCourseController {

	// Injecting a service that will handle the course population
	@Autowired
	private AdministratorCoursePopulateService service;

	// Endpoints --------------------------------------------------------------


	@GetMapping("/administrator/course/populate")
	public ModelAndView populateCourse() {
		Assert.state(PrincipalHelper.get().hasRealmOfType(Administrator.class), "acme.default.error.not-authorised");

		ModelAndView result;

		try {
			this.service.populateAndSaveCoursesFromJson();

			result = new ModelAndView("fragments/welcome");
			result.addObject("_globalSuccessMessage", "acme.default.global.message.success");
		} catch (Exception oops) {
			result = new ModelAndView("master/panic");
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			result.addObject("_globalErrorMessage", "acme.default.global.message.error");
			result.addObject("_oops", oops);
		}

		return result;
	}
}
