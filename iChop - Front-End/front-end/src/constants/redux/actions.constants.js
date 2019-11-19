const Actions = {
    NAVBAR_GUEST_SHOW_DROPDOWN: 'NAVBAR_GUEST_SHOW_DROPDOWN',
    NAVBAR_GUEST_SELECT_LOGIN: 'NAVBAR_GUEST_SELECT_LOGIN',
    NAVBAR_GUEST_SELECT_REGISTER: 'NAVBAR_GUEST_SELECT_REGISTER',
    NAVBAR_GUEST_SELECT_FORGOTTEN_PASSWORD: 'NAVBAR_GUEST_SELECT_FORGOTTEN_PASSWORD',

    AUTHENTICATED_USER_INFO_SET: 'AUTHENTICATED_USER_INFO_SET',
    AUTHENTICATED_USER_INFO_REMOVE: 'AUTHENTICATED_USER_INFO_REMOVE',

    USER_PROFILE_SET_USER: 'USER_PROFILE_SET_USER',
    USER_PROFILE_SET_FOLLOW: 'USER_PROFILE_SET_FOLLOW',
    USER_PROFILE_SET_POSTS: 'USER_PROFILE_SET_POSTS',
    USER_PROFILE_SET_INFORMATION: 'USER_PROFILE_SET_INFORMATION',
    USER_PROFILE_SET_MINECRAFT: 'USER_PROFILE_SET_MINECRAFT',
    USER_PROFILE_SET_REACTIONS: 'USER_PROFILE_SET_REACTIONS',
    USER_PROFILE_CLEAR: 'USER_PROFILE_CLEAR',

    THREADS_ALL_SET: 'THREADS_ALL_SET',
    THREADS_ALL_SET_TOTAL: 'THREADS_ALL_SET_TOTAL',
    THREADS_ALL_REMOVE_BY_ID: 'THREADS_ALL_REMOVE_BY_ID',
    THREADS_ALL_SET_STATISTICS: 'THREADS_ALL_SET_STATISTICS',

    THREAD_READ_SET_THREAD: 'THREAD_READ_SET_THREAD',
    THREAD_READ_SET_CREATOR_INFO: 'THREAD_READ_SET_CREATOR_INFO',
    THREAD_READ_SET_STATISTICS: 'THREAD_READ_SET_STATISTICS',

    THREAD_READ_SET_COMMENTS: 'THREAD_READ_SET_COMMENTS',
    THREAD_READ_SET_COMMENT_CREATOR_INFO: 'THREAD_READ_SET_COMMENT_CREATOR_INFO',
    THREAD_READ_SET_COMMENT_STATISTICS: 'THREAD_READ_SET_COMMENT_STATISTICS',

    THREAD_READ_SHOW_CREATE_COMMENT: 'THREAD_READ_SHOW_CREATE_COMMENT'
};

export default Actions;