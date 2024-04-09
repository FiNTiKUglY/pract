import axios from "axios"

export async function signIn(email, password) {
    let response = await axios.post(
        process.env.REACT_APP_API_URL + `/auth/signin`, 
        {
            "email": email,
            "password": password
        }
    )
    return response.data
}