package sg.edu.nus.iss.day29_redo2;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.day29_redo2.repo.EcommerceRepoImpl;

@SpringBootApplication
public class Day29Redo2Application implements CommandLineRunner {

	@Autowired
	private EcommerceRepoImpl repoImpl;

	public static void main(String[] args) {
		SpringApplication.run(Day29Redo2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Document> result = repoImpl.getAllProducts();
		for(Document d :result)
			System.out.printf(">>>>>>>>" + d.toJson().toString());
	}

}
