import ServerRoutingURLs from "../constants/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.USER;

const UserServices = {

    async login(email, password) {
        let response = await Requester.post(Endpoints.LOGIN, {email, password}, true);

        if (response.error) {
            NotificationHelper.showErrorNotification(response.error);
        } else {
            NotificationHelper.showSuccessNotification(response.message);
        }

        return response.data;
    },

    async register(username, password, confirmPassword, email) {
        let response = await Requester.post(Endpoints.REGISTER, {username, password, confirmPassword, email}, true);
        NotificationHelper.showNotificationByResponse(response);

        return response.successful;
    },

    async forgottenPassword(email) {
        let response = await Requester.post(Endpoints.FORGOTTEN_PASSWORD, {email}, true);
        NotificationHelper.showNotificationByResponse(response);
    },

    async changePasswordByToken(token, password, confirmPassword) {
        let response = await Requester.post(Endpoints.CHANGE_PASSWORD_BY_TOKEN, {
            token,
            password,
            confirmPassword
        }, true);
        NotificationHelper.showNotificationByResponse(response);
    },

    async retrieveUserByToken() {
        let response = await Requester.get(Endpoints.GET_CURRENT_AUTHENTICATED);

        if (!response.error) {
            return response.data;
        }
    },

    async findByUsername(username) {
        let query = `?username=${username}`;
        let response = await Requester.get(Endpoints.FIND_BY + query);

        return response.data;
    },

    async findFollowings(username) {
        let url = Endpoints.ALL_FOLLOWINGS.replace(':username', username);
        let response = await Requester.get(url);

        return response.data;
    },

    async findInformation(username) {
        let url = Endpoints.RETRIEVE_INFORMATION.replace(':username', username);
        let response = await Requester.get(url);

        return response.data;
    },

    async findFollowers(username) {
        let url = Endpoints.ALL_FOLLOWERS.replace(':username', username);
        let response = await Requester.get(url);

        return response.data;
    },

    async follow(username) {
        let url = Endpoints.FOLLOW.replace(':username', username);
        let response = await Requester.post(url);

        NotificationHelper.showNotificationByResponse(response);
    },

    async unfollow(username) {
        let url = Endpoints.UNFOLLOW.replace(':username', username);
        let response = await Requester.post(url);

        NotificationHelper.showNotificationByResponse(response);
    },

    async isFollowing(username, follow) {
        let url = Endpoints.IS_FOLLOWING.replace(':username', username) + '?username=' + follow;
        let response = await Requester.get(url);

        return response.data.result;
    },

    async logout() {
        await Requester.post(Endpoints.LOGOUT, {});
    }

};

export default UserServices;