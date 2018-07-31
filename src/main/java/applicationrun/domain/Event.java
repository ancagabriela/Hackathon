package applicationrun.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity(name = "Event")
public class Event {
	@Id
	private String eventName;
	private String description;
	 // opciones a votar
	private String imageURL;
	 //socios invitados
	
	public Event(String name, String description, BufferedImage image) throws Exception {
		eventName = name;
		this.description = description;
		this.imageURL = name;
		
	}
	
}
