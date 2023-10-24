package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import com.example.demo.repos.BookRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import com.example.demo.entities.Book;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		CommandLineRunner cmd;
	}

	@Autowired
	BookRepository bookRepository;

	// Run this if app.db.init.enabled = true
	@Bean
	@ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
	public CommandLineRunner demoCommandLineRunner() {
		return args -> {

			System.out.println("Running.....");

			Book b1 = new Book("Book A",
					BigDecimal.valueOf(9.99),
					LocalDate.of(2023, 8, 31));
			Book b2 = new Book("Book B",
					BigDecimal.valueOf(19.99),
					LocalDate.of(2023, 7, 31));
			Book b3 = new Book("Book C",
					BigDecimal.valueOf(29.99),
					LocalDate.of(2023, 6, 10));
			Book b4 = new Book("Book D",
					BigDecimal.valueOf(39.99),
					LocalDate.of(2023, 5, 5));

			bookRepository.saveAll(List.of(b1, b2, b3, b4));

		};
	}

}
