package com.eshoppingzone.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*getters, setters, all arguments and no argument constructors by annotations*/
@Data
@NoArgsConstructor
@AllArgsConstructor
/*@document- to specify custom property values*/
/*setting collection name */
@Document(collection="ProductIdSeq")
public class DbSequence {
    /*Providing all variables required */
	
	
	@Id
    private String id;
    private int seq;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

    
    

}