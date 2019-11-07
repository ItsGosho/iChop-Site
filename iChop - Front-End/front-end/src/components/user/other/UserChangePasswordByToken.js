import React, {Component} from 'react';
import UniversalPasswordsInputs from "../../other/UniversalPasswordsInputs";
import UserServices from "../../../services/user.services";
import {withRouter} from "react-router-dom";
import queryString from "query-string";

class UserChangePasswordByToken extends Component {

    constructor(props) {
        super(props);

        this.state = {
            token: ''
        };

        this.onChangeClick = this.onChangeClick.bind(this);
        this.getQueryParam = this.getQueryParam.bind(this);
    }

    async componentDidMount() {
        let token = await this.getQueryParam(QueryParams.TOKEN);
        console.log(token);
    }

    onChangeClick() {
        let {token, password, confirmPassword} = this.state;

        UserServices.changePasswordByToken(token, password, confirmPassword);
    }

    getQueryParam(name) {
        let queryParams = queryString.parse(this.props.location.search);
        console.log(queryParams);
        return queryParams[name];
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
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
        );
    }

}

export default withRouter(UserChangePasswordByToken);

const QueryParams = {
    TOKEN: 'token'
};