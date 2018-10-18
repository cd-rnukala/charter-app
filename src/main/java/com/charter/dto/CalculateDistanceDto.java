package com.charter.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CalculateDistanceDto {
	
	@NotNull
	private String fromLocation;
	@NotNull
	private String toLocation;
	@NotNull
	private String planeName;
	private Double totalTimeTaken;

}
