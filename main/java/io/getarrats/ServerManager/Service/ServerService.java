package io.getarrats.ServerManager.Service;

import io.getarrats.ServerManager.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAdress) throws IOException;
    Collection<Server> list(int limit);
    Server get(long id);
    Server update(Server server);
    boolean delete(long id);

}
