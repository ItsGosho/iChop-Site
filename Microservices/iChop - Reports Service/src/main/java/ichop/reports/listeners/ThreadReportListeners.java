package ichop.reports.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.common.aop.JmsAfterReturn;
import ichop.reports.common.aop.JmsValidate;
import ichop.reports.common.helpers.JmsHelper;
import ichop.reports.domain.models.jms.all.pageable.reply.ReportsAllPageableReply;
import ichop.reports.domain.models.jms.all.pageable.request.ThreadReportsAllPageableRequest;
import ichop.reports.domain.models.jms.create.reply.ThreadReportCreateReply;
import ichop.reports.domain.models.jms.create.request.ThreadReportCreateRequest;
import ichop.reports.domain.models.jms.delete.reply.ReportDeleteByIdReply;
import ichop.reports.domain.models.jms.delete.request.ThreadReportDeleteByIdRequest;
import ichop.reports.domain.models.service.ThreadReportServiceModel;
import ichop.reports.services.ThreadReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;

import static ichop.reports.common.constants.JmsFactories.QUEUE;
import static ichop.reports.constants.ReportReplyConstants.*;

@Component
public class ThreadReportListeners {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final ThreadReportServices threadReportServices;

    @Autowired
    public ThreadReportListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadReportServices threadReportServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.threadReportServices = threadReportServices;
    }


    @JmsValidate(model = ThreadReportCreateRequest.class)
    @JmsAfterReturn(message = REPORT_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.thread.create}", containerFactory = QUEUE)
    public ThreadReportCreateReply create(Message message) {
        ThreadReportCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadReportCreateRequest.class);

        ThreadReportServiceModel threadReport = this.objectMapper.convertValue(requestModel, ThreadReportServiceModel.class);

        return this.threadReportServices.save(threadReport, ThreadReportCreateReply.class);
    }

    @JmsValidate(model = ThreadReportDeleteByIdRequest.class)
    @JmsAfterReturn(message = REPORT_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.thread.delete_by_id}", containerFactory = QUEUE)
    public ReportDeleteByIdReply delteById(Message message) {
        ThreadReportDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadReportDeleteByIdRequest.class);

        this.threadReportServices.deleteByThreadId(requestModel.getThreadId());

        return new ReportDeleteByIdReply();
    }

    @JmsValidate(model = ThreadReportsAllPageableRequest.class)
    @JmsAfterReturn(message = REPORTS_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.thread.all_pageable}", containerFactory = QUEUE)
    public ReportsAllPageableReply<ThreadReportServiceModel> allPageable(Message message) {
        ThreadReportsAllPageableRequest requestModel = this.jmsHelper.getResultModel(message, ThreadReportsAllPageableRequest.class);

        List<ThreadReportServiceModel> reports = this.threadReportServices.findAll(requestModel.getPageable(), ThreadReportServiceModel.class);

        return new ReportsAllPageableReply<>(reports);
    }

}
