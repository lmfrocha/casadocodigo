package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.model.Usuario;

public class UsuarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		
		Usuario usr = (Usuario) target;
		
		if(usr.getPassword().length() < 5) {
			errors.rejectValue("senha", "field.required");
		}
		if(!usr.getPassword().equals(usr.getConfirmaSenha())) {
			errors.rejectValue("confirmaSenha", "field.required");
		}
		
	}

}
