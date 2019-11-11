import ServerRoutingURLs from "../constants/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.COMMENT;

const CommentServices = {

    async findAllUserProfileComments(username) {
        let url = Endpoints.USER_PROFILE_ALL.replace(':username',username);
        let response = await Requester.get(url);

        return response.data;
    },


};

export default CommentServices;