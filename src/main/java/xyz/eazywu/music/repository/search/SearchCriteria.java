package xyz.eazywu.music.repository.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 搜索标准
 */
public class SearchCriteria {
    /**
     * 实体字段名
     */
    private String key;
    /**
     * 实体字段值
     */
    private Object value;
    /**
     * 搜索选项
     */
    private SearchOperation operation;
}
