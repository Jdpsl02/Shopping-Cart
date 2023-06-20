package security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.pojo.Admission;
import security.pojo.Associate;
import security.pojo.Course;
import security.proxy.AdmissionProxy;
import security.proxy.AssociateProxy;
import security.proxy.CourseProxy;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private CourseProxy courseproxy;

	@Autowired
	private AssociateProxy associateproxy;

	@Autowired
	private AdmissionProxy admissionproxy;

//	===================================Course Service ======================================================================================================================================

	@GetMapping(value = "/course/viewAll", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Course>> viewAll(@RequestHeader("Authorization") String authorization) {
		return courseproxy.viewAll(authorization);
	}

	@PutMapping(value = "/course/calculateAverageFeedback/{courseId}/{rating}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public Course calculateAverageFeedbackAndUpdate(@PathVariable("courseId") String courseId,
			@PathVariable("rating") float rating, @RequestHeader("Authorization") String authorization) {
		return courseproxy.calculateAverageFeedbackAndUpdate(courseId, rating, authorization);
	}

	@PostMapping(value = "/course/addCourse", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Course> addCourse(@RequestBody @Validated Course cObj,
			@RequestHeader("Authorization") String authorization) {
		return courseproxy.addCourse(cObj, authorization);
	}

	@PutMapping(value = "/course/update/{courseId}/{courseFees}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Course> updateCourse(@PathVariable String courseId, @PathVariable Integer courseFees,
			@RequestHeader("Authorization") String authorization) {
		return courseproxy.updateCourse(courseId, courseFees, authorization);
	}

	@GetMapping(value = "/course/viewFeedbackRating/{courseId}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Float> findFeedbackForCourseId(@PathVariable String courseId,
			@RequestHeader("Authorization") String authorization) {
		return courseproxy.findFeedbackForCourseId(courseId, authorization);
	}

	@DeleteMapping(value = "/course/deactivate/{courseId}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Course> deactivateCourse(@PathVariable String courseId,
			@RequestHeader("Authorization") String authorization) {
		return courseproxy.deactivateCourse(courseId, authorization);
	}

//	===================================Associate Service ======================================================================================================================================

	@GetMapping(value = "/associate/viewAll", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Associate>> viewAll1(@RequestHeader("Authorization") String authorization) {
		return associateproxy.viewAll(authorization);
	}

	@PostMapping(value = "/associate/addAssociate", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Associate> addAssociate(@RequestBody @Validated Associate cObj,
			@RequestHeader("Authorization") String authorization) {
		return associateproxy.addAssociate(cObj, authorization);
	}

//	===================================Admission Service ======================================================================================================================================

	@PutMapping(value = "/admission/calculateFees/{associateId}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Integer> calculateFees(@PathVariable String associateId,
			@RequestHeader("Authorization") String authorization) {
		return admissionproxy.calculateFees(associateId, authorization);
	}

	@GetMapping(value = "/admission/highestFee/{associateId}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<String>> highestFeeForTheRegisteredCourse(@PathVariable String associateId,
			@RequestHeader("Authorization") String authorization) {
		return admissionproxy.highestFeeForTheRegisteredCourse(associateId, authorization);
	}

	@GetMapping(value = "/admission/viewFeedbackByCourseId/{courseId}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<String>> viewFeedbackByCourseId(@PathVariable String courseId,
			@RequestHeader("Authorization") String authorization) {
		return admissionproxy.viewFeedbackByCourseId(courseId, authorization);
	}

	@DeleteMapping(value = "/admission/deactivate/{courseId}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Boolean> deactivateAdmission(@PathVariable String courseId,
			@RequestHeader("Authorization") String authorization) {
		return admissionproxy.deactivateAdmission(courseId, authorization);
	}

	@GetMapping(value = "/admission/viewAll", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Admission>> viewAl(@RequestHeader("Authorization") String authorization) {
		return admissionproxy.viewAll(authorization);
	}

}
