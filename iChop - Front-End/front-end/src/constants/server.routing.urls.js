const CORE_SERVER_DOMAIN = 'http://localhost:8000';
const DATA_SERVER_DOMAIN = 'http://localhost:8001';
const OTHER_CRAFATAR_SERVER_DOMAIN = 'https://crafatar.com';
const OTHER_MINOTAR_SERVER_DOMAIN = 'https://minotar.net';

const ServerRoutingURLs = {
    CORE: {},
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