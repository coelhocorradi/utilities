package br.com.utilities.annotaions;

public class CustomValidator implements ConstraintValidator<CustomAnnotation, String> {

	@Override
	public void initialize(CustomAnnotation annotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext validatorContext) {
		return "true".equals(value.trim().toLowerCase());
		/*
		 * if ("true".equals(value.trim().toLowerCase())) return true; return false;
		 */
	}

}
