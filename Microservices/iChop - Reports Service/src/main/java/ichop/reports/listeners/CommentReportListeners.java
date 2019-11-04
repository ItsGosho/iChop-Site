package ichop.reports.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.models.jms.CommentReportReply;
import ichop.reports.domain.models.jms.all.pageable.CommentReportsAllPageableRequest;
import ichop.reports.domain.models.jms.create.CommentReportCreateRequest;
import ichop.reports.domain.models.jms.delete.CommentReportDeleteByIdRequest;
import ichop.reports.domain.models.service.CommentReportServiceModel;
import ichop.reports.services.CommentReportServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;

import static ichop.reports.constants.ReportReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

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
    public CommentReportReply create(Message message) {
        CommentReportCreateRequest requestModel = this.jmsHelper.toModel(message, CommentReportCreateRequest.class);

        CommentReportServiceModel commentReport = this.objectMapper.convertValue(requestModel, CommentReportServiceModel.class);

        return this.commentReportServices.save(commentReport, CommentReportReply.class);
    }

    @JmsValidate(model = CommentReportDeleteByIdRequest.class)
    @JmsAfterReturn(message = REPORT_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.comment.delete_by_id}", containerFactory = QUEUE)
    public EmptyReply deleteByCommentId(Message message) {
        CommentReportDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, CommentReportDeleteByIdRequest.class);

        this.commentReportServices.deleteByCommentId(requestModel.getCommentId(),requestModel.getType());

        return new EmptyReply();
    }

    @JmsValidate(model = CommentReportsAllPageableRequest.class)
    @JmsAfterReturn(message = REPORTS_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.comment.all_pageable}", containerFactory = QUEUE)
    public List<CommentReportReply> allPageable(Message message) {
        CommentReportsAllPageableRequest requestModel = this.jmsHelper.toModel(message, CommentReportsAllPageableRequest.class);

        return this.commentReportServices.findAll(requestModel.getPageable(),requestModel.getType(), CommentReportReply.class);
    }

}
