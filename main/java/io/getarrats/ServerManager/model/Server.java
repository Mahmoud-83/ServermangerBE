package io.getarrats.ServerManager.model;


import io.getarrats.ServerManager.enumaration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id

    @GeneratedValue(strategy = AUTO)
    private long id =0;
    @Column(unique= true)
    @NotEmpty(message = "IP Address cannot be empty or null ")
    private String ipAdress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;

    public Server(Long aLong, String ipAdress, String ubuntu_linux, String memory, String personal_pc, String imageUrl, Status serverUp) {
    }


    public long getId() {
        return id;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public String getName() {
        return name;
    }
    public String getMemory() {
        return memory;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Status getStatus() {
        return status;
    }

}
