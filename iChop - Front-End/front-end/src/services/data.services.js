import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.DATA_STORAGE;

const DataStorageServices = {

    async setUserAvatar(username, avatar) {
        let url = Endpoints.SET_USER_AVATAR(username);
        await Requester.post(url, {avatar: avatar});
    },
};

export default DataStorageServices;