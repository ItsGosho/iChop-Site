import ServerRoutingURLs from "../constants/server.routing.urls";

const UserRoutingURLs = ServerRoutingURLs.CORE.USER;

const UserRequester = {

    login(email, password) {

        fetch(UserRoutingURLs.LOGIN, {
            method: ''
        })

    },

    register() {

    }

};