import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";
import './SideInformationUser.css'
import PropTypes from "prop-types";
import withState from "../../../../hocs/with.state";

class SideInformationUser extends Component {

    render() {
        let {lastOnline,registrationDate,totalMessages} = this.props.userProfileInfo;

        let datePattern = 'dd mmm,yyyy';

        /*TODO: total messages*/

        return (
            <Fragment>

                {lastOnline !== null ? (<Info title="Last Online" content={dateFormat(lastOnline, datePattern)}/>) : null}
                {registrationDate !== null ? (<Info title="Joined On" content={dateFormat(registrationDate, datePattern)}/>) : null}
                <Info title="Messages" content={totalMessages}/>

            </Fragment>
        );
    }
}

export default withState(SideInformationUser);

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