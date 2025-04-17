package com.xamify.student.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XamiFyResponse<X> {
    private String status;
    private String message;
    private X data;
}
