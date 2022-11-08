package io.getarrats.ServerManager;

import io.getarrats.ServerManager.Repo.ServerRepo;
import io.getarrats.ServerManager.enumaration.Status;
import io.getarrats.ServerManager.model.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerApplication.class, args);
	}
	@Bean
	CommandLineRunner run (ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.168", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.168","Fedora Linux","16 GB","Dell tower","http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null,"192.168.1.168","MS 2008","32 GB","Web Server","http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.168","Red Hat Entr Linux","64 GB","Mail Server","http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
		};

	}


}
