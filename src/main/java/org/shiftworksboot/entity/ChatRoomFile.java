package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "chatroom_file")
@Getter @Setter
@ToString
public class ChatRoomFile {

    @Id
    private String file_id;

    private String file_name;

    private String file_src;
}
