import React, {Fragment} from 'react';
import dateFormat from "dateformat";
import './SideInformationUser.css'
import PropTypes from "prop-types";
import withState from "../../../../hocs/with.state";

const DATE_PATTERN = 'dd mmm,yyyy';

const SideInformationUser = (props) => {
    let {lastOnline, registrationDate, totalMessages} = props.userProfileInfo;

    /*TODO: total messages*/
    return (
        <Fragment>

            {lastOnline !== null ? (<Info title="Last Online" content={dateFormat(lastOnline, DATE_PATTERN)}/>) : null}
            {registrationDate !== null ? (<Info title="Joined On" content={dateFormat(registrationDate, DATE_PATTERN)}/>) : null}
            <Info title="Messages" content={totalMessages}/>

        </Fragment>
    );
};

export default withState(SideInformationUser);

const Info = ({title, content}) => (
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
);

Info.propTypes = {
    title: PropTypes.string,
    content: PropTypes.string,
};