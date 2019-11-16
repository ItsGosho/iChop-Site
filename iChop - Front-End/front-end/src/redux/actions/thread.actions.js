import Actions from "../../constants/redux/actions.constants";
import ThreadServices from "../../services/thread.services";


let fetchAllPageable = (page, size) => {
    return async (dispatch) => {
        let threads = await ThreadServices.getAllPageable(page, size);

        dispatch({
            type: Actions.FETCH_ALL_THREADS,
            payload: {threads}
        });
    }
};

let fetchTotal = () => {
    return async (dispatch) => {
        let total = await ThreadServices.getTotal();

        dispatch({
            type: Actions.FETCH_TOTAL_THREADS,
            payload: {total}
        });
    }
};

export {
    fetchAllPageable,
    fetchTotal
}