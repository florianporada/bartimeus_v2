package nl.itopia.modwillie.core.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * While it doesn't look like it does anything, without this security won't run. 
 * @author Robin de Jong
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}