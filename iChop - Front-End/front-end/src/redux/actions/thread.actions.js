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

export {
    fetchAllPageable
}