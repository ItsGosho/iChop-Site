const RoutingURLs = {
    HOME: '/',

    AUTHENTICATION: {
        LOGOUT: '/logout'
    },

    USER: {
        PROFILE: '/user/:username/profile',
        ALL: '/user/all',
        PROFILE_OPTIONS_INFORMATION: '/user/my-profile/options/information',
        AVATAR: 'http://localhost:8001/data/user/:username/avatar'
    },

    THREAD: {
        CREATE: '/thread/create'
    },

    COMMENT: {
        REPORTS_ALL: '/comment/reports/all'
    },
};

export default RoutingURLs;