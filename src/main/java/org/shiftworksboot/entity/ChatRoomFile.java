package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "chatroom_file")
@Getter @Setter
@ToString
public class ChatRoomFile {

    @Id
    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name ="file_src")
    private String fileSrc;
}
