import axios from "axios"

export async function getBooks() {
    let response = await axios.get(
        "http://localhost:8080/api/books"
    )
    return response.data
}

export async function getBook(id) {
    let response = await axios.get(
        "http://localhost:8080/api/books/" + id
    )
    return response.data
}

export async function deleteBook(id) {
    let response = await axios.delete(
        'http://localhost:8080/api/books/delete/' + id
    )
    return response.data
}

export async function addBook(book) {
    let response = await axios.post(
        'http://localhost:8080/api/books/add',
        book
    )
    return response.data
}

export async function updateBook(book) {
    let response = await axios.post(
        'http://localhost:8080/api/books/update/' + book.id,
        book
    )
    return response.data
}