import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import UserControlInformation from "./UserControlInformation";
import UserControlRole from "./UserControlRole";
import controlSidebarReduxHoc from "../../../redux/hocs/control.sidebar.hoc";

class UserControl extends Component {

    constructor(props) {
        super(props);

        this.onInformationClick = this.onInformationClick.bind(this);
        this.onRoleManagementClick = this.onRoleManagementClick.bind(this);
    }


    onInformationClick(event) {
        event.preventDefault();
        this.props.selectInformation();
    }

    onRoleManagementClick(event) {
        event.preventDefault();
        this.props.selectRoleManagement();
    }

    render() {
        let {isInformationSelected, isRoleManagementSelected} = this.props.redux;

        let username = '${Username of the profile of the user}';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <Link className="navbar-brand" to={profileUrl}>
                        <span>
                          <img src={userAvatarUrl}
                               alt=''
                               style={{'width': '20px', 'height': '20px'}}/>
                        </span>
                        <span>{username}</span></Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    </div>
                </nav>

                <div className="container" style={{'marginLeft': '0', 'marginTop': '10px'}}>
                    <div className="row">
                        <div className="col-sm">
                            <div className="card" style={{'width': '15rem'}}>
                                <div className="card-header">
                                    Options Menu
                                </div>
                                <ul className="list-group list-group-flush">

                                    <a href=' ' onClick={this.onInformationClick}>
                                        <li className="list-group-item control-option">Information</li>
                                    </a>

                                    <a href=' ' onClick={this.onRoleManagementClick}>
                                        <li className="list-group-item control-option">Role Management</li>
                                    </a>

                                </ul>
                            </div>
                        </div>
                        <div className="col-sm">
                            <div className="card">
                                <div className="card-body">

                                    {
                                        (() => {
                                            if (isInformationSelected) {
                                                return (<UserControlInformation/>)
                                            } else if (isRoleManagementSelected) {
                                                return (<UserControlRole/>);
                                            }
                                        })()
                                    }

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default controlSidebarReduxHoc(UserControl);