package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.entity.TaskFile;

@Getter @Setter @ToString
public class TaskFileDto {

    private String uuid;

    private Task task;

    private String file_name;

    private String file_src;


    public TaskFileDto () {
    }

    public TaskFileDto (TaskFile taskFile) {
        this.uuid = taskFile.getUuid();
        this.task = taskFile.getTask();
        this.file_name = taskFile.getFile_name();
        this.file_src = taskFile.getFile_src();
    }
    private static ModelMapper modelMapper = new ModelMapper();
    public TaskFile createTaskFile() {
        return modelMapper.map(this, TaskFile.class);
    }


}
