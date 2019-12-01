const PrefixURLs = {
    OPTIONS_PREFIX: '/user/options',
    USER_CONTROL_PREFIX: (username) => `/user/${username}/control`,
};

export default PrefixURLs;