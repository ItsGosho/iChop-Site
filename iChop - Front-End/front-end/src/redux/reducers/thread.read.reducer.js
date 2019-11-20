import Actions from "../../constants/redux/actions.constants";

let initialState = {
    id: '',
    creatorUsername: '',
    creatorTotalComments: 0,
    title: '',
    content: '',
    createdOn: new Date(),
    views: 0,
    totalReactions: 0,

    comments: [],
};

let threadReadReducer = (state = initialState, action) => {

    if (action.type === Actions.THREAD_READ_SET_THREAD) {
        let {id, creatorUsername, title, content, createdOn, views} = action.payload;

        return Object.assign({}, state, {
            id,
            creatorUsername,
            title,
            content,
            createdOn,
            views
        });
    }

    if (action.type === Actions.THREAD_READ_SET_CREATOR_INFO) {
        let {creatorTotalComments} = action.payload;

        return Object.assign({}, state, {creatorTotalComments});
    }

    if (action.type === Actions.THREAD_READ_INCREASE_REACTIONS) {
        return Object.assign({}, state, {totalReactions: state.totalReactions + 1});
    }

    if (action.type === Actions.THREAD_READ_ADD_COMMENT) {
        let {comment} = action.payload;

        let comments = state.comments;
        comments.push(comment);

        return Object.assign({}, state, {comments});
    }

    if (action.type === Actions.THREAD_READ_SET_STATISTICS) {
        let {totalReactions} = action.payload;

        return Object.assign({}, state, {totalReactions});
    }

    if (action.type === Actions.THREAD_READ_SET_COMMENTS) {
        let {comments} = action.payload;

        return Object.assign({}, state, {comments});
    }

    if (action.type === Actions.THREAD_READ_SET_COMMENT_CREATOR_INFO) {
        let {id, totalComments} = action.payload;

        let comments = [];

        for (const comment of state.comments) {
            if (comment.id === id) {
                comment.creatorTotalComments = totalComments;
            }
            comments.push(comment);
        }

        return Object.assign({}, state, {comments});
    }

    if (action.type === Actions.THREAD_READ_SET_COMMENT_STATISTICS) {
        let {id, likes, dislikes} = action.payload;

        let comments = [];

        for (const comment of state.comments) {
            if (comment.id === id) {
                comment.likes = likes;
                comment.dislikes = dislikes;
            }
            comments.push(comment);
        }

        return Object.assign({}, state, {comments});
    }

    return state;
};

export default threadReadReducer;