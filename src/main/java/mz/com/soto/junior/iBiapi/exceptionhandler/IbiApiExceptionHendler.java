package mz.com.soto.junior.iBiapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mz.com.soto.junior.iBiapi.service.exception.DataIntegrityViolationException;

@ControllerAdvice
public class IbiApiExceptionHendler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
		String mensagemTecnica = ex.getCause().toString();
		List<Erro>	erros = Arrays.asList(new Erro(mensagemUsuario,mensagemTecnica));
		return handleExceptionInternal(ex,erros , headers, HttpStatus.BAD_REQUEST, request);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros =  listaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	// lista de erros 
	private List<Erro>listaDeErros(BindingResult bindingResult) {
	List<Erro>	erros = new ArrayList<>();
	
	for(FieldError fieldError : bindingResult.getFieldErrors()) {
	
	String mensagemUsuario =messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	String mensagemTecnica =fieldError.toString();
	erros.add(new Erro(mensagemUsuario,mensagemTecnica));
	}
	return erros;
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(RuntimeException ex, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
		String mensagemTecnica = ex.toString();
		List<Erro>	erros = Arrays.asList(new Erro(mensagemUsuario,mensagemTecnica));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

		
	}
	
	@ExceptionHandler({NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(RuntimeException ex, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("recurso.nullo",null, LocaleContextHolder.getLocale());
		String mensagemTecnica = ex.toString();
		List<Erro>	erros = Arrays.asList(new Erro(mensagemUsuario,mensagemTecnica));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

		
	}
	
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request ){
		
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
		String mensagemTecnica = ex.toString();
		List<Erro>	erros = Arrays.asList(new Erro(mensagemUsuario,mensagemTecnica));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	
	@ExceptionHandler({IncorrectResultSizeDataAccessException.class})
	public ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex, WebRequest request ){
		
		String mensagemUsuario = messageSource.getMessage("recurso-existente",null, LocaleContextHolder.getLocale());
		String mensagemTecnica = ex.toString();
		List<Erro>	erros = Arrays.asList(new Erro(mensagemUsuario,mensagemTecnica));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	public static class Erro {
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		
	}    
    
}
