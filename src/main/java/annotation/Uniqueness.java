package annotation;

public @interface Uniqueness {
    Constrains constrains() default @Constrains(unique=true);
}
