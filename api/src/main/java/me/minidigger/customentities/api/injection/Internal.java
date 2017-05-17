package me.minidigger.customentities.api.injection;

import java.lang.annotation.*;

/**
 * Marker interface that tells the {@link CustomEntitiesFieldResolver} that want to inject API objects, not plugin objects.
 * Example:
 * <pre>
 * &#64;Internal Logger apiLogger;
 * Logger pluginLogger;
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Internal {
}
