import axios from "axios"

export async function getAuthors() {
    await axios.get(
        process.env.REACT_APP_API_URL + `/authors`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    
}

export async function getAuthor(id) {
    await axios.get(
        process.env.REACT_APP_API_URL + `/authors/${id}`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function deleteAuthor(id) {
    await axios.delete(
        process.env.REACT_APP_API_URL + `/authors/delete/${id}`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function addAuthor(author) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/authors/add`,
        author
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function updateAuthor(author) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/authors/update/${author.id}`,
        author
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}