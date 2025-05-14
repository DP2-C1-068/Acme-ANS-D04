
package acme.entities.course;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseApiResponse {

	private List<CourseData>	results;
	private Pagination			pagination;


	@Getter
	@Setter
	public static class CourseData {

		private String	blocks_url;
		private String	effort;
		private String	end;
		private String	enrollment_start;
		private String	enrollment_end;
		private String	id;
		private Media	media;
		private String	name;
		private String	number;
		private String	org;
		private String	short_description;
		private String	start;
		private String	start_display;
		private String	start_type;
		private String	pacing;
		private boolean	mobile_available;
		private boolean	hidden;
		private boolean	invitation_only;
		private String	course_id;


		public Course toCourse() {
			Course course = new Course();
			course.setBlocksUrl(this.blocks_url);
			course.setName(this.name);
			course.setOrg(this.org);
			course.setShortDescription(this.short_description);
			course.setStart(this.start);
			course.setCourseId(this.course_id);
			return course;
		}
	}

	@Getter
	@Setter
	public static class Pagination {

		private String	next;
		private String	previous;
		private int		count;
		private int		num_pages;
	}

	@Getter
	@Setter
	public static class Media {

		private BannerImage	banner_image;
		private CourseImage	course_image;
		private CourseVideo	course_video;
		private Image		image;
	}

	@Getter
	@Setter
	public static class BannerImage {

		private String	uri;
		private String	uri_absolute;
	}

	@Getter
	@Setter
	public static class CourseImage {

		private String uri;
	}

	@Getter
	@Setter
	public static class CourseVideo {

		private String uri;
	}

	@Getter
	@Setter
	public static class Image {

		private String	raw;
		private String	small;
		private String	large;
	}
}
