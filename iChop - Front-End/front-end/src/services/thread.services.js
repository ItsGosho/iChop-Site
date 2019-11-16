import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.THREAD;

const ThreadServices = {

    async create(title, content) {
        return await Requester.post(Endpoints.CREATE, {title, content});
    },

    async getAllPageable(page, size) {
        let query = `?page=${page}&size=${size}`;
        let response = await Requester.get(Endpoints.ALL + query);
        return response.data;
    }

};

export default ThreadServices;