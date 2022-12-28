package br.com.cams.mainapi.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String path;

}