/*
 * TechnicianCourseShowService.java
 *
 * Copyright (C) 2012-2025 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.technician.course;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.course.Course;
import acme.realms.Technician;

@GuiService
public class TechnicianCourseShowService extends AbstractGuiService<Technician, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private TechnicianCourseRepository repository;

	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Course course;
		int id;

		id = super.getRequest().getData("id", int.class);
		course = this.repository.findCourseById(id);

		super.getBuffer().addData(course);
	}

	@Override
	public void unbind(final Course course) {
		Dataset dataset;

		dataset = super.unbindObject(course, "name", "shortDescription", "org", "blocksUrl", "start", "courseId");

		super.getResponse().addData(dataset);
	}
}
