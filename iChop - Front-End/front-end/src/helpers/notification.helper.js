import {store} from "react-notifications-component";
import NotificationConstants from "../constants/notification.constants";

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
            dismiss: {
                duration: duration,
                onScreen: true,
                //pauseOnHover: true,
                click: true
            }
        });
    },

    showLoadingNotification(message = ' ') {
        let {TITLE,DURATION} = NotificationConstants.LOADING;
        return NotificationHelper.showNotification(TITLE, message, 'info', DURATION);
    },

    showSuccessNotification(message = ' ') {
        let {TITLE,DURATION} = NotificationConstants.SUCCESS;
        return NotificationHelper.showNotification(TITLE, message, 'success', DURATION);
    },

    showErrorNotification(message = ' ') {
        let {TITLE,DURATION} = NotificationConstants.ERROR;
        return NotificationHelper.showNotification(TITLE, message, 'danger', DURATION);
    },

    removeNotification(id) {
        store.removeNotification(id);
    }

};

export default NotificationHelper;