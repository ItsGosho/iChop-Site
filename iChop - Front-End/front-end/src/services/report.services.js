import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";
import EntityTypes from "../constants/enums/entity.types.constants";

const Endpoints = ServerRoutingURLs.CORE.REPORT;

const ReportServices = {

    async reportThread(threadId, reason) {
        return await ReportServices.report(threadId, reason, EntityTypes.THREAD);
    },

    async reportThreadComment(commentId, reason) {
        return await ReportServices.report(commentId, reason, EntityTypes.THREAD_COMMENT);
    },

    async reportUserProfileComment(userProfileCommentId, reason) {
        return await ReportServices.report(userProfileCommentId, reason, EntityTypes.USER_PROFILE_COMMENT);
    },

    async report(entityId, reason, type) {
        let response = await Requester.post(Endpoints.CREATE, {entityId, reason, type});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    },

    async hasReportedThread(username, threadId) {
        return await ReportServices.hasUserReported(username, EntityTypes.THREAD, threadId);
    },

    async hasReportedThreadComment(username, commentId) {
        return await ReportServices.hasUserReported(username, EntityTypes.THREAD_COMMENT, commentId);
    },

    async hasReportedUserProfileComment(username, userProfileCommentId) {
        return await ReportServices.hasUserReported(username, EntityTypes.USER_PROFILE_COMMENT, userProfileCommentId);
    },

    async findBy(type) {
        type = type ? type : null;

        let query = `?${type ? `type=${type}` : ''}`;
        let response = await Requester.get(Endpoints.FIND_BY + query);

        return await response.data;
    },

    async deleteById(id, type) {
        let response = await Requester.post(Endpoints.DELETE_BY_ID, {id, type});
        NotificationHelper.showNotificationByResponse(response);

        return response.successful;
    },

    async hasUserReported(creatorUsername, type, entityId) {
        let query = `?creatorUsername=${creatorUsername}&type=${type}&entityId=${entityId}`;
        let response = await Requester.get(Endpoints.IS_USER_REPORTED + query);

        return response.data.result;
    }

};

export default ReportServices;