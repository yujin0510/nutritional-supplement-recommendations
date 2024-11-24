package com.test.nutri.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
//@Table() //테이블명 동일
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadCombination {

	@Id
	private Long seq;
	
	private String bad;
	private String reason;
	private String link;
	private String ingredient_seq;
}
