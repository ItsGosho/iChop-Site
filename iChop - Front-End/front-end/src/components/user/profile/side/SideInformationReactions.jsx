import React, {Fragment} from 'react';
import './SideInformationReactions.css';
import withState from "../../../../hocs/with.state";
import PropTypes from 'prop-types';


const SideInformationReactions = ({userProfileInfo: {totalLikes, totalDislikes} = {}}) => (
    <Fragment>
        <Total icon="👍" color="darkgreen" total={totalLikes}/>
        <Total icon="👎" color="indianred" total={totalDislikes}/>
    </Fragment>
);

export default withState(SideInformationReactions);


const Total = ({icon, total, color}) => (
    <div className="col-md-auto">
        <div className="row row-holder">
            <div className="col-md-auto div-icon">
                {icon}
            </div>
            <div className="col-md-auto div-total">
                <span className="total-reactions" style={{'color': color}}>
                    {total}
                </span>
            </div>
        </div>
        <div className="dropdown-divider"/>
    </div>
);

Total.propTypes = {
    icon: PropTypes.string,
    total: PropTypes.number,
    color: PropTypes.string,
};