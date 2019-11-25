const CORE_SERVER_DOMAIN = 'http://localhost:8000';
const DATA_SERVER_DOMAIN = 'http://localhost:8007';
const OTHER_CRAFATAR_SERVER_DOMAIN = 'https://crafatar.com';
const OTHER_MINOTAR_SERVER_DOMAIN = 'https://minotar.net';

const ServerRoutingURLs = {
    CORE: {
        USER: {
            LOGIN: `${CORE_SERVER_DOMAIN}/auth/login`,
            REGISTER: `${CORE_SERVER_DOMAIN}/auth/register`,
            LOGOUT: `${CORE_SERVER_DOMAIN}/auth/logout`,
            GET_CURRENT_AUTHENTICATED: `${CORE_SERVER_DOMAIN}/user/current/authenticated`,
            FORGOTTEN_PASSWORD: `${CORE_SERVER_DOMAIN}/user/forgotten/password`,
            CHANGE_PASSWORD_BY_TOKEN: `${CORE_SERVER_DOMAIN}/user/change/password/by/token`,
            CHANGE_PASSWORD: `${CORE_SERVER_DOMAIN}/user/change/password`,
            FIND_BY: `${CORE_SERVER_DOMAIN}/user/find/by`,

            RETRIEVE_INFORMATION: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/information/retrieve`,
            UPDATE_INFORMATION: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/information/update`,

            FOLLOW: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/follow`,
            UNFOLLOW: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/unfollow`,
            ALL_FOLLOWINGS: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/all/followings`,
            ALL_FOLLOWERS: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/all/followers`,
            IS_FOLLOWING: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/is/following`
        },
        COMMENT: {
            THREAD_ALL: (id) => `${CORE_SERVER_DOMAIN}/thread/${id}/comment/all`,
            THREAD_DELETE: (threadId, commentId) => `${CORE_SERVER_DOMAIN}/thread/${threadId}/comment/${commentId}/delete`,
            THREAD_CREATE: (id) => `${CORE_SERVER_DOMAIN}/thread/${id}/comment/create`,
            CREATOR_TOTAL_COMMENTS: `${CORE_SERVER_DOMAIN}/comments/total/by/creator`,
            USER_PROFILE_ALL: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/comment/all`,
            USER_PROFILE_CREATE: (username) => `${CORE_SERVER_DOMAIN}/user/${username}/comment/create`,
            USER_PROFILE_DELETE: (username, commentId) => `${CORE_SERVER_DOMAIN}/user/${username}/comment/${commentId}/delete`,
        },
        REACTION: {
            FIND_BY: `${CORE_SERVER_DOMAIN}/reaction/find/by`,
            CREATE: `${CORE_SERVER_DOMAIN}/reaction/create`
        },
        THREAD: {
            CREATE: `${CORE_SERVER_DOMAIN}/thread/create`,
            ALL: `${CORE_SERVER_DOMAIN}/thread/find/all`,
            TOTAL: `${CORE_SERVER_DOMAIN}/thread/find/total`,
            DELETE_BY_ID: id => `${CORE_SERVER_DOMAIN}/thread/${id}/delete`,
            FIND_BY: `${CORE_SERVER_DOMAIN}/thread/find/by`
        },
        REPORT: {
            CREATE: `${CORE_SERVER_DOMAIN}/report/create`,
            DELETE_BY_ID: `${CORE_SERVER_DOMAIN}/report/delete/by/id`,
            IS_USER_REPORTED: `${CORE_SERVER_DOMAIN}/report/is/user/reported`,
        },
        DATA_STORAGE: {
            SET_USER_AVATAR: (username) => `${CORE_SERVER_DOMAIN}/data/set/user/${username}/avatar`,
        }
    },
    DATA: {
        USER: {
            AVATAR: {
                GET: (username) => `${DATA_SERVER_DOMAIN}/data/user/${username}/avatar`
            }
        }
    },
    OUTSIDE: {
        CRAFATAR: {
            MINECRAFT: {
                SKIN: (uuid) => `${OTHER_CRAFATAR_SERVER_DOMAIN}/renders/body/${uuid}`
            }
        },
        MINOTAR: {
            MINECRAFT: {
                HEAD: (accountName) => `${OTHER_MINOTAR_SERVER_DOMAIN}/avatar/${accountName}`
            }
        }
    }
};

export default ServerRoutingURLs;