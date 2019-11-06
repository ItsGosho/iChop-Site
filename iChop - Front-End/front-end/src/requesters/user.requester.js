import ServerRoutingURLs from "../constants/server.routing.urls";
import RequestTypes from "../constants/rest/request.type.constants";
import ContentType from "../constants/rest/content.type.constants";

const UserRoutingURLs = ServerRoutingURLs.CORE.USER;

const UserRequester = {

    login(email, password) {

        fetch(UserRoutingURLs.LOGIN, {
            method: RequestTypes.POST,
            headers: {
                'Content-Type': ContentType.JSON
            }
        })

    },

    register() {

    }

};