package com.guibedan.qr.code.generator.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.guibedan.qr.code.generator.dto.QrCodeDataRequestDto;
import com.guibedan.qr.code.generator.dto.QrCodeDataResponseDto;
import com.guibedan.qr.code.generator.dto.ResponseDataDto;

@Service
public class QrCodeGeneratorService {

	public ResponseDataDto getQrCode(QrCodeDataRequestDto data) throws WriterException, IOException {
		ResponseDataDto response = new ResponseDataDto();

		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(data.getLink(), BarcodeFormat.QR_CODE, 300, 300);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();

		String qrCode64 = Base64.getEncoder().encodeToString(pngData);

		QrCodeDataResponseDto responseDto = new QrCodeDataResponseDto(data.getTitle(), qrCode64);

		response.setSuccess(true);
		response.setData(responseDto);
		return response;
	}

}
