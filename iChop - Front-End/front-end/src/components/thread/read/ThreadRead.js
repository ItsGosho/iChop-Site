import React, {Component} from 'react';

class ThreadRead extends Component {


    render() {

        return (
            <div id="thread-view" className="container d-flex justify-content-center align-items-center">
                <div className="row">

                    <th:block th:object="${thread}">
                        <div className="col-xs-6">


                            <th:block th:insert="thread/partials/read/thread-main-content"></th:block>

                            <th:block th:insert="thread/partials/read/add-comment-section"></th:block>

                            <th:block th:insert="thread/partials/read/thread-all-comments"></th:block>


                        </div>
                    </th:block>

                </div>
            </div>
        );
    }

}

export default ThreadRead;