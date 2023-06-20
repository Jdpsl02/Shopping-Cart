
package security.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Course implements Serializable{
	@Id
	private String courseId;
	
	private String courseName;
	
	private Integer fees;
	
	private Integer duration;
	
	
	private String courseType;
	private float rating;
	
	
}
