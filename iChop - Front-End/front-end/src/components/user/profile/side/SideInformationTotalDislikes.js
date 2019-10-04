import React,{Component} from 'react';

class SideInformationTotalDislikes extends Component {

    render() {

        return (
            <div className="col-md-auto">
                <div className="row" style="font-size: 15px">
                    <div className="col-md-auto" style="font-size: 16px">
                        👎
                    </div>
                    <div className="col-md-auto" style="column-width: 150px">
                               <span style="display: inline-block;float: right;color: indianred;"
                                     th:text="*{totalLikes}">
                                    0000
                               </span>
                    </div>
                </div>
                <div className="dropdown-divider" style="width: 100%"></div>
            </div>
        );
    }
}

export default SideInformationTotalDislikes;