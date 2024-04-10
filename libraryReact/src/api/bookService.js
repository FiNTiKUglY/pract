import axios from "axios"

export async function getBooks() {
    await axios.get(
        process.env.REACT_APP_API_URL + `/books`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function getBook(id) {
    await axios.get(
        process.env.REACT_APP_API_URL + `/books/${id}`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function deleteBook(id) {
    await axios.delete(
        process.env.REACT_APP_API_URL + `/books/delete/${id}`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function addBook(book) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/books/add`,
        book
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function updateBook(book) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/books/update/${book.id}`,
        book
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}