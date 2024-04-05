import axios from "axios"

export async function getAuthors() {
    let response = await axios.get(
        "http://localhost:8080/api/authors"
    )
    return response.data
}

export async function getAuthor(id) {
    let response = await axios.get(
        "http://localhost:8080/api/authors/" + id
    )
    return response.data
}

export async function deleteAuthor(id) {
    let response = await axios.delete(
        'http://localhost:8080/api/authors/delete/' + id
    )
    return response.data
}

export async function addAuthor(author) {
    let response = await axios.post(
        'http://localhost:8080/api/authors/add',
        author
    )
    return response.data
}

export async function updateAuthor(author) {
    let response = await axios.post(
        'http://localhost:8080/api/authors/update/' + author.id,
        author
    )
    return response.data
}