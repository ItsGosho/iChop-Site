package com.ichop.core.areas.report.domain.models.service;

import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadReportServiceModel extends BaseReportServiceModel {

    private ThreadServiceModel thread;
}
