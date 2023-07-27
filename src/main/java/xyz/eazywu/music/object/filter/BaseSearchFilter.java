package xyz.eazywu.music.object.filter;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BaseSearchFilter {
    private String name;

    @Min(value = 1, message = "page最小值为1")
    private Integer page = 1;

    @Min(value = 1, message = "size最小值为1")
    private Integer size = 10;
}
