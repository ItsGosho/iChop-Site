import React, {Component, Fragment} from 'react';
import UniversalPasswordsInputs from "../../other/UniversalPasswordsInputs";
import UserServices from "../../../services/user.services";
import {Redirect, withRouter} from "react-router-dom";
import RoutingURLs from "../../../constants/routing/routing.constants";

const queryString = require("query-string");

class UserChangePasswordByToken extends Component {

    constructor(props) {
        super(props);

        this.state = {
            token: '',
            isSuccessful: false,
        };

        this.onChangeClick = this.onChangeClick.bind(this);
        this.getQueryParam = this.getQueryParam.bind(this);
    }

    componentDidMount() {
        let token = this.getQueryParam(QueryParams.TOKEN);
        this.setState({token: token})
    }

    async onChangeClick() {
        let {token, password, confirmPassword} = this.state;

        let response = await UserServices.changePasswordByToken(token, password, confirmPassword);

        if (response.successful) {
            this.setState({isSuccessful: true});
        }
    }

    getQueryParam(name) {
        let queryParams = queryString.parse(this.props.location.search);
        return queryParams[name];
    }

    render() {
        let {isSuccessful} = this.state;
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <Fragment>
                <div className="col-sm d-flex justify-content-center">
                    <div className="card">
                        <div className="card-body">
                            <form className="px-4 py-3">

                                <UniversalPasswordsInputs onChange={onChange}/>

                                <div align="center">

                                    <button type="button"
                                            data-style="zoom-in"
                                            className="btn btn-primary btn-ladda"
                                            onClick={this.onChangeClick}>
                                        Reset
                                    </button>

                                    <div className="dropdown-divider"/>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
                {isSuccessful ? <Redirect to={RoutingURLs.HOME} push/> : null}
            </Fragment>
        );
    }

}

export default withRouter(UserChangePasswordByToken);

const QueryParams = {
    TOKEN: 'token'
};