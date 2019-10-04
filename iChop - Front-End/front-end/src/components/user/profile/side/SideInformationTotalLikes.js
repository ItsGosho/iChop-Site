import React, {Component} from 'react';

class SideInformationTotalLikes extends Component {

    render() {
        let totaLikes = 32;

        return (
            <div className="col-md-auto">
                <div className="row" style={{'fontSize': '15px'}}>
                    <div className="col-md-auto" style={{'fontSize': '16px'}}>
                        👍
                    </div>
                    <div className="col-md-auto" style={{'columnWidth': '150px'}}>
                               <span style={{'display': 'inlineBlock', 'float': 'right', 'color': 'indianred'}}>
                                    {totaLikes}
                               </span>
                    </div>
                </div>
                <div className="dropdown-divider" style={{'width': '100%'}}/>
            </div>
        );
    }
}

export default SideInformationTotalLikes;