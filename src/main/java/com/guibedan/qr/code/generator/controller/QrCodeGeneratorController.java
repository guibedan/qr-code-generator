package com.guibedan.qr.code.generator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.guibedan.qr.code.generator.dto.QrCodeDataRequestDto;
import com.guibedan.qr.code.generator.dto.ResponseDataDto;
import com.guibedan.qr.code.generator.service.QrCodeGeneratorService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1/api/qr-code")
public class QrCodeGeneratorController {

	@Autowired
	private QrCodeGeneratorService qrCodeGeneratorService;

	@PostMapping
	public ResponseEntity<ResponseDataDto> getQrCode(@Valid @RequestBody QrCodeDataRequestDto dto)
			throws WriterException, IOException {
		ResponseDataDto responseDto = qrCodeGeneratorService.getQrCode(dto);
		return ResponseEntity.ok(responseDto);
	}

}
