import React,{Component} from 'react';
import controlSidebarReduxHoc from "../../../../redux/hocs/control.sidebar.hoc";

class UserControlSidebar extends Component {

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

        return (
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
        );
    }

}

export default controlSidebarReduxHoc(UserControlSidebar);