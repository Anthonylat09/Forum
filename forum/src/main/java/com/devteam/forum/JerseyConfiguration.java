package com.devteam.forum;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.devteam.forum.categories.CategorieResource;
import com.devteam.forum.messages.MessageResource;
import com.devteam.forum.personnes.PersonneResource;
import com.devteam.forum.sujets.SujetResource;

@Component
@ApplicationPath("forum")
public class JerseyConfiguration extends ResourceConfig{
	
	public JerseyConfiguration() {
		
		register(PersonneResource.class);
		register(CategorieResource.class);
		register(SujetResource.class);
		register(MessageResource.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}

}
