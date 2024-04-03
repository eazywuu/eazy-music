package xyz.eazywu.music.object.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
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
