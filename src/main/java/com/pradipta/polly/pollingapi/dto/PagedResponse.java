package com.pradipta.polly.pollingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<P> {
    private List<?> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
