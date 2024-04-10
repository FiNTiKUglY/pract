import axios from "axios"

export async function getGenres() {
    await axios.get(
        process.env.REACT_APP_API_URL + `/genres`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function getGenre(id) {
    await axios.get(
        process.env.REACT_APP_API_URL + `/genres/${id}`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function deleteGenre(id) {
    await axios.delete(
        process.env.REACT_APP_API_URL + `/genres/delete/${id}`
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function addGenre(genre) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/genres/add`,
        genre
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}

export async function updateGenre(genre) {
    await axios.post(
        process.env.REACT_APP_API_URL + `/genres/update/${genre.id}`,
        genre
    )
    .then((response) => {
        return response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
}