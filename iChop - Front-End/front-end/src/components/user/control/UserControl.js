import React, {Component} from 'react';
import UserControlInformation from "./UserControlInformation";
import UserControlRole from "./role/UserControlRole";
import controlSidebarReduxHoc from "../../../redux/hocs/control.sidebar.hoc";
import UserControlNav from "./base/UserControlNav";
import UserControlSidebar from "./base/UserControlSidebar";

class UserControl extends Component {

    render() {
        let {isInformationSelected, isRoleManagementSelected} = this.props.redux;

        return (
            <div>
                <UserControlNav/>

                <div className="container" style={{'marginLeft': '0', 'marginTop': '10px'}}>
                    <div className="row">

                        <UserControlSidebar/>

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