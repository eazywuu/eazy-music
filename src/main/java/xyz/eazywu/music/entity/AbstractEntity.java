package xyz.eazywu.music.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "xyz.eazywu.music.utils.KsuidIdentifierGenerator")
    private String id;

    /**
     * 创建时间
     */
    @CreationTimestamp
    private Date createdTime;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    private Date updatedTime;
}
