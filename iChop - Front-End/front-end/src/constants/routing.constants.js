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
        LOGOUT: '/auth/logout', /*Logged in*/
        RESET: {
            PASSWORD: '/auth/reset/password' /*Logged out*/
        }
    },

    USER: {

        PROFILE: {
            VIEW: '/user/:username/profile' /*Everyone*/
        },

        ALL: '/users/all', /*Admin+*/

        FOLLOWERS: {
            ALL: '/user/:username/followers/all' /*Everyone+*/
        },

        FOLLOWINGS: {
            ALL: '/user/:username/followings/all' /*Everyone+*/
        },

        CONTROL_PANEL: '/user/:username/control',

        OPTIONS: {
            INFORMATION: '/user/options/information', /*User+*/
            PASSWORD: '/user/options/password', /*User+*/
            MINECRAFT: '/user/options/minecraft', /*User+*/
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
        VIEW: '/thread/:id/view', /*Everyone*/
        REPORT: {
            ALL: '/thread/reports/all' /*Moderator+*/
        }
    },
};

export default RoutingURLs;