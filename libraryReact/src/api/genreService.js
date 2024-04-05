import axios from "axios"

export async function getGenres() {
    let response = await axios.get(
        "http://localhost:8080/api/genres"
    )
    return response.data
}

export async function getGenre(id) {
    let response = await axios.get(
        "http://localhost:8080/api/genres/" + id
    )
    return response.data
}

export async function deleteGenre(id) {
    let response = await axios.delete(
        'http://localhost:8080/api/genres/delete/' + id
    )
    return response.data
}

export async function addGenre(genre) {
    let response = await axios.post(
        'http://localhost:8080/api/genres/add',
        genre
    )
    return response.data
}

export async function updateGenre(genre) {
    let response = await axios.post(
        'http://localhost:8080/api/genres/update/' + genre.id,
        genre
    )
    return response.data
}