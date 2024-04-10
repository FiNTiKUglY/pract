import axios from "axios"

export async function signIn(email, password) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/auth/signin`, 
        {
            "email": email,
            "password": password
        }
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}