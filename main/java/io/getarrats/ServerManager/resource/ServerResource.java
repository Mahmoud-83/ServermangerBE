package io.getarrats.ServerManager.resource;

import io.getarrats.ServerManager.Service.implenmentation.ServerServiceImpl;
import io.getarrats.ServerManager.enumaration.Status;
import io.getarrats.ServerManager.model.Response;
import io.getarrats.ServerManager.model.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("servers ",serverService.list(30)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAdress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAdress")String ipAdress) throws IOException {
        Server server = serverService.ping(ipAdress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server ",server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed" )
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server ",serverService.create(server)))
                        .message("Server created " )
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id")long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server ",serverService.get(id)))
                        .message(" Server retrieved" )
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id")long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted ",serverService.delete(id)))
                        .message(" Server deleted " )
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }



    @GetMapping(path = "/image/{fileName}",produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName")String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+ "Desktop/serverimages/" + fileName));

    }

}
