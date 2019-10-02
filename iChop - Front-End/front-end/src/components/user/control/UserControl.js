import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import UserControlInformation from "./UserControlInformation";
import UserControlRole from "./role/UserControlRole";
import controlSidebarReduxHoc from "../../../redux/hocs/control.sidebar.hoc";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class UserControl extends Component {

    constructor(props) {
        super(props);

        this.onInformationClick = this.onInformationClick.bind(this);
        this.onRoleManagementClick = this.onRoleManagementClick.bind(this);
        this.onImageError = this.onImageError.bind(this);
    }

    onImageError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
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

        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <Link className="navbar-brand" to={profileUrl}>
                        <span>
                          <img src={userAvatarUrl} onError={this.onImageError}
                               style={{'width': '20px', 'height': '20px'}}/>
                        </span>
                        <span>{username}</span>
                    </Link>
                </nav>

                <div className="container" style={{'marginLeft': '0', 'marginTop': '10px'}}>
                    <div className="row">
                        <div className="col-sm">
                            <div className="card" style={{'width': '15rem'}}>
                                <div className="card-header">Options Menu</div>
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