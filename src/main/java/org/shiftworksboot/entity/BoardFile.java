package org.shiftworksboot.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="board_file")
@Getter @Setter
@ToString
public class BoardFile {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_src")
    private String fileSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
