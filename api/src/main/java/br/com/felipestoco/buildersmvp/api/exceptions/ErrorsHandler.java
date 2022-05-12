package br.com.felipestoco.buildersmvp.api.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {
	
	private final MessageSource messageSource;

	@Autowired
	public ErrorsHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroFormDTO> errors = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDTO erro = new ErroFormDTO(e.getField(), mensagem);
			errors.add(erro);
		});
		
		return errors;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErroFormDTO handle(NotFoundException exception) {
		return new ErroFormDTO(exception.getMessage(), "Não encontrado");
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public List<ErroFormDTO> handle(ConstraintViolationException exception) {
		List<ErroFormDTO> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
			ErroFormDTO erro = new ErroFormDTO(violation.getPropertyPath().toString(), violation.getMessage());
			errors.add(erro);
		}
		return errors;
	}

}
