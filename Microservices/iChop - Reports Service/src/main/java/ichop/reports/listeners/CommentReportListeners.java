package ichop.reports.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.common.aop.JmsAfterReturn;
import ichop.reports.common.aop.JmsValidate;
import ichop.reports.common.helpers.BaseListener;
import ichop.reports.common.helpers.JmsHelper;
import ichop.reports.domain.models.jms.all.pageable.reply.ReportsAllPageableReply;
import ichop.reports.domain.models.jms.all.pageable.request.CommentReportsAllPageableRequest;
import ichop.reports.domain.models.jms.create.reply.CommentReportCreateReply;
import ichop.reports.domain.models.jms.create.request.CommentReportCreateRequest;
import ichop.reports.domain.models.jms.delete.reply.ReportDeleteByIdReply;
import ichop.reports.domain.models.jms.delete.request.CommentReportDeleteByIdRequest;
import ichop.reports.domain.models.service.CommentReportServiceModel;
import ichop.reports.services.CommentReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;

import static ichop.reports.common.constants.JmsFactories.QUEUE;
import static ichop.reports.constants.ReportReplyConstants.*;

@Component
public class CommentReportListeners extends BaseListener {


    private final CommentReportServices commentReportServices;

    @Autowired
    protected CommentReportListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, CommentReportServices commentReportServices) {
        super(jmsHelper, objectMapper);
        this.commentReportServices = commentReportServices;
    }


    @JmsValidate(model = CommentReportCreateRequest.class)
    @JmsAfterReturn(message = REPORT_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.comment.create}", containerFactory = QUEUE)
    public CommentReportCreateReply create(Message message) {
        CommentReportCreateRequest requestModel = this.jmsHelper.getResultModel(message, CommentReportCreateRequest.class);

        CommentReportServiceModel commentReport = this.objectMapper.convertValue(requestModel, CommentReportServiceModel.class);

        return this.commentReportServices.save(commentReport, CommentReportCreateReply.class);
    }

    @JmsValidate(model = CommentReportDeleteByIdRequest.class)
    @JmsAfterReturn(message = REPORT_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.comment.delete_by_id}", containerFactory = QUEUE)
    public ReportDeleteByIdReply deleteByCommentId(Message message) {
        CommentReportDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, CommentReportDeleteByIdRequest.class);

        this.commentReportServices.deleteByCommentId(requestModel.getCommentId(),requestModel.getType());

        return new ReportDeleteByIdReply();
    }

    @JmsValidate(model = CommentReportsAllPageableRequest.class)
    @JmsAfterReturn(message = REPORTS_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.comment.all_pageable}", containerFactory = QUEUE)
    public ReportsAllPageableReply<CommentReportServiceModel> allPageable(Message message) {
        CommentReportsAllPageableRequest requestModel = this.jmsHelper.getResultModel(message, CommentReportsAllPageableRequest.class);

        List<CommentReportServiceModel> reports = this.commentReportServices.findAll(requestModel.getPageable(),requestModel.getType(), CommentReportServiceModel.class);

        return new ReportsAllPageableReply<>(reports);
    }

}
