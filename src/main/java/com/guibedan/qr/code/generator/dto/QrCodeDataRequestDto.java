package com.guibedan.qr.code.generator.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QrCodeDataRequestDto {

	@NotEmpty(message = "Title cannot be null or empty")
	private String title;

	@NotEmpty(message = "Link cannot be null or empty")
	private String link;

}
