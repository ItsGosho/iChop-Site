import FrontEndResourcesRoutingURLs from "../constants/front-end.resources.routings";

const UserHelper = {

    onAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

};

export default UserHelper;