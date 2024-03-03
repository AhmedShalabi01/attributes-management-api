package com.msa.attributesmanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttributesManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttributesManagementApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(UserAttributesRepository repository){
//		return args -> {
//			UserAttributes user;
//			TimeSchedule timeSchedule = TimeSchedule.getInstance();
//
//			timeSchedule.setStartTime(LocalTime.of(9, 0));
//			timeSchedule.setEndTime(LocalTime.of(17, 0));
//			timeSchedule.setDaysOfWeek(Set.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
//
//			user = new UserAttributes("204209","Engineer","Software Management",timeSchedule,5,"Level 1","Active");
//			repository.insert(user);
//		};
//	}

}
