import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.COMMENT;

const CommentServices = {

    async createThreadComment(threadId, content) {
        let url = Endpoints.THREAD_CREATE(threadId);
        let response = await Requester.post(url, {threadId, content});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    },

    async findAllUserProfileComments(username) {
        let url = Endpoints.USER_PROFILE_ALL(username);
        let response = await Requester.get(url);

        return response.data;
    },

    async findAllThreadComments(id) {
        let url = Endpoints.THREAD_ALL(id);
        let response = await Requester.get(url);

        return response.data;
    },

    async findCreatorTotalComments(username) {
        let query = `?username=${username}`;
        let response = await Requester.get(Endpoints.CREATOR_TOTAL_COMMENTS + query);

        return response.data.result;
    },

    async createUserProfileComment(username, content) {
        let url = Endpoints.USER_PROFILE_CREATE(username);
        let response = await Requester.post(url, {content});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    },

    async deleteUserProfileComment(username, commentId) {
        let url = Endpoints.USER_PROFILE_DELETE(username, commentId);

        let response = await Requester.post(url, {});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    },

    async deleteThreadComment(threadId, commentId) {
        let url = Endpoints.THREAD_DELETE(threadId, commentId);

        let response = await Requester.post(url, {});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    }
};

export default CommentServices;