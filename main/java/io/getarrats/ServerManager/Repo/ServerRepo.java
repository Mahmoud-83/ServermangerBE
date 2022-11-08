package io.getarrats.ServerManager.Repo;

import io.getarrats.ServerManager.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long > {

    Server findByIpAdress(String ipAdress);

}
