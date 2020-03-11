package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

	private String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@PostMapping("/setgreeting")
	public void setGreeting(@RequestParam(name = "greeting") String greeting) {
		template = greeting;
	}
}
