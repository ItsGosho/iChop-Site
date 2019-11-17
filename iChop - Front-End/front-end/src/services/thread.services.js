import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.THREAD;

const ThreadServices = {

    async create(title, content) {
        return await Requester.post(Endpoints.CREATE, {title, content});
    },

    async getAllPageable(page, size) {
        let query = `?page=${page}&size=${size}`;
        let response = await Requester.get(Endpoints.ALL + query);
        return response.data;
    },

    async getTotal() {
        let response = await Requester.get(Endpoints.TOTAL);
        return response.data;
    },

    async deleteById(id) {
        let url = Endpoints.DELETE_BY_ID.replace(':id',id);
        let response = await Requester.post(url,{});

        NotificationHelper.showNotificationByResponse(response);
        return response;
    },

    async findById(id){
        let query = `?id=${id}`;
        let response = await Requester.get(Endpoints.FIND_BY + query);

        return response.data;
    }

};

export default ThreadServices;