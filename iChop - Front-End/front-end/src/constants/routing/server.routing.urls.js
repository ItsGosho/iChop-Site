const CORE_SERVER_DOMAIN = 'http://localhost:8000';
const DATA_SERVER_DOMAIN = 'http://localhost:8001';
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
            FIND_BY: `${CORE_SERVER_DOMAIN}/user/find/by`,
            RETRIEVE_INFORMATION: `${CORE_SERVER_DOMAIN}/user/:username/information/retrieve`,

            FOLLOW: `${CORE_SERVER_DOMAIN}/user/:username/follow`,
            UNFOLLOW: `${CORE_SERVER_DOMAIN}/user/:username/unfollow`,
            ALL_FOLLOWINGS: `${CORE_SERVER_DOMAIN}/user/:username/all/followings`,
            ALL_FOLLOWERS: `${CORE_SERVER_DOMAIN}/user/:username/all/followers`,
            IS_FOLLOWING: `${CORE_SERVER_DOMAIN}/user/:username/is/following`
        },
        COMMENT: {
            USER_PROFILE_ALL: `${CORE_SERVER_DOMAIN}/user/:username/comment/all`,
            USER_PROFILE_CREATE: `${CORE_SERVER_DOMAIN}/user/:username/comment/create`,
            USER_PROFILE_DELETE: `${CORE_SERVER_DOMAIN}/user/:username/comment/:commentId/delete`,
        },
        REACTION: {
            FIND_BY: `${CORE_SERVER_DOMAIN}/reaction/find/by`
        }
    },
    DATA: {
        USER: {
            AVATAR: {
                GET: `${DATA_SERVER_DOMAIN}/data/user/:username/avatar`
            }
        }
    },
    OUTSIDE: {
        CRAFATAR: {
            MINECRAFT: {
                SKIN: `${OTHER_CRAFATAR_SERVER_DOMAIN}/renders/body/:uuid`
            }
        },
        MINOTAR: {
            MINECRAFT: {
                HEAD: `${OTHER_MINOTAR_SERVER_DOMAIN}/avatar/:accountName`
            }
        }
    }
};

export default ServerRoutingURLs;