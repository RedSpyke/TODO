package com.myapp.TODO.dto;

import com.myapp.TODO.model.Task.Priority;
import com.myapp.TODO.model.Task.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private UUID id;
    private String title;
    private String description;
    private Date dueDate;
    private boolean completed;
    private Priority priority;
    private Status status;
    private Date createdAt;
    private Date updatedAt;
    private UUID userId;
}