const RoutingURLs = {
    HOME: '/',

    PLAYER: {
        ACCOUNT: {
            LINK: '/player/account/link' /*User+*/
        },
        PROFILE: {
            VIEW: '/player/:uuid/profile' /*Everyone*/
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

        CONTROL_PANEL: {
            Role: '/user/:username/control/role' /*Admin+*/
        },

        OPTIONS: {
            INFORMATION: '/user/:username/options/information', /*User+*/
            PASSWORD: '/user/:username/options/password', /*User+*/
            MINECRAFT: '/user/:username/options/minecraft', /*User+*/
        },

        /*TODO: move it out from here*/
        AVATAR: 'http://localhost:8001/data/user/:username/avatar'
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