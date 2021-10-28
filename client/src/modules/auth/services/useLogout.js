import { useHistory } from 'react-router-dom';
import { clearCookie } from '../../../config';

export default function useLogout() {
    let history = useHistory();

    const logoutUser = async () => {
        clearCookie("jwt")
        history.push("/")
    }

    return {
        logoutUser
    }

}