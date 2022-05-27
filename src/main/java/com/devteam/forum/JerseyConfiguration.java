package com.devteam.forum;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.devteam.forum.personnes.PersonneResource;

@Component
@ApplicationPath("forum")
public class JerseyConfiguration extends ResourceConfig{
	
	public JerseyConfiguration() {
		
		register(PersonneResource.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}

}
