package nl.itopia.modwillie.core.init;

/**
 * While it doesn't look like it does anything, without this security won't run. 
 * @author Robin de Jong
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}