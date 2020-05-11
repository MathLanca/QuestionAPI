package com.mackenzie.cif.question.domain.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Data
@Document(collection = "Question")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {

    @Id
    private String id;
    @NotNull
    private String code;
    @NotNull
    private String title;
    @NotNull
    private String group;

    private String description;
    private String inclusion;
    private String exclusion;
}


