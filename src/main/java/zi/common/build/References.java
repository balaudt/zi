package zi.common.build;

/**
 * @author balamurugan
 */
public @interface References {
	Class[] classes() default {};
}
