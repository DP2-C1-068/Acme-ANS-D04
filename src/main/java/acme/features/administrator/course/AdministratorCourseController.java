
package acme.features.administrator.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.client.components.principals.Administrator;
import acme.client.controllers.GuiController;
import acme.client.helpers.Assert;
import acme.client.helpers.PrincipalHelper;
import acme.entities.course.Course;
import acme.internals.components.database.DatabaseManager;

@GuiController
public class AdministratorCourseController {

	// Injecting a service that will handle the course population
	@Autowired
	private AdministratorCoursePopulateService	service;

	@Autowired
	private DatabaseManager						databaseManager;
	// Endpoints --------------------------------------------------------------


	@GetMapping("/administrator/course/populate")
	public ModelAndView populateCourse() {
		Assert.state(PrincipalHelper.get().hasRealmOfType(Administrator.class), "acme.default.error.not-authorised");

		ModelAndView result;

		try {
			// 1. Iniciar transacción
			this.databaseManager.startTransaction();

			// 2. Obtener cursos nuevos desde el servicio
			List<Course> newCourses = this.service.fetchNewCoursesFromApi();

			// 3. Persistir nuevos cursos
			if (!newCourses.isEmpty())
				for (Course course : newCourses)
					this.databaseManager.persist(course); // Guardado individual

			// 4. Confirmar transacción
			this.databaseManager.commitTransaction();

			// 5. Mostrar éxito
			result = new ModelAndView("fragments/welcome");
			result.addObject("_globalSuccessMessage", "acme.default.global.message.success");

		} catch (Throwable t) {
			// 6. Deshacer cambios si hay error
			this.databaseManager.rollbackTransaction();

			// 7. Mostrar error
			result = new ModelAndView("master/panic");
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			result.addObject("_globalErrorMessage", "acme.default.global.message.error");
			result.addObject("_oops", t);
		}

		return result;
	}
}
