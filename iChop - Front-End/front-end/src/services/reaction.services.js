import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";
import EntityTypes from "../constants/enums/entity.types.constants";

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

    async findAllByIdAndReactionType(id, entityType, reactionType) {
        let url = Endpoints.FIND_BY + `?entityId=${id}&entityType=${entityType}&reactionType=${reactionType}`;
        let response = await Requester.get(url);

        return response.data;
    },

    async reactThread(id, type) {
        ReactionServices.create(id, EntityTypes.THREAD, type);
    },

    async reactComment(id, type) {
        ReactionServices.create(id, EntityTypes.THREAD_COMMENT, type);
    },

    async hasReactedThread(id, username) {
        let url = Endpoints.FIND_BY + `?creatorUsername=${username}&entityId=${id}&entityType=${EntityTypes.THREAD}`;
        let response = await Requester.get(url);

        return response.data !== undefined ? response.data.length > 0 : false;
    },

    async hasReactedThreadComment(id, username) {
        let url = Endpoints.FIND_BY + `?creatorUsername=${username}&entityId=${id}&entityType=${EntityTypes.THREAD_COMMENT}`;
        let response = await Requester.get(url);

        return response.data.length > 0;
    },

    async create(id, entityType, reactionType) {
        let response = await Requester.post(Endpoints.CREATE, {entityId: id, entityType, reactionType});

        NotificationHelper.showNotificationByResponse(response);
    }


};

export default ReactionServices;