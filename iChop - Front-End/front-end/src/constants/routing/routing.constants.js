import PrefixURLs from "./prefix.routing.constants";

const RoutingURLs = {

    HOME: '/',
    PLAYER: {
        ACCOUNT: {
            LINK: '/player/link-account' /*User+*/
        },
        PROFILE: {
            VIEW: (uuid) => `/player/${uuid}/profile`, /*Everyone*/
            STATISTICS: (uuid) => `/player/${uuid}/profile/statistics` /*Everyone*/
        }
    },
    AUTHENTICATION: {
        LOGOUT: '/logout', /*Logged in*/
        TOKEN: {
            RESET_PASSWORD: '/reset/password/by/token' /*Logged out*/
        }
    },
    USER: {
        PROFILE: {
            VIEW: (username) => `/user/${username}/profile`, /*Everyone*/
        },
        ALL: '/users/all', /*Admin+*/
        FOLLOWERS: {
            ALL: (username) => `/user/${username}/followers/all` /*Everyone+*/
        },
        FOLLOWINGS: {
            ALL: (username) => `/user/${username}/followings/all` /*Everyone+*/
        },
        CONTROL: {
            INFORMATION: (username) => `${PrefixURLs.USER_CONTROL_PREFIX(username)}/information`, /*Admin+*/
            ROLE: (username) => `${PrefixURLs.USER_CONTROL_PREFIX(username)}/role`, /*Admin+*/
        },
        OPTIONS: {
            INFORMATION: `${PrefixURLs.OPTIONS_PREFIX}/information`, /*User+*/
            PASSWORD: `${PrefixURLs.OPTIONS_PREFIX}/password`, /*User+*/
            MINECRAFT: `${PrefixURLs.OPTIONS_PREFIX}/minecraft`, /*User+*/
        },
    },
    THREAD: {
        CREATE: '/thread/create', /*Moderator+*/
        READ: (id) => `/thread/${id}/read`, /*Everyone*/
    },
    REPORT: {
        ALL: '/reports/all', /*Moderator++*/
    }
};

export default RoutingURLs;