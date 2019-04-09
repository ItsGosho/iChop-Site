package com.ichop.core.scheduled;

import com.ichop.core.areas.report.domain.entities.PostReport;
import com.ichop.core.areas.report.domain.entities.CommentReport;
import com.ichop.core.areas.report.domain.entities.ThreadReport;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.report.repositories.PostReportRepository;
import com.ichop.core.areas.report.repositories.ThreadReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportsEmailSender {

    private final ThreadReportRepository threadReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final PostReportRepository postReportRepository;

    @Autowired
    public ReportsEmailSender(ThreadReportRepository threadReportRepository, CommentReportRepository commentReportRepository, PostReportRepository postReportRepository) {
        this.threadReportRepository = threadReportRepository;
        this.commentReportRepository = commentReportRepository;
        this.postReportRepository = postReportRepository;
    }

    //@Scheduled(fixedDelay = 10000)
    public void sendAllReportsToOwner(){
        List<ThreadReport> threadReports = this.threadReportRepository.findAll();
        List<CommentReport> commentReports = this.commentReportRepository.findAll();
        List<PostReport> postReports = this.postReportRepository.findAll();

        //TODO: da izpratq email tochno kolko sa reportite i link kum vijdaneto im v profila na ownera
    }

}
