package com.ichop.core.areas.email.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportsStatisticsDto {

    private List<String> emails;
    private Integer totalReports;
    private Integer totalThreadReports;
    private Integer totalCommentReports;
    private Integer totalPostReports;

}
