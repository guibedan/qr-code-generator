package com.guibedan.qr.code.generator.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDataDto {

	private boolean success;
	private String dateTime;
	private Object data;

	public ResponseDataDto() {
		this.setDateTime(LocalDateTime.now().toString());
	}
}
