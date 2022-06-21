package br.com.utilities.annotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CustomValidator.class })
public @interface CustomAnnotation {

	public String value() default "false";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
