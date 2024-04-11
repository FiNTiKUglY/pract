import axios from "axios"

export async function signIn(email, password) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/auth/signin`, 
        {
            "email": email,
            "password": password
        }
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}