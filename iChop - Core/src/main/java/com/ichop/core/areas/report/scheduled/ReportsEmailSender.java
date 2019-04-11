package com.ichop.core.areas.report.scheduled;

import com.ichop.core.areas.report.domain.entities.CommentReport;
import com.ichop.core.areas.report.domain.entities.PostReport;
import com.ichop.core.areas.report.domain.entities.ThreadReport;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.report.repositories.PostReportRepository;
import com.ichop.core.areas.report.repositories.ThreadReportRepository;
import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.components.email.EmailServices;
import com.ichop.core.components.email.dtos.ReportsStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportsEmailSender {

    private final ThreadReportRepository threadReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final PostReportRepository postReportRepository;
    private final UserServices userServices;
    private final EmailServices emailServices;

    @Autowired
    public ReportsEmailSender(ThreadReportRepository threadReportRepository, CommentReportRepository commentReportRepository, PostReportRepository postReportRepository, UserServices userServices, EmailServices emailServices) {
        this.threadReportRepository = threadReportRepository;
        this.commentReportRepository = commentReportRepository;
        this.postReportRepository = postReportRepository;
        this.userServices = userServices;
        this.emailServices = emailServices;
    }

    /*
    *
    * On every 12 hours a email is send to the owner
    * with statistic containing total reports for that
    * day by thread,comment and post.
    *
    * */
    @Scheduled(cron = "0 0 */12 ? * *")
    public void sendReportStatisticToTheOwners(){
        LocalDateTime dateTime = LocalDateTime.now().minusHours(12);

        List<ThreadReport> threadReports = this.threadReportRepository.findAll().stream().filter(x-> x.getReportDate().isAfter(dateTime)).collect(Collectors.toList());
        List<CommentReport> commentReports = this.commentReportRepository.findAll().stream().filter(x-> x.getReportDate().isAfter(dateTime)).collect(Collectors.toList());
        List<PostReport> postReports = this.postReportRepository.findAll().stream().filter(x-> x.getReportDate().isAfter(dateTime)).collect(Collectors.toList());

        int totalReportsToday = threadReports.size() + commentReports.size() + postReports.size();
        int totalThreadReports = threadReports.size();
        int totalCommentReports = commentReports.size();
        int totalPostReports = postReports.size();

        List<String> emails = this.userServices.findUsersWhomHasRole(UserRoles.OWNER.name())
                .stream()
                .map(UserServiceModel::getEmail)
                .collect(Collectors.toList());

        ReportsStatisticsDto reportsStatisticsDto = new ReportsStatisticsDto();
        reportsStatisticsDto.setEmails(emails);
        reportsStatisticsDto.setTotalReports(totalReportsToday);
        reportsStatisticsDto.setTotalThreadReports(totalThreadReports);
        reportsStatisticsDto.setTotalCommentReports(totalCommentReports);
        reportsStatisticsDto.setTotalPostReports(totalPostReports);

        this.emailServices.sendReportStatisticsEmail(reportsStatisticsDto);

    }

}
