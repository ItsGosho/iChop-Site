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
            ALL_FOLLOWINGS: `${CORE_SERVER_DOMAIN}/user/:username/all/followings`,
            ALL_FOLLOWERS: `${CORE_SERVER_DOMAIN}/user/:username/all/followers`,
            RETRIEVE_INFORMATION: `${CORE_SERVER_DOMAIN}/user/:username/information/retrieve`,
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