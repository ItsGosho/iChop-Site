import ServerRoutingURLs from "../constants/server.routing.urls";
import Requester from "../requesters/requester";

const Endpoints = ServerRoutingURLs.CORE.REACTION;

const ReactionServices = {

    async findByCreatorUsernameAndReactionType(creatorUsername, reactionType) {
        let url = Endpoints.FIND_BY + `?creatorUsername=${creatorUsername}&reactionType=${reactionType}`;
        let response = await Requester.get(url);

        return response.data;
    },

};

export default ReactionServices;