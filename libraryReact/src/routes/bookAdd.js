import React, { useState, useEffect } from 'react';
import { getAuthors } from "../api/authorService"
import { getGenres } from "../api/genreService"
import Select  from "react-select";
import { useNavigate } from "react-router-dom";
import { addBook } from "../api/bookService"

export default function BooksAdd() {
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [shortDescription, setShortDescription] = useState('');
    const [cost, setCost] = useState('');
    const [downloadLink, setDownloadLink] = useState('');
    const [imageLink, setImageLink] = useState('');
    const [author, setAuthor] = useState({value: null, label: null});
    const [genres, setGenres] = useState([]);
    const [newBook, setNewBook] = useState({});
    const [errors, setErrors] = useState({});
    const [submitting, setSubmitting] = useState(false);

    let authorsList = [];
    let genresList = [];

    loadAuthors();
    loadGenres();

    useEffect(() => {
        const addData = async (book) => {
            await addBook(book);
        }
        if (Object.keys(errors).length === 0 && submitting) {
            addData(newBook);
            navigate('/books');
        }
      }, [errors]);

    const validateValues = (book) => {
        let errors = {};
        if (book.title.length < 1) {
            errors.title = "Название слишком короткое";
            alert("Название слишком короткое")
        }
        if (book.title.length > 80) {
            errors.title = "Название слишком длинное";
            alert("Название слишком длинное")
        }
        if (book.shortDescription.length < 5) {
            errors.shortDescription = "Описание слишком короткое";
            alert("Описание слишком короткое")
        }
        if (book.shortDescription.length > 255) {
            errors.shortDescription = "Описание слишком длинное";
            alert("Описание слишком длинное")
        }
        if (book.cost.toString().replace(',', '.').includes('.') && book.cost.toString().replace(',').split('.')[1].length > 2) {
            console.log(book.cost.toString().split('.')[1])
            errors.cost = "У цены больше 2 знаков после точки";
            alert("У цены больше 2 знаков после точки")
        }
        if (book.author == null) {
            errors.author = "Выберите автора";
            alert("Выберите автора")
        }
        try {
            let value = Number(book.cost)
            if (value <= 0) {
                errors.cost = "Число не может быть отрицательным";
                alert("Число не может быть отрицательным")
            }
        }
        catch {
            errors.cost = "Цена должны быть числом";
            alert("Цена должны быть числом")
        }
        return errors;
    };

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    onChange={(e) => setTitle(e.target.value)}
                />
                <Select
                    options={authorsList}
                    placeholder="Автор"
                    value={author}
                    onChange={handleSelectAuthor}
                    isSearchable={true}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    onChange={(e) => setShortDescription(e.target.value)}
                />
                <Select
                    options={genresList}
                    placeholder="Жанры"
                    value={genres}
                    onChange={handleSelectGenre}
                    isSearchable={true}
                    isMulti
                />
                <input
                    placeholder="Цена"
                    className="form-control"
                    type="number"
                    onChange={(e) => setCost(e.target.value)}
                />
                <input
                    placeholder="Ссылка загрузки"
                    className="form-control"
                    type="text"
                    onChange={(e) => setDownloadLink(e.target.value)}
                />
                <input
                    placeholder="Ссылка изображения"
                    className="form-control"
                    type="text"
                    onChange={(e) => setImageLink(e.target.value)}
                />
                <button
                    onClick={addBookClick}>
                    Добавить
                </button>
            </div>
        </section>
    );

    function handleSelectAuthor(data) {
        setAuthor(data);
    }

    function handleSelectGenre(data) {
        setGenres(data);
    }

    async function loadAuthors() {
        const authors = await getAuthors()
        authors.map(author =>
            authorsList.push({ value: author, label: `${author.surname} ${author.name}`})    
        )
    }

    async function loadGenres() {
        const genres = await getGenres()
        genres.map(genre =>
            genresList.push({ value: genre, label: genre.name})    
        )
    }

    async function addBookClick() {
        let book = {}
        book.id = crypto.randomUUID();
        book.title = title
        book.shortDescription = shortDescription
        book.cost = cost
        book.author = author.value
        book.genres = genres.map(genre => genre.value)
        book.downloadLink = downloadLink
        book.imageLink = imageLink
        setErrors(validateValues(book))
        setNewBook(book)
        setSubmitting(true);
    }
}