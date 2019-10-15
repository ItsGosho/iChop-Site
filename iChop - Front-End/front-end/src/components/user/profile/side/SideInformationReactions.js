import React, {Component, Fragment} from 'react';
import './SideInformationReactions.css';


class SideInformationReactions extends Component {


    render() {
        let totaLikes = 56;
        let totalDislikes = 3;

        return (
            <Fragment>
                <div className="col-md-auto">
                    <div className="row row-holder">
                        <div className="col-md-auto div-icon">
                            👍
                        </div>
                        <div className="col-md-auto div-total">
                               <span className="total-reactions-like">
                                    {totaLikes}
                               </span>
                        </div>
                    </div>
                    <div className="dropdown-divider"/>
                </div>


                <div className="col-md-auto">
                    <div className="row row-holder">
                        <div className="col-md-auto div-icon">
                            👎
                        </div>
                        <div className="col-md-auto div-total">
                               <span className="total-reactions-dislike">
                                    {totalDislikes}
                               </span>
                        </div>
                    </div>
                    <div className="dropdown-divider"/>
                </div>

            </Fragment>
        );
    }

}

export default SideInformationReactions;