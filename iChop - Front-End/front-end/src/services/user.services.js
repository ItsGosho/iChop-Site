import ServerRoutingURLs from "../constants/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.USER;

const UserServices = {

    async login(email, password) {
        let response = await Requester.post(Endpoints.LOGIN, {email, password});
        console.log(response);
    },

    async logout() {

    }

};

export default UserServices;