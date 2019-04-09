package com.ichop.core.areas.report.domain.entities;

import com.ichop.core.areas.thread.domain.entities.Thread;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "ThreadReport")
@Table(name = "threads_reports")
public class ThreadReport extends BaseReport {

    @ManyToOne(targetEntity = Thread.class,cascade = CascadeType.REMOVE)
    private Thread thread;

}
