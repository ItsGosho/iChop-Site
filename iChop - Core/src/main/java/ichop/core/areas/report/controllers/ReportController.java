package ichop.core.areas.report.controllers;

import ichop.core.areas.report.constants.ReportRoutingConstants;
import ichop.core.areas.report.models.jms.IsUserReportedRequest;
import ichop.core.areas.report.models.jms.ReportCreateRequest;
import ichop.core.areas.report.models.jms.ReportDeleteByIdRequest;
import ichop.core.areas.report.requesters.ReportRequesterImp;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ReportController {

    private final ResponseHelpers responseHelpers;
    private final ReportRequesterImp reportRequesterImp;

    @Autowired
    public ReportController(ResponseHelpers responseHelpers, ReportRequesterImp reportRequesterImp) {
        this.responseHelpers = responseHelpers;
        this.reportRequesterImp = reportRequesterImp;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(ReportRoutingConstants.CREATE)
    public ResponseEntity create(@RequestBody ReportCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        JmsReplyModel reply = this.reportRequesterImp.create(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ReportRoutingConstants.DELETE_BY_ID)
    public ResponseEntity deleteById(@RequestBody ReportDeleteByIdRequest request) {
        JmsReplyModel reply = this.reportRequesterImp.deleteById(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(ReportRoutingConstants.IS_USER_REPORTED)
    public ResponseEntity isUserReported(IsUserReportedRequest request) {
        JmsReplyModel reply = this.reportRequesterImp.isUserReported(request);

        return this.responseHelpers.respondGeneric(reply);
    }
}
