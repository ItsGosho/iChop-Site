const CORE_SERVER_DOMAIN = 'http://localhost:8000';
const DATA_SERVER_DOMAIN = 'http://localhost:8001';

const ServerRoutingURLs = {
    CORE: {},
    DATA: {
        USER: {
            AVATAR: {
                GET: `${DATA_SERVER_DOMAIN}/data/user/:username/avatar`
            }
        }
    }
};

export default ServerRoutingURLs;