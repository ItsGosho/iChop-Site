import {store} from "react-notifications-component";
import NotificationConstants from "../constants/notification/notification.constants";

const NotificationHelper = {

    showNotification(title, message, type, duration) {
        return store.addNotification({
            title: title,
            message: message,
            type: type,
            insert: "top",
            container: "top-left",
            animationIn: ["animated", "fadeIn"],
            animationOut: ["animated", "fadeOut"],
            showIcon: false,
            width: 250,
            dismiss: {
                duration: duration,
                onScreen: true,
                //pauseOnHover: true,
                click: true
            }
        });
    },

    showNotificationByResponse(response) {
        if(response.successful){
            NotificationHelper.showSuccessNotification(response.message);
        }else{
            NotificationHelper.showErrorNotification(response.error);
        }
    },

    showLoadingNotification(message = ' ') {
        let {TITLE, DURATION} = NotificationConstants.LOADING;
        return NotificationHelper.showNotification(TITLE, message, Types.INFO, DURATION);
    },

    showSuccessNotification(message = ' ') {
        let {TITLE, DURATION} = NotificationConstants.SUCCESS;
        return NotificationHelper.showNotification(TITLE, message, Types.SUCCESS, DURATION);
    },

    showErrorNotification(message = ' ') {
        let {TITLE, DURATION} = NotificationConstants.ERROR;
        return NotificationHelper.showNotification(TITLE, message, Types.DANGER, DURATION);
    },

    removeNotification(id) {
        if (id) {
            store.removeNotification(id);
        }
    }

};

export default NotificationHelper;

const Types = {
    INFO: 'info',
    SUCCESS: 'success',
    DANGER: 'danger'
};