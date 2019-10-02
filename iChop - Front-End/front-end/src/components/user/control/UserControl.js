import React, {Component} from 'react';
import UserControlInformation from "./UserControlInformation";
import UserControlRole from "./role/UserControlRole";
import controlSidebarReduxHoc from "../../../redux/hocs/control.sidebar.hoc";
import UserControlNav from "./base/UserControlNav";

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

        return (
            <div>
                <UserControlNav/>

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