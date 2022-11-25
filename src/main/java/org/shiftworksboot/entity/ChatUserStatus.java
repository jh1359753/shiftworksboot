package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chatuser_status")
@Getter
@Setter
@ToString
public class ChatUserStatus {

    @Id
    private int status_id;

    private String status;

    private String connect_lasttime;
}
