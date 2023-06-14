package security.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


import security.pojo.Course;


@FeignClient(value="courseService",url="http://localhost:9091")
public interface CourseProxy {
	
//	@GetMapping(value="/course/show",produces = "application/json")
//	public String show();
//	
//	@GetMapping(value="/course/test",produces = "application/json")
//	public String test();
	
	@GetMapping(value="/course/viewByCourseId/{courseId}",produces = "application/json")
	public ResponseEntity<Course> viewByCourseId(@PathVariable("courseId") String courseId,@RequestHeader("Authorization") String authorization);
	
	@PutMapping(value="/course/calculateAverageFeedback/{courseId}/{rating}",produces = "application/json")
	public Course calculateAverageFeedbackAndUpdate(@PathVariable("courseId") String courseId, @PathVariable("rating")  float rating,@RequestHeader("Authorization") String authorization);
	
	@PostMapping(value = "/course/addCourse",produces = "application/json")
	public ResponseEntity<Course> addCourse(@RequestBody @Validated Course cObj,@RequestHeader("Authorization") String authorization);
	
	@PutMapping(value="/course/update/{courseId}/{courseFees}",produces = "application/json")
	public ResponseEntity<Course> updateCourse(@PathVariable String courseId,@PathVariable Integer courseFees,@RequestHeader("Authorization") String authorization);
	
	@GetMapping(value="/course/viewFeedbackRating/{courseId}",produces = "application/json")
	public ResponseEntity<Float> findFeedbackForCourseId(@PathVariable String courseId,@RequestHeader("Authorization") String authorization);
	
	@DeleteMapping(value="/course/deactivate/{courseId}",produces = "application/json")
	public ResponseEntity<Course> deactivateCourse(@PathVariable String courseId,@RequestHeader("Authorization") String authorization);
	
	@GetMapping(value="/course/viewAll",produces = "application/json")
	public ResponseEntity<List<Course>> viewAll(@RequestHeader("Authorization") String authorization);
		

}
