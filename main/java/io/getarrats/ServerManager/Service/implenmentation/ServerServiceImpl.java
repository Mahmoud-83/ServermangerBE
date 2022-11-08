package io.getarrats.ServerManager.Service.implenmentation;

import io.getarrats.ServerManager.Repo.ServerRepo;
import io.getarrats.ServerManager.Service.ServerService;
import io.getarrats.ServerManager.enumaration.Status;
import io.getarrats.ServerManager.model.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.getarrats.ServerManager.enumaration.Status.SERVER_DOWN;
import static io.getarrats.ServerManager.enumaration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
        private final ServerRepo serverRepo;

        @Override
        public Server create(Server server) {
            log.info("Saving new server: {}", server.getName());
            server.setImageUrl(setServerImageUrl());
            return serverRepo.save(server);
        }
    @Override
    public Server ping(String ipAdress) throws IOException {
        log.info("Pinging server IP: {}", ipAdress);
        Server server = serverRepo.findByIpAdress(ipAdress);
        InetAddress address = InetAddress.getByName(ipAdress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
        public Collection<Server> list(int limit) {
        log.info("Fetching All servers ");
            return serverRepo.findAll(PageRequest.of(0,limit)).toList();
        }

        @Override
        public Server get(long id) {
            log.info("Fetching server By ID: {}", id);
            return serverRepo.findById(id).get();
        }

        @Override
        public Server update(Server server) {
            log.info("Updating  server: {}", server.getName());
            return serverRepo.save(server);
        }

        @Override
        public boolean delete(long id) {
            log.info("Deleting  server by ID: {}", id);
            serverRepo.deleteById(id);
            return true;
        }


    private String setServerImageUrl() {
            String [] imageNames = {"server1.png","server2.png","server3.png","server4.png"};
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" +imageNames[new Random().nextInt(4)]).toUriString();
    }


    }
