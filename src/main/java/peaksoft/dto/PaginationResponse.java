package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter @AllArgsConstructor
public class PaginationResponse {
    private List<StudentResponse>students;
    private int currentPage;
    private int pageSize;

}
