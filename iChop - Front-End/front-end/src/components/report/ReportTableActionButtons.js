import React, {Component} from 'react';

class ReportTableActionButtons extends Component {

    render() {
        let entityName = this.props.entityName;

        let onDeleteEntity = this.props.onDeleteEntity;
        let onDeleteReport = this.props.onDeleteReport;

        return (
            <div className="row">
                <div className="dropdown">

                    <button className="btn btn-warning btn-sm dropdown-toggle" type="button"
                            id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ⚙Take Action!
                    </button>

                    <span className="dropdown-menu">


                        <button type="button" onClick={onDeleteEntity}
                                className="btn btn-danger btn-sm dropdown-item">❌Delete {entityName}</button>


                        <button type="button" onClick={onDeleteReport}
                                className="btn btn-danger btn-sm dropdown-item">😖Delete Report
                            </button>

                    </span>
                </div>
            </div>
        );
    }
}

export default ReportTableActionButtons;