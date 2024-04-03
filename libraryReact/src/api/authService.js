import axios from "axios"

export async function signIn(email, password) {
    let response = await axios.post(
        "http://localhost:8080/api/auth/signin", 
        {
            "email": email,
            "password": password
        }
    )
    return response.data
}