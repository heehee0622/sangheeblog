package com.sanghee.test.sangheeblog.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseDateEntity {
    @Column(name = "CREATE_DATE", updatable = false)
    @CreatedDate
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @LastModifiedDate
    private Date updateDate;

}