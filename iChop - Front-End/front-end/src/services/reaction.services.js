import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.REACTION;

const ReactionServices = {

    async findByCreatorUsernameAndReactionType(creatorUsername, reactionType) {
        let url = Endpoints.FIND_BY + `?creatorUsername=${creatorUsername}&reactionType=${reactionType}`;
        let response = await Requester.get(url);

        return response.data;
    },

    async findAllById(id, entityType) {
        let url = Endpoints.FIND_BY + `?entityId=${id}&entityType=${entityType}`;
        let response = await Requester.get(url);

        return response.data;
    },

};

export default ReactionServices;