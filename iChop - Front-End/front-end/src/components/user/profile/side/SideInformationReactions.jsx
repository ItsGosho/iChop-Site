import React, {Component, Fragment} from 'react';
import './SideInformationReactions.css';
import withState from "../../../../hocs/with.state";


class SideInformationReactions extends Component {


    render() {
        let {totalLikes,totalDislikes} = this.props.userProfileInfo;

        return (
            <Fragment>
                <Total icon="👍" color="darkgreen" total={totalLikes}/>
                <Total icon="👎" color="indianred" total={totalDislikes}/>
            </Fragment>
        );
    }
}

export default withState(SideInformationReactions);


const Total = (props) => {
    let {icon, total, color} = props;

    return (
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
    )
};