import axios from "axios"

export async function getGenres() {
    let response = await axios.get(
        process.env.REACT_APP_API_URL + `/genres`
    )
    .catch(err => { 
        alert(err.message)
    })
    return response.data
}

export async function getGenre(id) {
    let response = await axios.get(
        process.env.REACT_APP_API_URL + `/genres/${id}`
    )
    return response.data
}

export async function deleteGenre(id) {
    let response = await axios.delete(
        process.env.REACT_APP_API_URL + `/genres/delete/${id}`
    )
    return response.data
}

export async function addGenre(genre) {
    let response = await axios.post(
        process.env.REACT_APP_API_URL + `/genres/add`,
        genre
    )
    .catch(err => { 
        alert(err.message)
    })
    return response.data
}

export async function updateGenre(genre) {
    let response = await axios.post(
        process.env.REACT_APP_API_URL + `/genres/update/${genre.id}`,
        genre
    )
    return response.data
}