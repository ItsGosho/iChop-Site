import React, {Component} from 'react';
import qs from 'qs';
import {Link} from "react-router-dom";

class PaginationNav extends Component {


    render() {
        // eslint-disable-next-line no-restricted-globals
        let page = qs.parse(location.search, {ignoreQueryPrefix: true}).page;
        let totalPages = 10;
        let redirectPage = '/thread/reports/all';

        page = page !== undefined ? Number(page) : undefined;
        let pagesIterator = [10];

        return (
            <nav aria-label="Page navigation example" className="d-flex justify-content-center align-items-center">
                <ul className="pagination">

                    {
                        (() => {
                                if (page !== undefined && page - 1 >= 0) {
                                    return (
                                        <li className="page-item">
                                            <Link to={redirectPage + '?page=' + (page - 1)}>Next</Link>
                                        </li>
                                    );
                                }
                            }
                        )()
                    }

                    {
                        (() => {
                                let result = [];
                                for (let i = 1; i <= totalPages; i++) {
                                    result.push(<li className="page-item">
                                        <Link to={redirectPage + '?page=' + i}>{i}</Link>
                                    </li>)
                                }
                                return result;
                            }
                        )()
                    }

                    {
                        (() => {
                                if (page !== undefined && page + 1 <= totalPages - 1) {
                                    return (
                                        <li className="page-item">
                                            <Link to={redirectPage + '?page=' + (page + 1)}>Next</Link>
                                        </li>
                                    );
                                }
                            }
                        )()
                    }

                </ul>
            </nav>
        );
    }

}

export default PaginationNav;