import RequestTypes from "../constants/rest/request.type.constants";
import ContentType from "../constants/rest/content.type.constants";
import RequestHeaders from "../constants/rest/request.header.constants";


const Requester = {

    async post(url, data) {

        let response = await fetch(url, {
            method: RequestTypes.POST,
            credentials: 'include',
            headers: {
                [RequestHeaders.CONTENT_TYPE]: ContentType.JSON
            },
            body: JSON.stringify(data)
        });

        return await response.json();
    },

    async get(url) {
        let response = await fetch(url);
        return await response.json();
    }

};

export default Requester;