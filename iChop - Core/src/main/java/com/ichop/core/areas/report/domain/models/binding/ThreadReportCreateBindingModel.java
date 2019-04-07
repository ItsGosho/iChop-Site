package com.ichop.core.areas.report.domain.models.binding;

import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadReportCreateBindingModel extends BaseReportCreateBindingModel {

    private ThreadServiceModel thread;

}
