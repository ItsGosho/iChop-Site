import RequestTypes from "../constants/rest/request.type.constants";
import ContentType from "../constants/rest/content.type.constants";
import RequestHeaders from "../constants/rest/request.header.constants";
import NotificationHelper from "../helpers/notification.helper";
import NotificationConstants from "../constants/notification/notification.constants";


const Requester = {

    async post(url, data, loadingNotification) {

        let notificationId = undefined;
        if (loadingNotification) {
            notificationId = NotificationHelper.showLoadingNotification(NotificationConstants.LOADING)
        }

        let response = await fetch(url, {
            method: RequestTypes.POST,
            credentials: 'include',
            headers: {
                [RequestHeaders.CONTENT_TYPE]: ContentType.JSON
            },
            body: JSON.stringify(data)
        });

        NotificationHelper.removeNotification(notificationId);
        return await response.json();
    },


    async get(url) {
        let response = await fetch(url, {
            credentials: 'include',
        });
        return await response.json();
    }

};

export default Requester;