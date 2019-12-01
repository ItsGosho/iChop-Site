import * as queryString from "query-string";

class QueryHelper {

    constructor(props) {
        this.props = props;
    };

    addQueryParameter(key, value) {
        let location = this.props.match.path;
        let queryParams = queryString.parse(new URLSearchParams(window.location.search).toString());

        console.log(queryParams);
        queryParams[key] = value;

        let params = '?' + queryString.stringify(queryParams);
        this.props.history.replace(location + params, {});

        return params;
    };

    getQueryParam(name) {
        let queryParams = queryString.parse(new URLSearchParams(window.location.search).toString());
        return queryParams[name];
    };

    removeQueryParam(name) {
        let location = this.props.match.path;
        let queryParams = queryString.parse(new URLSearchParams(window.location.search).toString());

        delete queryParams[name];

        let params = '?' + queryString.stringify(queryParams);
        this.props.history.replace(location + params, {});
    };
};

export default QueryHelper;