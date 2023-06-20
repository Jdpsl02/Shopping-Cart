package security.proxy;

import java.io.IOException;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import security.pojo.Admission;
import security.pojo.Order;

@FeignClient(value = "admissionService", url = "http://localhost:9093")
public interface AdmissionProxy {

	@PostMapping(value = "/admission/register/{associateId}/{courseId}", produces = "application/json")
	public ResponseEntity<Admission> registerAssociateForCourse(@PathVariable String associateId,
			@PathVariable String courseId, @RequestHeader("Authorization") String authorization);

	@PutMapping(value = "/admission/calculateFees/{associateId}", produces = "application/json")
	public ResponseEntity<Integer> calculateFees(@PathVariable String associateId,
			@RequestHeader("Authorization") String authorization);

	@PostMapping(value = "/admission/feedback/{regNo}/{feedback}/{feedbackRating}", produces = "application/json")
	public ResponseEntity<Admission> addFeedback(@PathVariable long regNo, @PathVariable String feedback,
			@PathVariable float feedbackRating, @RequestHeader("Authorization") String authorization);

	@GetMapping(value = "/admission/highestFee/{associateId}", produces = "application/json")
	public ResponseEntity<List<String>> highestFeeForTheRegisteredCourse(@PathVariable String associateId,
			@RequestHeader("Authorization") String authorization);

	@GetMapping(value = "/admission/viewFeedbackByCourseId/{courseId}", produces = "application/json")
	public ResponseEntity<List<String>> viewFeedbackByCourseId(@PathVariable String courseId,
			@RequestHeader("Authorization") String authorization);

	@DeleteMapping(value = "/admission/deactivate/{courseId}", produces = "application/json")
	public ResponseEntity<Boolean> deactivateAdmission(@PathVariable String courseId,
			@RequestHeader("Authorization") String authorization);

	@PostMapping(value = "/admission/makePayment/{registartionId}", produces = "application/json")
	public ResponseEntity<String> makePayment(@RequestBody Order order, @PathVariable int registartionId,
			@RequestHeader("Authorization") String authorization);

	@GetMapping(value = "/admission/viewAll", produces = "application/json")
	public ResponseEntity<List<Admission>> viewAll(@RequestHeader("Authorization") String authorization);

}
