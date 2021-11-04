import { useState, useContext, useEffect } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import { UserContext } from "../../../context/userContext";
import { setCookie, getCookie } from "../../../config";
import { useAppState } from "../../../AppState"

export default function useAuth() {
	let history = useHistory();
	const [error, setError] = useState(null);
	console.log("useAuth", useAuth);
	const [state, dispatch] = useAppState()
	//set user
	const setUserContext = async () => {
		console.log("setUserContext")
		let token = getCookie("jwt");
		let i = axios.create();
		i.defaults.headers.common["Authorization"] = token;
		return await i
			.get("http://localhost:8080/api/users/info")
			.then((res) => {
				console.log("res.data", res.data)
				dispatch({ type: 'GET_USER', payload: { data: res.data } })
				history.push("/home");
			})
			.catch((err) => {
				console.log("err", err);
				// setError(err.response.data.message);
			});
	};


	//login user
	const loginUser = async (data) => {
		console.log("loginUser")
		const { username, password } = data;
		return axios
			.post("http://localhost:8080/api/auth/login", {
				username,
				password,
			})
			.then(async (res) => {
				console.log("Response: ", res.data);
				let jwt = res.data.tokenType + " " + res.data.accessToken;
				setCookie("jwt", jwt, 10000);
				//gọi service tìm kiếm user theo jwt để set user vào UserContext
				await setUserContext();
			})
			.catch((err) => {
				console.log("error", err);
				// setError(err.response.data.message);
			});
	};

	// //register user
	// const registerUser = async (data) => {
	//     console.log(data);
	//     const { username, email, password, passwordConfirm } = data;
	//     return axios.post(`auth/register`, {
	//         username,
	//         email,
	//         password,
	//         passwordConfirm
	//     }).then(async () => {
	//         await setUserContext();
	//     })
	//         .catch((err) => {
	//             return setError(err.response.data);
	//         })
	// };

	return {
		// registerUser,
		loginUser,
		error,
	};
}
