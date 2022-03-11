package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception) {

		List<ErroFormularioDto> dto = new ArrayList<ErroFormularioDto>();
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

		fieldError.forEach(error -> {

			String messagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			ErroFormularioDto erro = new ErroFormularioDto(error.getField(), messagem);
			dto.add(erro);

		});

		return dto;

	}

}
