import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";
import './SideInformationUser.css'
import PropTypes from "prop-types";

class SideInformationUser extends Component {

    render() {
        let {user} = this.props;
        let datePattern = 'dd mmm,yyyy';

        let lastOnline = dateFormat(user.lastOnline, datePattern);
        let registrationDate = dateFormat(user.registrationDate, datePattern);
        let totalMessages = 32;
        /*TODO: total messages*/

        return (
            <Fragment>

                {user.lastOnline !== null ? (<Info title="Last Online" content={lastOnline}/>) : null}
                {user.registrationDate !== null ? (<Info title="Joined On" content={registrationDate}/>) : null}
                <Info title="Messages" content={totalMessages}/>

            </Fragment>
        );
    }
}

SideInformationUser.propTypes = {
    user: PropTypes.object
};


export default SideInformationUser;

const Info = (props) => {
    let {title, content} = props;

    return (
        <div className="col-md-auto">
            <div className="row info-row">

                <div className="col-md">
                    {title}:
                </div>

                <div className="col-md">
                    <span className="info-content">{content}</span>
                </div>

            </div>
        </div>
    )
};