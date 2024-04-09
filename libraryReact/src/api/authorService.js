import axios from "axios"

export async function getAuthors() {
    let response = await axios.get(
        process.env.REACT_APP_API_URL + `/authors`
    )
    return response.data
}

export async function getAuthor(id) {
    let response = await axios.get(
        process.env.REACT_APP_API_URL + `/authors/${id}`
    )
    return response.data
}

export async function deleteAuthor(id) {
    let response = await axios.delete(
        process.env.REACT_APP_API_URL + `/authors/delete/${id}`
    )
    return response.data
}

export async function addAuthor(author) {
    let response = await axios.post(
        process.env.REACT_APP_API_URL + `/authors/add`,
        author
    )
    return response.data
}

export async function updateAuthor(author) {
    let response = await axios.post(
        process.env.REACT_APP_API_URL + `/authors/update/${author.id}`,
        author
    )
    return response.data
}