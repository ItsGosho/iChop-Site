import React, {Component} from 'react';
import qs from 'qs';

class PaginationNav extends Component {


    render() {
        // eslint-disable-next-line no-restricted-globals
        let page = qs.parse(location.search,{ignoreQueryPrefix: true}).page;

        return (
            <nav aria-label="Page navigation example" className="d-flex justify-content-center align-items-center">
                <ul className="pagination">

                    <th:block th:if="${#request.getParameter('page')} != null">

                        <th:block
                            th:if="${(#conversions.convert(#request.getParameter('page'), 'Integer')) - 1} >= 0">
                            <li className="page-item"><a className="page-link"
                                                         th:href="@{/post/reports/all(page=${(#conversions.convert(#request.getParameter('page'), 'Integer')) - 1})}">Previous</a>
                            </li>
                        </th:block>

                    </th:block>

                    <th:block th:each="i: *{#numbers.sequence(1,totalPages)}">
                        <li className="page-item"><a className="page-link" th:text="${i}"
                                                     th:href="@{/post/reports/all(page=${i-1})}"></a></li>
                    </th:block>


                    <th:block th:if="${#request.getParameter('page')} != null">

                        <th:block
                            th:if="*{(#conversions.convert(#request.getParameter('page'), 'Integer')) + 1 <= totalPages-1}">
                            <li className="page-item"><a className="page-link"
                                                         th:href="@{/post/reports/all(page=${(#conversions.convert(#request.getParameter('page'), 'Integer')) + 1})}">Next</a>
                            </li>
                        </th:block>

                    </th:block>

                </ul>
            </nav>
        );
    }

}

export default PaginationNav;