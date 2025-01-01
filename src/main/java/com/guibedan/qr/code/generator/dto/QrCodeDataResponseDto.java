package com.guibedan.qr.code.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QrCodeDataResponseDto {

	private String title;
	private String qrCode64;

}
