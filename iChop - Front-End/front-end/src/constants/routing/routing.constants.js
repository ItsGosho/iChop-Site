import PrefixURLs from "./prefix.routing.constants";

const RoutingURLs = {
    HOME: '/',

    PLAYER: {
        ACCOUNT: {
            LINK: '/player/account/link' /*User+*/
        },
        PROFILE: {
            VIEW: '/player/:uuid/profile', /*Everyone*/
            STATISTICS: '/player/:uuid/profile/statistics' /*Everyone*/
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
            VIEW: (username) => (`/user/${username}/profile`), /*Everyone*/
        },

        ALL: '/users/all', /*Admin+*/

        FOLLOWERS: {
            ALL: '/user/:username/followers/all' /*Everyone+*/
        },

        FOLLOWINGS: {
            ALL: '/user/:username/followings/all' /*Everyone+*/
        },

        CONTROL: {
            INFORMATION: '/user/:username/control/information',
            ROLE: '/user/:username/control/role',
        },

        OPTIONS: {
            INFORMATION: `${PrefixURLs.OPTIONS_PREFIX}/information`, /*User+*/
            PASSWORD: `${PrefixURLs.OPTIONS_PREFIX}/password`, /*User+*/
            MINECRAFT: `${PrefixURLs.OPTIONS_PREFIX}/minecraft`, /*User+*/
        },
    },

    POST: {
        REPORT: {
            ALL: '/post/reports/all' /*Moderator+*/
        }
    },

    COMMENT: {
        REPORT: {
            ALL: '/comment/reports/all' /*Moderator+*/
        }
    },

    THREAD: {
        CREATE: '/thread/create', /*Moderator+*/
        READ: '/thread/:id/read', /*Everyone*/
        REPORT: {
            ALL: '/thread/reports/all' /*Moderator+*/
        }
    },
};

export default RoutingURLs;