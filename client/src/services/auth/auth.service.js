import { useHistory } from "react-router-dom";
import axios from "axios";
import { setCookie, getCookie, clearCookie } from "../../config";
import { useAppState } from "../../AppState";


let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;

export function useAuth() {
	let history = useHistory();
	const [state, dispatch] = useAppState()
	//set user
	const setUserContext = async () => {
		return await
			axios.get("/api/users/info")
				.then((res) => {
					dispatch({ type: 'GET_USER', payload: { data: res.data } })
					history.push("/home");
				})
				.catch((err) => {
					console.log("err", err);
				});
	};

	//login user
	const loginUser = async (data) => {
		const { username, password } = data;
		return axios
			.post("/api/auth/login", { username, password })
			.then(async (res) => {
				let jwt = res.data.tokenType + " " + res.data.accessToken;
				setCookie("jwt", jwt, 10000);
				//gọi service tìm kiếm user theo jwt để set user vào UserContext
				await setUserContext();
			})
			.catch((err) => {
				console.log("error", err);
			});
	};

	return {
		loginUser,
	};
}
export function useLogout() {
	let history = useHistory();
	const logoutUser = async () => {
		clearCookie("jwt")
		history.push("/")
	}
	return {
		logoutUser
	}
}