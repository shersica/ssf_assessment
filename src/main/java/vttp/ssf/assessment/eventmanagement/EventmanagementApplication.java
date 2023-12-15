package vttp.ssf.assessment.eventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

	@Autowired
	DatabaseService dbSvc;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileDir = "/Users/shersica/vttp/vttp2023-batch4-ssf-assessment/events.json";

		List<Event> eventList = dbSvc.readFile(fileDir);
		System.out.println("List of events");
		for(Event e : eventList){
			System.out.printf("Event ID: %s\n", e.getEventId());
			System.out.printf("Event Name: %s\n", e.getEventName());
			System.out.printf("Event Size : %s\n", e.getEventSize());
			System.out.printf("Event Date: %s\n", e.getEventDate());
			System.out.printf("Participants: %s\n", e.getParticipants());

			dbSvc.saveRecord(e);
		}
		
	}
	
	// TODO: Task 1

}
