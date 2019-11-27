package ichop.reports.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.constants.ReportReplyConstants;
import ichop.reports.constants.ReportRoutingConstants;
import ichop.reports.domain.entities.Report;
import ichop.reports.domain.models.jms.IsUserReportedRequest;
import ichop.reports.domain.models.jms.ReportCreateRequest;
import ichop.reports.domain.models.jms.ReportDeleteByIdRequest;
import ichop.reports.domain.models.jms.ReportReply;
import ichop.reports.domain.models.service.ReportServiceModel;
import ichop.reports.services.ReportServices;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
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
    private final ReportServices reportServices;
    private final ObjectMapper objectMapper;
    private final ValidationHelper validationHelper;

    @Autowired
    public ReportController(ResponseHelpers responseHelpers,
                            ReportServices reportServices,
                            ObjectMapper objectMapper,
                            ValidationHelper validationHelper) {
        this.responseHelpers = responseHelpers;
        this.reportServices = reportServices;
        this.objectMapper = objectMapper;
        this.validationHelper = validationHelper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(ReportRoutingConstants.CREATE)
    public ResponseEntity create(@RequestBody ReportCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        ReportServiceModel report = this.objectMapper.convertValue(request, ReportServiceModel.class);
        this.reportServices.save(report);

        return this.responseHelpers.respondSuccessful(ReportReplyConstants.REPORT_CREATED_SUCCESSFUL, report);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ReportRoutingConstants.DELETE_BY_ID)
    public ResponseEntity deleteById(@RequestBody ReportDeleteByIdRequest request) {

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.reportServices.deleteById(request.getType(), request.getId());

        return this.responseHelpers.respondSuccessful(ReportReplyConstants.REPORT_DELETED_SUCCESSFUL);
    }

    @GetMapping(ReportRoutingConstants.IS_USER_REPORTED)
    public ResponseEntity isUserReported(IsUserReportedRequest request) {

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        boolean hasReported = this.reportServices.hasReported(request.getType(), request.getCreatorUsername(), request.getEntityId());
        BoolReply reply = new BoolReply(hasReported);

        return this.responseHelpers.respondSuccessful(ReportReplyConstants.FETCH_SUCCESSFUL, reply);
    }
}
