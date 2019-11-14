import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.THREAD;

const ThreadServices = {

    async create(title,content) {
        return await Requester.post(Endpoints.CREATE, {title, content});
    },

};

export default ThreadServices;