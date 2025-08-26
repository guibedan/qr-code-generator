package com.guibedan.qr.code.generator.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.guibedan.qr.code.generator.dto.ResponseDataDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Tratamento de erros de validação (400 - BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDataDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ResponseDataDto response = new ResponseDataDto();
		response.setSuccess(false);
		response.setData(errors);

		logger.warn("Required fields were not filled in");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Tratamento de erros de método não suportado (405 - METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResponseDataDto> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		ResponseDataDto response = new ResponseDataDto();
		response.setSuccess(false);

		Map<String, String> errors = new HashMap<>();
		errors.put("error", "Method '" + ex.getMethod() + "' is not supported for this endpoint.");
		response.setData(errors);

		logger.error("Method '{}' is not supported for this endpoint.", ex.getMethod());
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	// Tratamento de exceções relacionadas ao status HTTP (genérico)
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ResponseDataDto> handleResponseStatusException(ResponseStatusException ex) {
		ResponseDataDto response = new ResponseDataDto();
		response.setSuccess(false);

		Map<String, String> errors = new HashMap<>();
		errors.put("error", ex.getReason());
		response.setData(errors);

		logger.error("Generic error response status", ex);
		return new ResponseEntity<>(response, ex.getStatusCode());
	}

	// Tratamento de exceções gerais (500 - INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDataDto> handleAllExceptions(Exception ex) {
		ResponseDataDto response = new ResponseDataDto();
		response.setSuccess(false);

		Map<String, String> errors = new HashMap<>();
		errors.put("error", "An unexpected error occurred: " + ex.getMessage());
		response.setData(errors);

		logger.error("An unexpected error occurred", ex);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Tratamento de corpo da requisição ausente ou inválido
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseDataDto> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		ResponseDataDto response = new ResponseDataDto();
		response.setSuccess(false);

		Map<String, String> errors = new HashMap<>();
		errors.put("error", "Invalid or missing request body. Please provide 'title' and 'link' in the request body.");
		response.setData(errors);

		logger.error("Invalid or missing request body. Please provide 'title' and 'link' in the request body.");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
