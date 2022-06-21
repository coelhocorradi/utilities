package br.com.utilities.annotaions;

public @interface Constraint {

	Class<CustomValidator>[] validatedBy();
}
