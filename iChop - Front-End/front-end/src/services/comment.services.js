import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.COMMENT;

const CommentServices = {

    async findAllUserProfileComments(username) {
        let url = Endpoints.USER_PROFILE_ALL.replace(':username', username);
        let response = await Requester.get(url);

        return response.data;
    },

    async createUserProfileComment(username, content) {
        let url = Endpoints.USER_PROFILE_CREATE.replace(':username', username);
        let response = await Requester.post(url, {content});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    },

    async deleteUserProfileComment(username,commentId) {
        let url = Endpoints.USER_PROFILE_DELETE
            .replace(':username', username)
            .replace(':commentId',commentId);

        let response = await Requester.post(url, {});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    }


};

export default CommentServices;