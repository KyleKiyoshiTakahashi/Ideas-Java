package com.codingdojo.Ideas.validators;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.codingdojo.Ideas.models.Idea;


@Component
public class IdeaValidator implements Validator {
	
	@Override
    public boolean supports(Class<?> clazz) {
        return Idea.class.equals(clazz);
    }

	@Override
	public void validate(Object obj, Errors errors) {
//		Idea idea = (Idea) target;
//		
//		errors.rejectValue("name", "Size");
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Idea idea = (Idea) obj;
        if (idea.getName() == null) {
            errors.rejectValue("name", "Size");
        } 
	}
	
}
