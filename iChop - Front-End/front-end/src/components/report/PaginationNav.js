import React, {Component} from 'react';
import qs from 'qs';
import {Link} from "react-router-dom";
import Pagination from "react-bootstrap/Pagination";

class PaginationNav extends Component {


    render() {
        // eslint-disable-next-line no-restricted-globals
        let page = qs.parse(location.search, {ignoreQueryPrefix: true}).page;

        let totalPages = 10;
        let redirectPage = '/thread/reports/all';

        page = page === undefined ? 1 : page;

        return (
            <Pagination className={'justify-content-center'}>

                {
                    (() => {
                            if (page - 1 >= 0) {
                                return (
                                    <Pagination.Prev>
                                        <Link to={redirectPage + '?page=' + (page - 1)}>Prev</Link>
                                    </Pagination.Prev>
                                );
                            }
                        }
                    )()
                }

                {
                    (() => {
                            let result = [];
                            for (let i = 1; i <= totalPages; i++) {
                                result.push(
                                    <Pagination.Item>
                                        <Link to={redirectPage + '?page=' + i}>{i}</Link>
                                    </Pagination.Item>
                                )
                            }
                            return result;
                        }
                    )()
                }

                {
                    (() => {
                            if (page + 1 <= totalPages - 1) {
                                return (
                                    <Pagination.Next>
                                        <Link to={redirectPage + '?page=' + (page + 1)}>Next</Link>
                                    </Pagination.Next>
                                );
                            }
                        }
                    )()
                }

            </Pagination>
        );
    }

}

export default PaginationNav;