const USERS_SERVICE_DOMAIN = 'http://localhost:8001';
const THREADS_SERVICE_DOMAIN = 'http://localhost:8003';
const REPORTS_SERVICE_DOMAIN = 'http://localhost:8004';
const REACTIONS_SERVICE_DOMAIN = 'http://localhost:8005';
const STORAGE_SERVICE_DOMAIN = 'http://localhost:8007';
const COMMENTS_SERVICE_DOMAIN = 'http://localhost:8008';
const LINK_ACCOUNT_SERVICE_DOMAIN = 'http://localhost:8009';

const OTHER_CRAFATAR_SERVER_DOMAIN = 'https://crafatar.com';
const OTHER_MINOTAR_SERVER_DOMAIN = 'https://minotar.net';

const ServerRoutingURLs = {
    CORE: {
        USER: {
            LOGIN: `${USERS_SERVICE_DOMAIN}/auth/login`,
            REGISTER: `${USERS_SERVICE_DOMAIN}/auth/register`,
            LOGOUT: `${USERS_SERVICE_DOMAIN}/auth/logout`,
            GET_CURRENT_AUTHENTICATED: `${USERS_SERVICE_DOMAIN}/user/current/authenticated`,
            FORGOTTEN_PASSWORD: `${USERS_SERVICE_DOMAIN}/user/forgotten/password`,
            CHANGE_PASSWORD_BY_TOKEN: `${USERS_SERVICE_DOMAIN}/user/change/password/by/token`,
            CHANGE_PASSWORD: `${USERS_SERVICE_DOMAIN}/user/change/password`,
            FIND_BY: `${USERS_SERVICE_DOMAIN}/user/find/by`,

            ADMIN_FIND_BY: `${USERS_SERVICE_DOMAIN}/admin/user/find/by`,
            ROLE_PROMOTE: (username) => `${USERS_SERVICE_DOMAIN}/admin/user/${username}/role/promote`,
            ROLE_DEMOTE: (username) => `${USERS_SERVICE_DOMAIN}/admin/user/${username}/role/demote`,
            ROLE_HAS_NEXT: (username) => `${USERS_SERVICE_DOMAIN}/admin/user/${username}/role/has/next`,
            ROLE_HAS_PREVIOUS: (username) => `${USERS_SERVICE_DOMAIN}/admin/user/${username}/role/has/previous`,
            ROLE_NEXT: (username) => `${USERS_SERVICE_DOMAIN}/admin/user/${username}/role/next`,
            ROLE_PREVIOUS: (username) => `${USERS_SERVICE_DOMAIN}/admin/user/${username}/role/previous`,

            RETRIEVE_INFORMATION: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/information/retrieve`,
            UPDATE_INFORMATION: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/information/update`,

            FOLLOW: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/follow`,
            UNFOLLOW: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/unfollow`,
            ALL_FOLLOWINGS: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/all/followings`,
            ALL_FOLLOWERS: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/all/followers`,
            IS_FOLLOWING: (username) => `${USERS_SERVICE_DOMAIN}/user/${username}/is/following`
        },
        COMMENT: {
            THREAD_ALL: (id) => `${COMMENTS_SERVICE_DOMAIN}/thread/${id}/comment/all`,
            THREAD_DELETE: (threadId, commentId) => `${COMMENTS_SERVICE_DOMAIN}/thread/${threadId}/comment/${commentId}/delete`,
            THREAD_CREATE: (id) => `${COMMENTS_SERVICE_DOMAIN}/thread/${id}/comment/create`,
            CREATOR_TOTAL_COMMENTS: `${COMMENTS_SERVICE_DOMAIN}/comments/total/by/creator`,
            USER_PROFILE_ALL: (username) => `${COMMENTS_SERVICE_DOMAIN}/user/${username}/comment/all`,
            USER_PROFILE_CREATE: (username) => `${COMMENTS_SERVICE_DOMAIN}/user/${username}/comment/create`,
            USER_PROFILE_DELETE: (username, commentId) => `${COMMENTS_SERVICE_DOMAIN}/user/${username}/comment/${commentId}/delete`,
        },
        REACTION: {
            FIND_BY: `${REACTIONS_SERVICE_DOMAIN}/reaction/find/by`,
            CREATE: `${REACTIONS_SERVICE_DOMAIN}/reaction/create`
        },
        THREAD: {
            CREATE: `${THREADS_SERVICE_DOMAIN}/thread/create`,
            ALL: `${THREADS_SERVICE_DOMAIN}/thread/find/all`,
            TOTAL: `${THREADS_SERVICE_DOMAIN}/thread/find/total`,
            DELETE_BY_ID: id => `${THREADS_SERVICE_DOMAIN}/thread/${id}/delete`,
            FIND_BY: `${THREADS_SERVICE_DOMAIN}/thread/find/by`
        },
        REPORT: {
            FIND_BY: `${REPORTS_SERVICE_DOMAIN}/reports/find/by`,
            CREATE: `${REPORTS_SERVICE_DOMAIN}/report/create`,
            DELETE_BY_ID: `${REPORTS_SERVICE_DOMAIN}/report/delete/by/id`,
            IS_USER_REPORTED: `${REPORTS_SERVICE_DOMAIN}/report/is/user/reported`,
        },
        DATA_STORAGE: {
            SET_USER_AVATAR: (username) => `${STORAGE_SERVICE_DOMAIN}/data/set/user/${username}/avatar`,
            GET_USER_AVATAR: (username) => `${STORAGE_SERVICE_DOMAIN}/data/user/${username}/avatar`
        },
        LINK_ACCOUNT: {
            IS_KEY_VALID: (key) => `${LINK_ACCOUNT_SERVICE_DOMAIN}/key/is/valid?key=${key}`,
            KEY_RETRIEVE: (key) => `${LINK_ACCOUNT_SERVICE_DOMAIN}/key/retrieve?key=${key}`,
            LINK_RETRIEVE: (username) => `${LINK_ACCOUNT_SERVICE_DOMAIN}/link/retrieve?username=${username}`,
            LINK_CREATE: `${LINK_ACCOUNT_SERVICE_DOMAIN}/link/create`,
            LINK_REMOVE: `${LINK_ACCOUNT_SERVICE_DOMAIN}/link/remove`,
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