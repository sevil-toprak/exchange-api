package com.akinon.exchangeapi.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}