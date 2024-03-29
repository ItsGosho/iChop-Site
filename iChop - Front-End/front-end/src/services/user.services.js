import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.USER;

const UserServices = {

    hasRole(roles, role) {
        return roles.filter(x => x.authority.toLowerCase() === role.toLowerCase()).length > 0;
    },

    async login(email, password) {
        let response = await Requester.post(Endpoints.LOGIN, {email, password}, true);

        if (response.successful) {
            NotificationHelper.showSuccessNotification(response.message);
        } else {
            NotificationHelper.showErrorNotification(response.message);
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
        return response;
    },

    async changePassword(password, confirmPassword) {
        let response = await Requester.post(Endpoints.CHANGE_PASSWORD, {
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
        let url = Endpoints.ALL_FOLLOWINGS(username);
        let response = await Requester.get(url);

        return response.data;
    },

    async findInformation(username) {
        let url = Endpoints.RETRIEVE_INFORMATION(username);
        let response = await Requester.get(url);

        return response.data;
    },

    async updateInformation(username, statusMessage, birthDate, aboutYou) {
        let url = Endpoints.UPDATE_INFORMATION(username);
        let response = await Requester.post(url, {statusMessage, birthDate, aboutYou});

        NotificationHelper.showNotificationByResponse(response);

        return response;
    },

    async findFollowers(username) {
        let url = Endpoints.ALL_FOLLOWERS(username);
        let response = await Requester.get(url);

        return response.data;
    },

    async follow(username) {
        let url = Endpoints.FOLLOW(username);
        let response = await Requester.post(url);

        NotificationHelper.showNotificationByResponse(response);
    },

    async unfollow(username) {
        let url = Endpoints.UNFOLLOW(username);
        let response = await Requester.post(url);

        NotificationHelper.showNotificationByResponse(response);
    },

    async isFollowing(username, follow) {
        let url = Endpoints.IS_FOLLOWING(username) + '?username=' + follow;
        let response = await Requester.get(url);

        return response.data.result;
    },

    async logout() {
        await Requester.post(Endpoints.LOGOUT, {});
    },

    async adminFindByUsername(username) {
        let query = `?username=${username}`;
        let response = await Requester.get(Endpoints.ADMIN_FIND_BY + query);

        return response.data;
    },

    async rolePromote(username) {
        let response = await Requester.post(Endpoints.ROLE_PROMOTE(username), {});
        NotificationHelper.showNotificationByResponse(response);
    },

    async roleDemote(username) {
        let response = await Requester.post(Endpoints.ROLE_DEMOTE(username), {});
        NotificationHelper.showNotificationByResponse(response);
    },

    async hasNextRole(username) {
        let response = await Requester.get(Endpoints.ROLE_HAS_NEXT(username));
        return response.data ? response.data.result : false;
    },

    async hasPreviousRole(username) {
        let response = await Requester.get(Endpoints.ROLE_HAS_PREVIOUS(username));

        return response.data ? response.data.result : false;
    },

    async nextRole(username) {
        let response = await Requester.get(Endpoints.ROLE_NEXT(username));
        return response.data ? response.data.authority : null;
    },

    async previousRole(username) {
        let response = await Requester.get(Endpoints.ROLE_PREVIOUS(username));
        return response.data ? response.data.authority : null;
    },
};

export default UserServices;