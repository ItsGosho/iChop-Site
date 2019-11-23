import React from 'react';
import ReactPaginate from "react-paginate";
import PropTypes from 'prop-types';


const PaginationNav = ({total, resultsPerPage,onPageChange}) => (
    <ReactPaginate
        previousLabel={'previous'}
        nextLabel={'next'}
        breakLabel={'...'}
        pageCount={total / resultsPerPage}
        /*
        marginPagesDisplayed={2}
        pageRangeDisplayed={5}*/
        onPageChange={onPageChange}
        breakClassName={'page-item'}
        breakLinkClassName={'page-link'}
        containerClassName={'pagination justify-content-center'}
        pageClassName={'page-item'}
        pageLinkClassName={'page-link'}
        previousClassName={'page-item'}
        previousLinkClassName={'page-link'}
        nextClassName={'page-item'}
        nextLinkClassName={'page-link'}
        activeClassName={'active'}/>
);

export default PaginationNav;

PaginationNav.propTypes = {
    total: PropTypes.number,
    resultsPerPage: PropTypes.number,
    onPageChange: PropTypes.func,
};