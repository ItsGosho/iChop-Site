import React, {Component, Fragment} from 'react';
import './SideInformationReactions.css';


class SideInformationReactions extends Component {


    render() {
        let totaLikes = 56;
        let totalDislikes = 3;

        return (
            <Fragment>
                <div className="col-md-auto">
                    <div className="row" style={{'fontSize': '15px'}}>
                        <div className="col-md-auto" style={{'fontSize': '16px'}}>
                            👍
                        </div>
                        <div className="col-md-auto" style={{'columnWidth': '150px'}}>
                               <span style={{'display': 'inlineBlock', 'float': 'right', 'color': 'green'}}>
                                    {totaLikes}
                               </span>
                        </div>
                    </div>
                    <div className="dropdown-divider"/>
                </div>


                <div className="col-md-auto">
                    <div className="row" style={{'fontSize': '15px'}}>
                        <div className="col-md-auto" style={{'fontSize': '16px'}}>
                            👎
                        </div>
                        <div className="col-md-auto" style={{'columnWidth': '150px'}}>
                               <span style={{'display': 'inlineBlock', 'float': 'right', 'color': 'indianred'}}>
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